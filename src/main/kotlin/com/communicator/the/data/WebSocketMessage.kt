package com.communicator.the.data

class WebSocketMessage (
        val event: String,
        val senderId: String,
        val recipientId: String?
)