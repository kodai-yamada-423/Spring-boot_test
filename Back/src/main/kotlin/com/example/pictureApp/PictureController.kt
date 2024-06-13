package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/pictures")
class PictureController(@Autowired val pictureRepository: PictureRepository, @Autowired val pictureService: PictureService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun savePicture(

        @RequestParam("image") image: MultipartFile,
                    @RequestParam("title") title: String,
                    @RequestParam("user_id") user_id: Long
    ): ResponseEntity<String> {
        val savedPicture = pictureService.savePicture(image, title, user_id)
        return ResponseEntity.ok("保存した")
    }

    @GetMapping
    fun getAllPictures(): List<Picture> = pictureService.getAllPictures()

    @GetMapping("/{id}")
    fun getPictureById(@PathVariable id: Long): ResponseEntity<Picture> =
        pictureRepository.findById(id)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deletePicture(@PathVariable id: Long): ResponseEntity<Void> =
        if (pictureRepository.deleteById(id) > 0) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
}
