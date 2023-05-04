package com.varani.domain

import com.varani.domain.repository.TestProductRepository
import com.varani.model.data.testProductsList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Ana Varani on 04/05/2023.
 */
class GetAllScannedProductsUserCaseTest {

    private val productRepository = TestProductRepository()

    lateinit var useCase: GetAllScannedProductsUserCase

    @Before
    fun setUp() {
        useCase = GetAllScannedProductsUserCase(
            productRepository
        )
    }

    @Test
    fun getAllProducts() = runTest {
        val productList = useCase()

        productRepository.sendProducts(testProductsList)

        assertEquals(testProductsList, productList.first())
    }
}