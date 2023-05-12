package com.edwinacubillos.data.source


import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.domain.remote.data.LocalCat

interface RemoteCatsDataSource {

    suspend fun getCats(apiKey: String): ResourceRemote<WrappedResponse<List<LocalCat>>>

}
