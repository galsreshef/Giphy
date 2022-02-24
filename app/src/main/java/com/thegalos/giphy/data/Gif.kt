package com.thegalos.giphy.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Gal Reshef on 2/22/2022.
 */
class Gif : Serializable {
    var id: String? = null
    var fullSizeGif: String? = null
    var downsizedGif: String? = null
    var title: String? = null
    var type: String? = null
}