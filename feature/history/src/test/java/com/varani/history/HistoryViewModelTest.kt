package com.varani.history

import com.varani.domain.GetAllScannedProductsUserCase
import com.varani.model.data.testProductsList
import com.varani.testing.MainDispatcherRule
import com.varani.testing.repository.TestProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

/**
 * Created by Ana Varani on 28/04/2023.
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class HistoryViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private var productRepository = TestProductRepository()

    private var getAllScannedProductsUserCase = GetAllScannedProductsUserCase(
        productRepository
    )

    private lateinit var viewModel: HistoryViewModel

    @Before
    fun setUp() {
        viewModel = HistoryViewModel(
            getAllScannedProductsUserCase
        )
    }

    @Test
    fun `when initialised, UiState is Loading`() = runTest {
        assertEquals(HistoryUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `when collecting flow, UiState is Success`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        productRepository.sendProducts(testProductsList)

        val historyUiState = viewModel.uiState.value

        assertIs<HistoryUiState.Success>(historyUiState)

        collectJob.cancel()
    }

    @Test
    fun `when collecting flow, UiState Success matches repository`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        productRepository.sendProducts(testProductsList)

        val productsFromRepository = productRepository.getAllProductsStream().first()

        val historyUiState = viewModel.uiState.value

        assertEquals(
            productsFromRepository,
            (historyUiState as HistoryUiState.Success).scanProducts
        )

        collectJob.cancel()
    }
}