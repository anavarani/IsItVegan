package com.varani.domain

import com.varani.domain.model.mapToProductDetail
import com.varani.domain.repository.TestProductRepository
import com.varani.model.data.testProductsList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Ana Varani on 04/05/2023.
 */
class GetProductByBarcodeUseCaseTest {

    private val productRepository = TestProductRepository()

    lateinit var useCase: GetProductByBarcodeUseCase

    @Before
    fun setUp() {
        useCase = GetProductByBarcodeUseCase(
            productRepository
        )
    }

    @Test
    fun getAllProducts() = runTest {
        val product = useCase(testProductsList[0].barcode)

        productRepository.sendProducts(testProductsList)

        val flow = flowOf(testProductsList[0]).mapToProductDetail()

        assertEquals(
            flow.first(),
            product.first()
        )
    }
}