package com.edwinacubillos.usescases.cats

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.data.repository.CatsRepository
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.domain.remote.data.LocalCat

class GetCatsUseCase(private val catsRepository: CatsRepository) {

    suspend operator fun invoke(): ResourceRemote<WrappedResponse<List<LocalCat>>> =
        catsRepository.getCats()

}
