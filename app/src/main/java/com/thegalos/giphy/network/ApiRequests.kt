package com.thegalos.giphy.network

import com.thegalos.giphy.data.GifItem
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Gal Reshef on 2/21/2022.
 */
interface ApiRequests {
    @GET("v1/gifs/search")
    fun getGif(@Query("q") q: String,
               @Query("api_key") apiKey: String?
    ): Call<GifItem>

}