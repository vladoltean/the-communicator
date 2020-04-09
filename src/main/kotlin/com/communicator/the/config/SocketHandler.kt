package com.communicator.the.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList

class SocketHandler : TextWebSocketHandler() {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(SocketHandler::class.java)
    }

    private val sessions: MutableList<WebSocketSession> = CopyOnWriteArrayList()


    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (webSocketSession in sessions) {
            if (webSocketSession.isOpen && session.id != webSocketSession.id) {

                LOG.info("Sending message to ${session.id}: \n Message: ${message.payload}")
                webSocketSession.sendMessage(message)
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }
}
