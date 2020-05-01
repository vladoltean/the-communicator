package com.communicator.the.model

class WebSocketMessage (
        val event: String,
        val senderId: String,
        val recipientId: String?
)