package com.psgpw.pickapp.data.models

data class User(
    val id :String? = null,
    val email :String,
    val name :String? = null,
    val profile_image   :String? = null,
) {
}

data class ChatUser(
    val name :String? = null,
    val message :String? = null,
) {
}