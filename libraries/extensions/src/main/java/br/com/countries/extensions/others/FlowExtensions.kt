package br.com.countries.extensions.others

import br.com.countries.network.event.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

suspend fun <T> executeFlow(
    getRepository:suspend (() -> T)
):Flow<Event<T>> = flow {
    emit(Event.Loading)
    val data = getRepository()
    emit(Event.Data(data))
}.catch {
    val exception = Throwable(it.cause)
    emit(Event.Error(exception))
}