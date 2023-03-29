package com.varani.isitvegan

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.google.mlkit.vision.barcode.common.Barcode
import com.varani.isitvegan.MainActivity.Companion.TAG

/**
 * Created by Ana Varani on 29/03/2023.
 */
class BarcodeViewModel(barcode: Barcode) {
    var boundingRect: Rect = barcode.boundingBox!!
    var barcodeContent: String = ""
    var barcodeTouchCallback = { v: View, e: MotionEvent -> false } //no-op

    init {
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
                }
            }
//            else -> {
//                qrContent = "Unsupported data type: ${barcode.rawValue.toString()}"
//                Log.d(TAG, "Product: ${barcode.valueType}")
//            }
        }
    }
}