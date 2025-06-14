package com.ab.sclr.data.overlays

import retrofit2.http.GET

interface OverlaysEndpoint {

    @GET("overlays")
    suspend fun listOverlays(): List<OverlayCategory>

    companion object {
        const val ENDPOINT = "https://appostropheanalytics.herokuapp.com/scrl/test/"
    }
}