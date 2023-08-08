package br.com.countries.core.di

import br.com.countries.data.BASE_URL
import br.com.countries.data.CountriesRepositoryImpl
import br.com.countries.data.api.CountriesApi
import br.com.countries.domain.repository.CountriesRepository
import br.com.countries.domain.usecases.GetCountriesUseCase
import br.com.countries.domain.usecases.GetCountrySearchNameUseCase
import br.com.countries.network.service.Service
import br.com.countries.presentation.CountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.module.Module
import org.koin.dsl.module

object CountriesModule {

    fun load() {
        GlobalContext.loadKoinModules(
            listOf(
                dataModule(),
                domainModule(),
                presentationModule()
            )
        )
    }

    private fun dataModule(): Module = module {
        factory<CountriesApi> {
            Service.createService(
                baseUrl = BASE_URL
            )
        }
        single<CountriesRepository> {
            CountriesRepositoryImpl(api = get())
        }
    }

    private fun domainModule(): Module = module {
        factory {
            GetCountriesUseCase(repository = get())
        }
        factory {
            GetCountrySearchNameUseCase(repository = get())
        }
    }

    private fun presentationModule(): Module = module {
        viewModel {
            CountriesViewModel(
                getCountriesUseCase = get(),
                getCountrySearchNameUseCase = get()
            )
        }
    }
}