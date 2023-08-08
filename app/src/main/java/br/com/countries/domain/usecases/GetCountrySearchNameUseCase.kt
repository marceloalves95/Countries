package br.com.countries.domain.usecases

import br.com.countries.domain.repository.CountriesRepository
import br.com.countries.extensions.others.executeFlow

class GetCountrySearchNameUseCase(
    private val repository: CountriesRepository
) {
    suspend operator fun invoke(
        country:String?
    ) = executeFlow(
        getRepository = {
            repository.getCountriesSearchName(country)
        }
    )
}