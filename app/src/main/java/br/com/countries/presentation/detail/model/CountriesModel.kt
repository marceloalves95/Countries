package br.com.countries.presentation.detail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountriesModel(
    val common: String?,
    val capital: List<String>?,
    val region: String?,
    val latitude: List<Double>?,
    val googleMaps: String?,
    val png: String?,
    val area: Double?,
    val population:Int?
) : Parcelable