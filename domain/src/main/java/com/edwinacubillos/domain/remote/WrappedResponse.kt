package com.edwinacubillos.domain.remote

import com.google.gson.annotations.SerializedName

data class WrappedResponse<D>(
    @SerializedName("status") val status: Status,
    @SerializedName("data") val data: D? = null,
    @SerializedName("error") val error: ErrorResponse? = null
)
