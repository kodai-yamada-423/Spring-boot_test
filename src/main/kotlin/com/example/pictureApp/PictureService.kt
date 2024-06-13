package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import kotlin.jvm.Throws


@Service
class PictureService(@Autowired val pictureRepository: PictureRepository) {
    @Throws(IOException::class)
    fun savePicture(image: MultipartFile,title:String,user_id:Long){
        val picture = Picture( user_id = user_id, image = image.bytes, title = title)
        pictureRepository.save(picture)
    }
    fun getAllPictures(): List<Picture> = pictureRepository.findAll()
}