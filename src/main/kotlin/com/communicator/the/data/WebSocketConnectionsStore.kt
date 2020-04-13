package com.communicator.the.data

import com.communicator.the.exception.UserWebSocketSessionNotFoundException
import org.springframework.stereotype.Component

@Component
class WebSocketConnectionsStore {

    private val storeBySessionId: MutableMap<String, UserWebSocketSession> = HashMap();
    private val storeByUserId: MutableMap<String, UserWebSocketSession> = HashMap();

    fun add(userWSSession: UserWebSocketSession) {
        storeBySessionId.put(userWSSession.sessionId, userWSSession);
        storeByUserId.put(userWSSession.userId, userWSSession);
    }

    fun getByUserId(userId: String): UserWebSocketSession {
        val userWsSession = storeByUserId.get(userId);
        if (userWsSession == null) {
            throw UserWebSocketSessionNotFoundException("User WS session not found for user id $userId!");
        }
        return userWsSession;
    }

    fun getUsers(): List<String> {
        return storeByUserId.keys.toList();
    }

}