package com.polyglots.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PolyglotsApplication

fun main(args: Array<String>) {
	runApplication<PolyglotsApplication>(*args)
}
