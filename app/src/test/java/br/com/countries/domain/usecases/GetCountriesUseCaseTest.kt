package br.com.countries.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.countries.domain.models.Countries
import br.com.countries.domain.repository.CountriesRepository
import br.com.countries.domain.model.dummyCountries
import br.com.countries.network.event.Event
import br.com.countries.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCountriesUseCaseTest : BaseTest() {

    @RelaxedMockK
    private lateinit var getCountriesUseCase: GetCountriesUseCase

    @MockK
    private lateinit var repository: CountriesRepository

    @Before
    fun setup() {
        getCountriesUseCase = GetCountriesUseCase(repository)
    }

    @Test
    fun `should get interactor when it is called with success`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<List<Countries>>> = mutableListOf()

        coEvery {
            repository.getCountries()
        } returns listOf(dummyCountries)

        //Act
        getCountriesUseCase.invoke().collect { event ->
            progressEmit.add(event)
        }

        //Assert
        assertThat(progressEmit).isEqualTo(
            mutableListOf(
                Event.Loading,
                Event.Data(listOf(dummyCountries))
            )
        )

        coVerify {
            repository.getCountries()
        }

        confirmVerified(repository)
    }

    @Test(expected = Throwable::class)
    fun `should get interactor when it is called with failure`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<List<Countries>>> = mutableListOf()
        val error = mockk<Throwable>(relaxed = true)

        coEvery {
            repository.getCountries()
        } throws error

        //Act
        getCountriesUseCase.invoke().collect { event ->
            progressEmit.add(event)
        }

        //Assert
        assertThat(progressEmit).isEqualTo(
            mutableListOf(
                Event.Loading,
                Event.Error(error)
            )
        )

        coVerify {
            repository.getCountries()
        }

        confirmVerified(repository)
    }

}