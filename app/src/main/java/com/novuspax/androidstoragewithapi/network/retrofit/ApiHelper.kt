package com.novuspax.androidstoragewithapi.network.retrofit

import com.novuspax.androidstoragewithapi.network.data.RAMResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getRickAndMortyResponse(
        page: String
    ): Response<RAMResponse>
}