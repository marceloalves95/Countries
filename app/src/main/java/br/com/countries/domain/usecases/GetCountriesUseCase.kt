package br.com.countries.domain.usecases

import br.com.countries.domain.repository.CountriesRepository
import br.com.countries.extensions.others.executeFlow

class GetCountriesUseCase(
    private val repository: CountriesRepository
) {
    suspend operator fun invoke() = executeFlow(
        getRepository = {
            repository.getCountries()
        }
    )
}