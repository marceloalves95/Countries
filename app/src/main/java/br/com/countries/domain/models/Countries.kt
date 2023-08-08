package br.com.countries.domain.models

data class Countries(
    val name:CountriesName?,
    val capital:List<String>?,
    val region:String?,
    val latitude:List<Double>?,
    val maps:CountriesMaps?,
    val flags:CountriesFlags?,
    val area:Double?,
    val population:Int?
)

data class CountriesName(
    val common:String?,
    val official:String?
)

data class CountriesMaps(
    val googleMaps:String?,
    val openStreetMaps:String?
)

data class CountriesFlags(
    val png:String?,
    val svg:String?
)