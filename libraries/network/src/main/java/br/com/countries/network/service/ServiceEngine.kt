package br.com.countries.network.service

import br.com.countries.network.model.RequestMethod
import br.com.countries.network.model.ServiceState
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.reflect.KClass

class ServiceEngine(
    private val requestMethodService: RequestMethodService
) : NetworkingApi {

    private val gson = Gson()

    override suspend fun <T : Any> get(
        url: String,
        responseKClass: KClass<T>
    ): ServiceState<T> {
        return callRequest(
            url = url,
            requestMethod = RequestMethod.GET,
            responseKClass = responseKClass
        ).run {
            when (this) {
                is ServiceState.Success -> ServiceState.Success(data)
                is ServiceState.Error -> ServiceState.Error(
                    response = response,
                    exception = exception
                )
            }
        }
    }

    override suspend fun <T : Any> post(
        url: String,
        body: Any,
        responseKClass: KClass<T>
    ): ServiceState<T> {
        return callRequest(
            url = url,
            requestMethod = RequestMethod.POST,
            responseKClass = responseKClass
        ).run {
            when (this) {
                is ServiceState.Success -> ServiceState.Success(data)
                is ServiceState.Error -> ServiceState.Error(
                    response = response,
                    exception = exception
                )
            }
        }
    }

    override suspend fun <T : Any> put(
        url: String,
        body: Any,
        responseKClass: KClass<T>
    ): ServiceState<T> {
        return callRequest(
            url = url,
            requestMethod = RequestMethod.PUT,
            responseKClass = responseKClass
        ).run {
            when (this) {
                is ServiceState.Success -> ServiceState.Success(data)
                is ServiceState.Error -> ServiceState.Error(
                    response = response,
                    exception = exception
                )
            }
        }
    }

    override suspend fun <T : Any> delete(
        url: String,
        responseKClass: KClass<T>
    ): ServiceState<T> {
        return callRequest(
            url = url,
            requestMethod = RequestMethod.DELETE,
            responseKClass = responseKClass
        ).run {
            when (this) {
                is ServiceState.Success -> ServiceState.Success(data)
                is ServiceState.Error -> ServiceState.Error(
                    response = response,
                    exception = exception
                )
            }
        }
    }

    private suspend fun <T : Any> callRequest(
        url: String,
        body: Any? = null,
        requestMethod: RequestMethod,
        headers: List<Pair<String, String>>? = null,
        responseKClass: KClass<T>
    ): ServiceState<T> {

        val headersMap = hashMapOf<String, String>()

        headers?.forEach {
            headersMap[it.first] = it.second
        }

        return load {
            convertResponse(
                when (requestMethod) {
                    RequestMethod.GET -> requestMethodService.getRequest(
                        url = url,
                        headers = headersMap
                    )
                    RequestMethod.POST -> requestMethodService.postRequest(
                        url = url,
                        body = getRequestBody(body),
                        headers = headersMap
                    )
                    RequestMethod.PUT -> requestMethodService.putRequest(
                        url = url,
                        body = getRequestBody(body),
                        headers = headersMap
                    )
                    RequestMethod.DELETE -> requestMethodService.deleteRequest(
                        url = url,
                        body = getRequestBody(body),
                        headers = headersMap
                    )
                }.string(),
                responseClazz = responseKClass.java
            )
        }
    }

    private fun <T> convertResponse(json: String?, responseClazz: Class<T>): T =
        gson.fromJson(json, responseClazz)

    private fun getRequestBody(body: Any? = null) =
        gson.toJson(body).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    private suspend fun <R : Any> load(block: suspend () -> R): ServiceState<R> {
        val response: R = block.invoke()
        return try {
            ServiceState.Success(response)
        } catch (error: Throwable) {
            error.printStackTrace()
            ServiceState.Error(exception = error, response = response)
        }
    }
}