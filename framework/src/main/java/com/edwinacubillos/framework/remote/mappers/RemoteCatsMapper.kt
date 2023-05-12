package com.edwinacubillos.framework.remote.mappers

import com.edwinacubillos.domain.remote.Status
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.domain.remote.data.LocalCat
import com.edwinacubillos.framework.remote.domain.CatList

class RemoteCatsMapper {

    fun getCats(catList: CatList): WrappedResponse<List<LocalCat>> {
        return WrappedResponse(
            status = Status.Success,
            data = catList.map {
                LocalCat(
                    name = it.name,
                    origin = it.origin,
                    afflectionLevel = it.affection_level,
                    intelligence = it.intelligence,
                    imageUrl = "https://cdn2.thecatapi.com/images/" + it.reference_image_id + ".jpg"
                )
            },
            error = null
        )
    }
}