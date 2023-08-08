package br.com.countries.presentation

import androidx.lifecycle.Observer
import br.com.countries.domain.usecases.GetCountriesUseCase
import br.com.countries.domain.usecases.GetCountrySearchNameUseCase
import br.com.countries.domain.model.dummyCountries
import br.com.countries.network.event.Event
import br.com.countries.presentation.model.CountriesState
import br.com.countries.presentation.model.CountrySearchState
import br.com.countries.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountriesViewModelTest : BaseTest() {

    @RelaxedMockK
    private lateinit var getCountriesUseCase: GetCountriesUseCase

    @RelaxedMockK
    private lateinit var getCountrySearchNameUseCase: GetCountrySearchNameUseCase

    @RelaxedMockK
    private lateinit var stateObserver: Observer<CountriesState>

    @RelaxedMockK
    private lateinit var searchObserver: Observer<CountrySearchState>

    @RelaxedMockK
    private lateinit var viewModel: CountriesViewModel

    @Before
    fun setup() {
        viewModel = CountriesViewModel(
            getCountriesUseCase = getCountriesUseCase,
            getCountrySearchNameUseCase = getCountrySearchNameUseCase
        ).also {
            with(viewModel) {
                state.observeForever(stateObserver)
                searchState.observeForever(searchObserver)
            }
        }
    }

    @After
    fun tearDown() {
        with(viewModel) {
            state.removeObserver(stateObserver)
            searchState.removeObserver(searchObserver)
        }
    }

    @Test
    fun `should load countries when it is called with success`() = runBlocking {

        //Arrange
        val state = CountriesState.ScreenData(listOf(dummyCountries))

        coEvery { getCountriesUseCase.invoke() } returns flowOf(
            Event.loading(isLoading = true),
            Event.data(listOf(dummyCountries))
        )

        coEvery {
            stateObserver.onChanged(state)
        } returns Unit

        //Act
        viewModel.loadCountries()

        //Assert
        coVerify(exactly = 1) {
            getCountriesUseCase.invoke()
        }

        confirmVerified(getCountriesUseCase)
    }

    @Test
    fun `should load countries when it is called with failure`() = runBlocking {

        //Arrange
        val error = mockk<Throwable>(relaxed = true)
        val state = CountriesState.Error(exception = error)

        coEvery { getCountriesUseCase.invoke() } returns flowOf(
            Event.loading(isLoading = true),
            Event.error(error)
        )

        coEvery {
            stateObserver.onChanged(state)
        } returns Unit

        //Act
        viewModel.loadCountries()

        //Assert
        coVerify(exactly = 1) {
            getCountriesUseCase.invoke()
        }

        confirmVerified(getCountriesUseCase)

    }

    @Test
    fun `should load search country when it is called with success`() = runBlocking {

        //Arrange
        val state = CountrySearchState.ScreenData(listOf(dummyCountries))

        coEvery { getCountrySearchNameUseCase.invoke(COUNTRY) } returns flowOf(
            Event.loading(isLoading = true),
            Event.data(listOf(dummyCountries))
        )

        coEvery {
            searchObserver.onChanged(state)
        } returns Unit

        //Act
        viewModel.searchCountryByName(COUNTRY)

        //Assert
        coVerify(exactly = 1) {
            getCountrySearchNameUseCase.invoke(COUNTRY)
        }

        confirmVerified(getCountrySearchNameUseCase)
    }

    @Test
    fun `should load search country when it is called with failure`() = runBlocking {

        //Arrange
        val error = mockk<Throwable>(relaxed = true)
        val state = CountrySearchState.Error(exception = error)

        coEvery { getCountrySearchNameUseCase.invoke(COUNTRY) } returns flowOf(
            Event.loading(isLoading = true),
            Event.error(error)
        )

        coEvery {
            searchObserver.onChanged(state)
        } returns Unit

        //Act
        viewModel.searchCountryByName(COUNTRY)

        //Assert
        coVerify(exactly = 1) {
            getCountrySearchNameUseCase.invoke(COUNTRY)
        }

        confirmVerified(getCountrySearchNameUseCase)

    }

    companion object {
        private const val COUNTRY = "brazil"
    }

}