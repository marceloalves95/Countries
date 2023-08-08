package br.com.countries.network.service

import br.com.countries.network.model.ServiceState
import kotlin.reflect.KClass

interface NetworkingApi {

    @Throws(Throwable::class)
    suspend fun <T:Any> get(
        url:String,
        responseKClass: KClass<T>
    ): ServiceState<T>

    @Throws(Throwable::class)
    suspend fun <T:Any> post(
        url:String,
        body:Any,
        responseKClass: KClass<T>
    ): ServiceState<T>

    @Throws(Throwable::class)
    suspend fun <T:Any> put(
        url:String,
        body:Any,
        responseKClass: KClass<T>
    ): ServiceState<T>

    @Throws(Throwable::class)
    suspend fun <T:Any> delete(
        url:String,
        responseKClass: KClass<T>
    ): ServiceState<T>
}