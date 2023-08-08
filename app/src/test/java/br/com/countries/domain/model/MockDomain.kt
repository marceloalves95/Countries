package br.com.countries.domain.model

import br.com.countries.domain.models.Countries
import br.com.countries.domain.models.CountriesFlags
import br.com.countries.domain.models.CountriesMaps
import br.com.countries.domain.models.CountriesName

val dummyCountriesFlags = CountriesFlags(
    png = "https://flagcdn.com/w320/br.png",
    svg = "https://flagcdn.com/br.svg"
)
val dummyCountriesMaps = CountriesMaps(
    googleMaps = "https://goo.gl/maps/waCKk21HeeqFzkNC9",
    openStreetMaps = "https://www.openstreetmap.org/relation/59470"
)
val dummyCountriesName = CountriesName(
    common = "Brazil",
    official = "Federative Republic of Brazil"
)
val dummyCountries = Countries(
    name = dummyCountriesName,
    capital = listOf("Brasília"),
    region = "Americas",
    latitude = listOf(-10.0, -55.0),
    maps = dummyCountriesMaps,
    flags = dummyCountriesFlags,
    area = 8515767.0,
    population = 212559409
)