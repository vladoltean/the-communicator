package com.communicator.the.rest

import com.communicator.the.config.SocketHandler
import com.communicator.the.model.User
import com.communicator.the.model.WebSocketConnectionsStore
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("api/web-socket")
class WebSocketSessionsController @Autowired constructor(
        private val wsConnectionsStore: WebSocketConnectionsStore
) {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(SocketHandler::class.java)
    }

    @GetMapping("users")
    fun getUsersWithSessions(): List<User> {
        return wsConnectionsStore.getUsers()
                .map { userId -> User(userId) }
    }


}