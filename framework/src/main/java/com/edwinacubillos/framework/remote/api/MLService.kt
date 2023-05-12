package com.edwinacubillos.framework.remote.api

import com.edwinacubillos.framework.remote.domain.CatList
import retrofit2.http.GET
import retrofit2.http.Query

interface MLService {

    @GET("breeds")
    suspend fun getCats(@Query("x-api-key") apiKey: String): CatList

}
