package com.communicator.the

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheApplication

fun main(args: Array<String>) {
    runApplication<TheApplication>(*args)
}
