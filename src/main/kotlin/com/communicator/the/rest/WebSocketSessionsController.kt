package com.communicator.the.rest

import com.communicator.the.data.User
import com.communicator.the.data.WebSocketConnectionsStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/web-socket")
class WebSocketSessionsController @Autowired constructor(
        private val wsConnectionsStore: WebSocketConnectionsStore
) {


    @GetMapping("users")
    fun getUsersWithSessions(): List<User> {
        return wsConnectionsStore.getUsers()
                .map { userId -> User(userId) }
    }

}