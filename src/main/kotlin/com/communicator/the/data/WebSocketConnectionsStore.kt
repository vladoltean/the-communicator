package com.communicator.the.data

import com.communicator.the.exception.UserWebSocketSessionNotFoundException
import org.springframework.stereotype.Component

//TODO: Remove me
fun tempInit(): MutableMap<String, UserWebSocketSession> {
    val map = HashMap<String, UserWebSocketSession>();
    map.put("dummy-1", UserWebSocketSession("dummy-1", "dummy-1"))
    map.put("dummy-2", UserWebSocketSession("dummy-2", "dummy-2"))
    map.put("dummy-3", UserWebSocketSession("dummy-3", "dummy-3"))

    return map;
}


@Component
class WebSocketConnectionsStore {

    private val storeBySessionId: MutableMap<String, UserWebSocketSession> = tempInit()
    private val storeByUserId: MutableMap<String, UserWebSocketSession> = tempInit()

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