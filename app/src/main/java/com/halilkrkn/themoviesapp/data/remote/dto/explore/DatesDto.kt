package com.halilkrkn.themoviesapp.data.remote.dto.explore


import com.google.gson.annotations.SerializedName

data class DatesDto(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)