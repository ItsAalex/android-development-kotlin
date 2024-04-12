package com.atvss.marvellist.data
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// This class can be serialized and converted into another type, and later it can be converted back.
class Hero: Serializable {
    var name: String

    // connecting key from json with our parameter. (camelCase) (This is anotation)
    @SerializedName("realname")
    // nullable object is defined by "?"
    var realName: String? = null

    @SerializedName("imageurl")
    var imageUrl: String? = null

    @SerializedName("createdby")
    var createdBy: String? = null

    var bio: String? = null

    // creating with ofline data.
    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, realName: String?, imageUrl: String?, createdBy: String?, bio: String?) {
        this.name = name
        this.realName = realName
        this.imageUrl = imageUrl
        this.createdBy = createdBy
        this.bio = bio
    }
}