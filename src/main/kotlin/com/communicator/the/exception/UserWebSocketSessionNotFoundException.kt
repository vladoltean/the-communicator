package com.communicator.the.exception

import java.lang.Exception

class UserWebSocketSessionNotFoundException: Exception {

    constructor(message: String) : super(message)
}