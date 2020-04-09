package com.communicator.the.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun helloEndpoint(@RequestParam(name = "name", required = false) nameParam: String): String {
        val name: String = if (nameParam.isEmpty()) "zame" else nameParam;
        return "Hoi $name";
    }

}
