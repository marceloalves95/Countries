package br.com.countries.core

import android.app.Application
import br.com.countries.core.di.CountriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@Application)
        }
        CountriesModule.load()
    }
}