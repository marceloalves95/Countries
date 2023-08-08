package br.com.countries.presentation

import br.com.countries.databinding.ActivityCountriesBinding
import br.com.countries.domain.models.Countries
import br.com.countries.extensions.views.gone
import br.com.countries.extensions.views.shimmerVisible
import br.com.countries.extensions.views.visible
import br.com.countries.presentation.adapter.CountriesAdapter
import br.com.countries.presentation.model.CountriesState
import br.com.countries.presentation.model.CountrySearchState

class CountriesLayoutContainer(
    private val binding: ActivityCountriesBinding,
    private val viewModel: CountriesViewModel
) {
    private var countriesAdapter = CountriesAdapter(emptyList())

    fun setState(state: CountriesState) {
        when (state) {
            is CountriesState.ScreenData -> handleScreenData(state.screenData)
            is CountriesState.Error -> handleErrorData(state.exception)
            else -> Unit
        }
        handleLoading(state)
    }

    private fun handleLoading(state: CountriesState) {
        binding.shimmerFrameLayout.shimmerVisible(state is CountriesState.Loading)
    }

    private fun handleScreenData(screenData: List<Countries>) {
        binding.frameLayout.gone()
        binding.rvCountries.apply {
            countriesAdapter = CountriesAdapter(screenData.sortedBy { it.name?.common })
            adapter = countriesAdapter
        }
    }

    private fun handleErrorData(error: Throwable?) {
        binding.frameLayout.visible()
        binding.ncevError.apply {
            title = error?.message
            onClick = { viewModel.loadCountries() }
        }
    }

    fun setSearchState(searchState: CountrySearchState){
        when (searchState) {
            is CountrySearchState.ScreenData -> handleSearchScreenData(searchState.screenData)
            is CountrySearchState.Error -> handleSearchErrorData(searchState.exception)
            else -> Unit
        }
    }

    private fun handleSearchScreenData(screenData: List<Countries>) {
        binding.rvCountries.apply {
            countriesAdapter = CountriesAdapter(screenData)
            adapter = countriesAdapter
        }
    }

    private fun handleSearchErrorData(error: Throwable?){
        println(error?.message)
    }
}