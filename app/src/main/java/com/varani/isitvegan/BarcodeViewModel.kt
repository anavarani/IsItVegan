package com.varani.isitvegan

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.common.Barcode
import com.varani.isitvegan.domain.RetrieveProductDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
@HiltViewModel
class BarcodeViewModel @Inject constructor(
    private val retrieveProductDataUseCase: RetrieveProductDataUseCase
) : ViewModel() {
    lateinit var boundingRect: Rect
    var barcodeContent: String = ""
    var barcodeTouchCallback = { v: View, e: MotionEvent -> false } //no-op

    fun readBarcode(barcode: Barcode) {
        boundingRect = barcode.boundingBox!!
        when (barcode.valueType) {
            Barcode.TYPE_URL -> {
                barcodeContent = barcode.url!!.url!!
                barcodeTouchCallback = { v: View, e: MotionEvent ->
                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(
                            e.x.toInt(), e.y.toInt()
                        )
                    ) {
                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
                        openBrowserIntent.data = Uri.parse(barcodeContent)
                        v.context.startActivity(openBrowserIntent)
                    }
                    true // return true from the callback to signify the event was handled
                }
            }
            Barcode.TYPE_PRODUCT -> {
                if (barcode.rawValue.toString().isNotEmpty()) {
                    Log.d(TAG, "Product: ${barcode.rawValue.toString()}")
                    barcodeContent = "Product: ${barcode.rawValue.toString()}"

                    viewModelScope.launch(Dispatchers.IO) {
                        val result = retrieveProductDataUseCase(barcode.rawValue.toString())
                        Log.d(TAG, "readBarcode: $result")
                    }
                }
            }
        }
    }
}