package com.example.pictureApp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Component
class UserRowMapper : RowMapper<User> {
    override fun mapRow(rs: ResultSet, rowNum: Int): User {
        return  User(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            pass = rs.getString("pass"))
    }
}


@Repository
class UserRepository (@Autowired val jdbcTemplate: JdbcTemplate, @Autowired val userRowMapper: UserRowMapper){

    fun findAll(): List<User> {
        val users = jdbcTemplate.query("SELECT * FROM users", userRowMapper)
        return users
    }

    fun findById(pass: String): User? {
        val user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE pass = ?", userRowMapper, pass)
        return user
    }

    fun findByUsername(name: String): User? =
        jdbcTemplate.queryForObject("SELECT * FROM users WHERE name = ?", userRowMapper, name)


    fun save(user: User): Int =
        jdbcTemplate.update(
            "INSERT INTO users (name, pass) VALUES (?, ?)",
            user.name,  user.pass
        )


    fun deleteById(id: Long): Int =
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id)
}
