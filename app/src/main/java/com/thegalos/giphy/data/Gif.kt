package com.thegalos.giphy.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Gal Reshef on 2/22/2022.
 */
class Gif : Serializable {
//    @SerializedName("id")
    var id: String? = null

//    @SerializedName("url")
    var fullSizeGif: String? = null

    var downsizedGif: String? = null
//    @SerializedName("title")
    var title: String? = null

//    @SerializedName("type")
    var type: String? = null
}