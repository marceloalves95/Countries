package br.com.countries.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.countries.data.api.CountriesApi
import br.com.countries.data.mapper.toCountries
import br.com.countries.data.model.dummyCountriesResponse
import br.com.countries.data.models.CountriesResponse
import br.com.countries.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class CountriesRepositoryImplTest : BaseTest() {

    @RelaxedMockK
    private lateinit var repository: CountriesRepositoryImpl

    @RelaxedMockK
    private lateinit var service: CountriesApi

    @Before
    fun setup() {
        repository = CountriesRepositoryImpl(service)
    }


    @Test
    fun `should get countries when is called with success`() = runBlocking {

        //Arrange
        val response = Response.success(listOf(dummyCountriesResponse))

        coEvery {
            service.getCountries()
        } returns response

        //Act
        val result = repository.getCountries()

        //Assert
        assertThat(result).isEqualTo(listOf(dummyCountriesResponse.toCountries()))

        coVerify(exactly = 1) {
            service.getCountries()
        }

        confirmVerified(service)

    }

    @Test(expected = HttpException::class)
    fun `should get countries when is called with failure`() = runBlocking {

        //Arrange
        val responseError = Response.error<CountriesResponse>(
            500,
            "some content".toResponseBody("plain/text".toMediaTypeOrNull())
        )

        coEvery {
            service.getCountries()
        } throws HttpException(responseError)

        //Act
        repository.getCountries()

        //Assert
        coVerify(exactly = 1) {
            service.getCountries()
        }

        confirmVerified(service)
    }

    @Test
    fun `should get search name country when is called with success`() = runBlocking {

        //Arrange
        val response = Response.success(listOf(dummyCountriesResponse))

        coEvery {
            service.getCountriesSearch(COUNTRY)
        } returns response

        //Act
        val result = repository.getCountriesSearchName(COUNTRY)

        //Assert
        assertThat(result).isEqualTo(listOf(dummyCountriesResponse.toCountries()))

        coVerify(exactly = 1) {
            service.getCountriesSearch(COUNTRY)
        }

        confirmVerified(service)

    }

    @Test(expected = HttpException::class)
    fun `should get search name country when is called with failure`() = runBlocking {

        //Arrange
        val responseError = Response.error<CountriesResponse>(
            500,
            "some content".toResponseBody("plain/text".toMediaTypeOrNull())
        )

        coEvery {
            service.getCountriesSearch(COUNTRY)
        } throws HttpException(responseError)

        //Act
        repository.getCountriesSearchName(COUNTRY)

        //Assert
        coVerify(exactly = 1) {
            service.getCountriesSearch(COUNTRY)
        }

        confirmVerified(service)
    }

    companion object {
        private const val COUNTRY = "brazil"
    }
}