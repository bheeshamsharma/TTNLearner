package com.psgpw.pickapp.data.models

import java.util.*

open class BaseRequest {
    var device_type: String? = null
    var type: String? = null
    var device_token: String? = null

    constructor(deviceType: String?, type: String?) {
        this.device_type = deviceType
        this.type = type
    }


    constructor(deviceType: String?, type: String?, deviceToken: String?) {
        this.device_type = deviceType
        this.type = type
        this.device_token = deviceToken
    }
}

class SignInRequest : BaseRequest {
    var name: String? = null
    var email: String? = null

    constructor(deviceType: String? = null, type: String? = null, name: String, email: String) : super(deviceType, type) {
        this.name = name
        this.email = email
    }
}

