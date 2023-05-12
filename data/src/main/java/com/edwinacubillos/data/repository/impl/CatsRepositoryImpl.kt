package com.edwinacubillos.data.repository.impl

import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.data.repository.CatsRepository
import com.edwinacubillos.data.source.RemoteCatsDataSource
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.data.LocalCat

class CatsRepositoryImpl(
    private var remoteCatsDataSource: RemoteCatsDataSource,
    private val apiKey: String,
) : CatsRepository {

    override suspend fun getCats(): ResourceRemote<WrappedResponse<List<LocalCat>>> =
        remoteCatsDataSource.getCats(apiKey)

}
