package br.com.countries.network.di

import br.com.countries.network.retrofit.NAMED_RETROFIT
import br.com.countries.network.retrofit.buildRetrofit
import br.com.countries.network.service.NetworkingApi
import br.com.countries.network.service.RequestMethodService
import br.com.countries.network.service.ServiceEngine
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkModule {
    fun load(baseUrl: String) {
        loadKoinModules(
            module = module {
                single(named(NAMED_RETROFIT)) {
                    buildRetrofit(baseUrl = baseUrl)
                }
                factory {
                    get<Retrofit>(named(NAMED_RETROFIT)).create(RequestMethodService::class.java)
                }
                factory<NetworkingApi> {
                    ServiceEngine(get())
                }
            })
    }
}