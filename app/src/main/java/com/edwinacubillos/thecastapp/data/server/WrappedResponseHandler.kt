package com.edwinacubillos.thecastapp.data.server

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.WrappedResponse

open class WrappedResponseHandler {
    fun <T : Any?> handleSuccess(data: WrappedResponse<T>): ResourceRemote<WrappedResponse<T>>{
        return ResourceRemote.Success(data.status, data)
    }
}
