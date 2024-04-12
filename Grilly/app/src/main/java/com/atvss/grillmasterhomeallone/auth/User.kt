package com.atvss.grillmasterhomeallone.auth

class User (
    val id:Int,
    val email:String,
    val name:String?
)
// represents data key (from json response)
class UserData(val data:User)