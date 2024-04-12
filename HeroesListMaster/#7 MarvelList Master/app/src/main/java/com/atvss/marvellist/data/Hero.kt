package com.atvss.marvellist.data
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// This class can be serialized and converted into another type, and later it can be converted back.
class Hero: Serializable {
    var name: String? = null
    var images: String? = null
    var personal: String? = null

    // created for test purpose
    constructor(name: String) {
        this.name = name
    }

    constructor(name: String?, images: String?, personal: String?) {
        this.name = name
        this.images = images
        this.personal = personal
    }
}