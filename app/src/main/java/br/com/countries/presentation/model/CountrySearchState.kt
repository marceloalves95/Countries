package br.com.countries.presentation.model

import br.com.countries.domain.models.Countries

sealed class CountrySearchState{
    object Loading: CountrySearchState()
    data class ScreenData(val screenData: List<Countries>) : CountrySearchState()
    data class Error(val exception: Throwable? = null) : CountrySearchState()
}
