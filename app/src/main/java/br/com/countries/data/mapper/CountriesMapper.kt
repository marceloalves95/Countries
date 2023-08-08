package br.com.countries.data.mapper

import br.com.countries.data.models.CountriesFlagsResponse
import br.com.countries.data.models.CountriesMapsResponse
import br.com.countries.data.models.CountriesNameResponse
import br.com.countries.data.models.CountriesResponse
import br.com.countries.domain.models.Countries
import br.com.countries.domain.models.CountriesFlags
import br.com.countries.domain.models.CountriesMaps
import br.com.countries.domain.models.CountriesName

internal fun CountriesResponse.toCountries() = Countries(
    name = name?.toCountriesName(),
    capital = capital,
    region = region,
    latitude = latitude,
    maps = maps?.toCountriesMaps(),
    flags = flags?.toCountriesFlags(),
    area = area,
    population = population
)

internal fun CountriesNameResponse.toCountriesName() = CountriesName(
    common = common, official = official
)

internal fun CountriesMapsResponse.toCountriesMaps() = CountriesMaps(
    googleMaps = googleMaps, openStreetMaps = openStreetMaps
)

internal fun CountriesFlagsResponse.toCountriesFlags() = CountriesFlags(
    png = png, svg = svg
)