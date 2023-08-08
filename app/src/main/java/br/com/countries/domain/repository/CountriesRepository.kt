package br.com.countries.domain.repository

import br.com.countries.domain.models.Countries

interface CountriesRepository {
    suspend fun getCountries():List<Countries>
    suspend fun getCountriesSearchName(country:String?):List<Countries>
}