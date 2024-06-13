package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@RequestMapping("/api/titles")
class TitleController(@Autowired val titleRepository: TitleRepository) {

    @GetMapping("/random")
    fun getRandomTitle(): ResponseEntity<Title> {
        val titles = titleRepository.findAll()
        if (titles.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        val randomTitle = titles[Random.nextInt(titles.size)]
        return ResponseEntity.ok(randomTitle)
    }
}