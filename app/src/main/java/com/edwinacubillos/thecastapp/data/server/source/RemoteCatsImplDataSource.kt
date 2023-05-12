package com.edwinacubillos.thecastapp.data.server.source

import com.edwinacubillos.thecastapp.data.server.WrappedResponseHandler
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.data.source.RemoteCatsDataSource
import com.edwinacubillos.domain.remote.data.LocalCat
import com.edwinacubillos.framework.remote.api.MLService
import com.edwinacubillos.framework.remote.mappers.RemoteCatsMapper

class RemoteCatsImplDataSource (
    private val mlService: MLService,
    private val remoteCatsMapper: RemoteCatsMapper,
    private val wrappedResponseHandler: WrappedResponseHandler
) : RemoteCatsDataSource {

    override suspend fun getCats(apiKey: String): ResourceRemote<WrappedResponse<List<LocalCat>>> {
        return wrappedResponseHandler.handleSuccess(remoteCatsMapper.getCats(mlService.getCats(apiKey)))
    }
}
