package br.com.countries.data.model

import br.com.countries.data.models.CountriesFlagsResponse
import br.com.countries.data.models.CountriesMapsResponse
import br.com.countries.data.models.CountriesNameResponse
import br.com.countries.data.models.CountriesResponse

val dummyCountriesFlagsResponse = CountriesFlagsResponse(
    png = "https://flagcdn.com/w320/br.png",
    svg = "https://flagcdn.com/br.svg"
)
val dummyCountriesMapsResponse = CountriesMapsResponse(
    googleMaps = "https://goo.gl/maps/waCKk21HeeqFzkNC9",
    openStreetMaps = "https://www.openstreetmap.org/relation/59470"
)
val dummyCountriesNameResponse = CountriesNameResponse(
    common = "Brazil",
    official = "Federative Republic of Brazil"
)
val dummyCountriesResponse = CountriesResponse(
    name = dummyCountriesNameResponse,
    capital = listOf("Brasília"),
    region = "Americas",
    latitude = listOf(-10.0, -55.0),
    maps = dummyCountriesMapsResponse,
    flags = dummyCountriesFlagsResponse,
    area = 8515767.0,
    population = 212559409
)