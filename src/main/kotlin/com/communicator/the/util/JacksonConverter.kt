package com.communicator.the.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

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

}