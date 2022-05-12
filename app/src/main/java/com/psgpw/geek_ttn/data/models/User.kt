package com.psgpw.pickapp.data.models

data class User(
    val id :String,
    val email :String,
    val username :String? = null,
    val access_token   :String? = null,
) {
}

data class ChatUser(
    val name :String? = null,
    val message :String? = null,
) {
}