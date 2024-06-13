package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Component
class PictureRowMapper : RowMapper<Picture> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Picture {
        return  Picture(
            id = rs.getLong("id"),
            image = rs.getBytes("image"),
            title = rs.getString("title"),
            create_time = rs.getTimestamp("create_time").toLocalDateTime(),
            user_id  = rs.getLong("user_id")
        )
    }
}


@Repository
class PictureRepository(@Autowired val jdbcTemplate: JdbcTemplate, @Autowired val pictureRowMapper: PictureRowMapper) {

    fun findAll(): List<Picture> =
        jdbcTemplate.query("SELECT * FROM pictures", pictureRowMapper)

    fun findById(id: Long): Picture? =
        jdbcTemplate.queryForObject("SELECT * FROM pictures WHERE id = ?", pictureRowMapper, id)

    fun findByUserId(user_id: Long): List<Picture> =
        jdbcTemplate.query("SELECT * FROM pictures WHERE user_id = ?", pictureRowMapper, user_id)

    fun save(picture: Picture): Int =
        jdbcTemplate.update(
            "INSERT INTO pictures (user_id, image, title, create_time) VALUES (?, ?, ?, ?)",
            picture.user_id, picture.image, picture.title, picture.create_time
        )

    fun deleteById(id: Long): Int =
        jdbcTemplate.update("DELETE FROM pictures WHERE id = ?", id)


}