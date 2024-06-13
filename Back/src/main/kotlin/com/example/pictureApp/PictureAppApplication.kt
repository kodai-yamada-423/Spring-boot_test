package com.example.pictureApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PictureAppApplication

fun main(args: Array<String>) {
	runApplication<PictureAppApplication>(*args)
}
