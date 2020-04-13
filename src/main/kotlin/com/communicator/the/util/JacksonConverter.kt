package com.communicator.the.util

import com.communicator.the.data.WebSocketMessage
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object JacksonConverter {

    val OBJECT_MAPPER: ObjectMapper = init();

    private fun init():ObjectMapper {
        val om = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return om
    }

    fun <T> toObject(input: String, klazz: Class<T>): T {
        return OBJECT_MAPPER.readValue(input, klazz);
    }

    fun toJson(input: Any): String {
        return OBJECT_MAPPER.writeValueAsString(input)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val message = toObject("{\"event\":\"candidate\",\"data\":{\"candidate\":\"candidate:3926511315 1 udp 2122260223 192.168.0.38 52591 typ host generation 0 ufrag JNCK network-id 1 network-cost 10\",\"sdpMid\":\"2\",\"sdpMLineIndex\":2}}", WebSocketMessage::class.java)
        println(message);
    }
}