package br.com.countries.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountriesResponse(
    @SerializedName("name")
    val name:CountriesNameResponse?,
    @SerializedName("capital")
    val capital:List<String>?,
    @SerializedName("region")
    val region:String?,
    @SerializedName("latlng")
    val latitude:List<Double>?,
    @SerializedName("maps")
    val maps:CountriesMapsResponse?,
    @SerializedName("flags")
    val flags:CountriesFlagsResponse?,
    @SerializedName("area")
    val area:Double?,
    @SerializedName("population")
    val population:Int?
):Parcelable

@Parcelize
data class CountriesNameResponse(
    @SerializedName("common")
    val common:String?,
    @SerializedName("official")
    val official:String?
):Parcelable

@Parcelize
data class CountriesMapsResponse(
    @SerializedName("googleMaps")
    val googleMaps:String?,
    @SerializedName("openStreetMaps")
    val openStreetMaps:String?
):Parcelable

@Parcelize
data class CountriesFlagsResponse(
    @SerializedName("png")
    val png:String?,
    @SerializedName("svg")
    val svg:String?
):Parcelable
