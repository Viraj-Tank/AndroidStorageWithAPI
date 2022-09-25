package com.novuspax.androidstoragewithapi.network.retrofit

import com.novuspax.androidstoragewithapi.network.data.RAMResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : ApiHelper {
    override suspend fun getRickAndMortyResponse(
        page: String
    ): Response<RAMResponse> = apiInterface.getRickAndMortyResponse(page)
}