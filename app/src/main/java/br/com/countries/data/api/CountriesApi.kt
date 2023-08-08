package br.com.countries.data.api

import br.com.countries.data.models.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("all")
    suspend fun getCountries(): Response<List<CountriesResponse>>
    @GET("name/{name}")
    suspend fun getCountriesSearch(@Path("name") country:String?):Response<List<CountriesResponse>>
}