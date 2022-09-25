package com.novuspax.androidstoragewithapi.network.retrofit.api

import com.novuspax.androidstoragewithapi.network.data.RAMResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
    fun getRickAndMortyResponse(
        @Query("page") page: String
    ): Response<RAMResponse>

}