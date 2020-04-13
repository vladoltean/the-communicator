package com.communicator.the.config

import com.communicator.the.data.UserWebSocketSession
import com.communicator.the.data.WebSocketConnectionsStore
import com.communicator.the.data.WebSocketMessage
import com.communicator.the.util.JacksonConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class SocketHandler(webSocketConnectionsStore: WebSocketConnectionsStore) : TextWebSocketHandler() {

    val webSocketConnectionsStore: WebSocketConnectionsStore = webSocketConnectionsStore;

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(SocketHandler::class.java)
    }

    private val sessionsMap: ConcurrentMap<String, WebSocketSession> = ConcurrentHashMap();


    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val wsMessage: WebSocketMessage = JacksonConverter.toObject(message.payload, WebSocketMessage::class.java);

        if (wsMessage.event.equals(EventType.IDENTIFY.eventName)) {
            handleInit(session.id, wsMessage)
            return
        }

        if(wsMessage.recipientId.isNullOrEmpty()){
            LOG.debug("Recipient id is empty. Message:%n ${message.payload}")
            return
        }
        //find recipient
        val recipientSessionId = webSocketConnectionsStore.getByUserId(wsMessage.recipientId).sessionId
        if(session.id == recipientSessionId){
            LOG.debug("Sender and Recipient session ids are matching:%n sender.sessionId=${session.id}%nrecipientSessionid=$recipientSessionId%n")
            return;
        }

        //TODO: Check for null
        val recipientSession: WebSocketSession = sessionsMap[recipientSessionId]!!;

        if (recipientSession.isOpen) {
            LOG.info("Sending message to ${recipientSession.id}: \n Message: ${message.payload}")
            recipientSession.sendMessage(message)
        }


//        for (webSocketSession in sessionsMap.values) {
//            if (webSocketSession.isOpen && session.id != webSocketSession.id) {
//
//                LOG.info("Sending message to ${session.id}: \n Message: ${message.payload}")
//                webSocketSession.sendMessage(message)
//            }
//        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionsMap.put(session.id, session);
    }

    fun handleInit(sessionId: String, wsMessage: WebSocketMessage) {
        LOG.info("Handling init event:%n ${JacksonConverter.toJson(wsMessage)}");
        webSocketConnectionsStore.add(UserWebSocketSession(sessionId, wsMessage.senderId));
    }

    enum class EventType(val eventName: String) {
        IDENTIFY("identify"),
        OFFER("offer"),
        ANSWER("answer"),
        CANDIDATE("candidate")
    }


}
