package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Component
class TitleRowMapper : RowMapper<Title> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Title {
        return  Title(
            id = rs.getLong("id"),
            topic = rs.getString("topic"),
        )}
}

@Repository
class TitleRepository(@Autowired val jdbcTemplate: JdbcTemplate, @Autowired val titleRowMapper: TitleRowMapper) {


    fun findAll(): List<Title> =
        jdbcTemplate.query("SELECT * FROM titles", titleRowMapper)

    fun findById(id: Long): Title? =
        jdbcTemplate.queryForObject("SELECT * FROM titles WHERE id = ?", titleRowMapper, id)

}