package br.com.countries.network.service

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface RequestMethodService {

    @GET
    suspend fun getRequest(
        @Url url:String,
        @HeaderMap headers:Map<String, String>
    ):ResponseBody

    @POST
    suspend fun postRequest(
        @Url url:String,
        @Body body: RequestBody,
        @HeaderMap headers:Map<String, String>
    ):ResponseBody

    @PUT
    suspend fun putRequest(
        @Url url:String,
        @Body body: RequestBody,
        @HeaderMap headers:Map<String, String>
    ):ResponseBody

    @DELETE
    suspend fun deleteRequest(
        @Url url:String,
        @Body body: RequestBody,
        @HeaderMap headers:Map<String, String>
    ):ResponseBody
}