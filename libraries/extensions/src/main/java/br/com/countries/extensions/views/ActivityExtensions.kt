package br.com.countries.extensions.views

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.io.Serializable

inline fun <reified T> AppCompatActivity.observe(
    liveData: LiveData<T>,
    noinline lifecycle:(T) -> Unit){
    liveData.observe(this, Observer(lifecycle))
}

inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else throw IllegalArgumentException("didn't find extra with key $key")
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun AppCompatActivity.onBackButtonPressed(){
    onBackPressedDispatcher.onBackPressed()
}