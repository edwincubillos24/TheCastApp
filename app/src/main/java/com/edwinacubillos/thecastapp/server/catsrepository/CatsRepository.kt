package com.edwinacubillos.thecastapp.server.catsrepository

import com.edwinacubillos.thecastapp.server.ApiCats

class CatsRepository {

    private val apiKey = "bda53789-d59e-46cd-9bc5-2936630fde39"

    suspend fun getCats() = ApiCats.retrofit.getCats(apiKey)
}
