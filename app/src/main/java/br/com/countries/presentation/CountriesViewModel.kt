package br.com.countries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.countries.domain.usecases.GetCountriesUseCase
import br.com.countries.domain.usecases.GetCountrySearchNameUseCase
import br.com.countries.extensions.others.launch
import br.com.countries.network.event.Event
import br.com.countries.presentation.model.CountriesState
import br.com.countries.presentation.model.CountrySearchState

class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountrySearchNameUseCase: GetCountrySearchNameUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CountriesState>()
    val state: LiveData<CountriesState> get() = _state

    private val _searchState = MutableLiveData<CountrySearchState>()
    val searchState: LiveData<CountrySearchState> get() = _searchState

    fun loadCountries() = launch {
        getCountriesUseCase.invoke().collect { event ->
            when (event) {
                is Event.Loading -> {
                    _state.value = CountriesState.Loading
                }

                is Event.Data -> {
                    _state.value = CountriesState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _state.value = CountriesState.Error(event.error)
                }
            }
        }
    }

    fun searchCountryByName(country: String?) = launch {
        getCountrySearchNameUseCase.invoke(country).collect { event ->
            when (event) {
                is Event.Loading -> {
                    _searchState.value = CountrySearchState.Loading
                }

                is Event.Data -> {
                    _searchState.value = CountrySearchState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _searchState.value = CountrySearchState.Error(event.error)
                }
            }
        }
    }
}