package com.example.pictureApp

import org.springframework.web.multipart.MultipartFile

data class PicturesRequest(val image:ByteArray, val title:String, val user_id:Long)
