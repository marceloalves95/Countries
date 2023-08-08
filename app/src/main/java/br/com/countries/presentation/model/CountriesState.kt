package br.com.countries.presentation.model

import br.com.countries.domain.models.Countries

sealed class CountriesState{
    object Loading: CountriesState()
    data class ScreenData(val screenData: List<Countries>) : CountriesState()
    data class Error(val exception: Throwable? = null) : CountriesState()
}
