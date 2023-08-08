package br.com.countries.presentation.detail

import br.com.countries.databinding.ActivityCountriesDetailBinding
import br.com.countries.presentation.detail.model.CountriesModel
import com.bumptech.glide.Glide

class CountriesDetailLayoutContainer(
    private val binding: ActivityCountriesDetailBinding
) {
    fun setupCountries(countries: CountriesModel) = with(binding) {
        actvCountriesName.text = countries.common ?: ""
        actvCountriesCapital.text = countries.capital?.single() ?: ""
        actvCountriesRegion.text = countries.region ?: ""
        Glide.with(actvCountriesFlag.context)
            .load(countries.png)
            .into(actvCountriesFlag)
        actvCountryArea.text = countries.area.toString()
        actvCountryPopulation.text= countries.population.toString()
    }
}