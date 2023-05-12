package com.edwinacubillos.data.repository

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.domain.remote.data.LocalCat

interface CatsRepository {

    suspend fun getCats(): ResourceRemote<WrappedResponse<List<LocalCat>>>
}
