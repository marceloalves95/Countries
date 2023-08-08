package br.com.countries.data

import br.com.countries.data.api.CountriesApi
import br.com.countries.data.mapper.toCountries
import br.com.countries.domain.models.Countries
import br.com.countries.domain.repository.CountriesRepository
import br.com.countries.extensions.network.parseResponse
import br.com.countries.extensions.network.toResponse

class CountriesRepositoryImpl(
    private val api: CountriesApi
) : CountriesRepository {
    override suspend fun getCountries(): List<Countries> {
        return api.getCountries().parseResponse().toResponse().map { it.toCountries() }
    }

    override suspend fun getCountriesSearchName(country:String?): List<Countries> {
        return api.getCountriesSearch(country).parseResponse().toResponse().map { it.toCountries() }
    }
}