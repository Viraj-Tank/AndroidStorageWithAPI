package com.novuspax.androidstoragewithapi.network.retrofit

import com.novuspax.androidstoragewithapi.network.data.RAMResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
    suspend fun getRickAndMortyResponse(
        @Query("page") page: String
    ): Response<RAMResponse>

}