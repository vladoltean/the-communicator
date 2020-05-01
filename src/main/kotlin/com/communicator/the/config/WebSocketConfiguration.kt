package com.communicator.the.config

import com.communicator.the.model.WebSocketConnectionsStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfiguration : WebSocketConfigurer {

    private val webSocketConnectionsStore: WebSocketConnectionsStore;

    @Autowired
    constructor(wsConnectionsStore: WebSocketConnectionsStore){
        this.webSocketConnectionsStore = wsConnectionsStore;
    }

    override fun registerWebSocketHandlers(register: WebSocketHandlerRegistry) {
        register.addHandler(SocketHandler(this.webSocketConnectionsStore), "/socket")
    }
}
