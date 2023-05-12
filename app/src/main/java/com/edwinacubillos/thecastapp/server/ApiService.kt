package com.edwinacubillos.thecastapp.server

import com.edwinacubillos.thecastapp.server.model.CatList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("breeds")
    suspend fun getCats(@Query("x-api-key") apiKey: String) : CatList

}
