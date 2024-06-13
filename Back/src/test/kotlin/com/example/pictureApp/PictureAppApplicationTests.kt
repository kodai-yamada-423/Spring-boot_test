package com.example.pictureApp

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class PictureAppApplicationTests(@Autowired val restTemplate: TestRestTemplate,
								 @LocalServerPort val port: Int) {


	@Test
	fun contextLoads() {
	}

	@Test
	fun `最初のテスト`() {
		assertThat(1+2, equalTo(3))
	}


	@Test
	fun `GETリクエストはOKステータスを返す`() {
		// localhost/users に GETリクエストを発行する。
		val response = restTemplate.getForEntity("http://localhost:$port/api/users", List::class.java)
		// レスポンスのステータスコードは OK である。
		assertThat(response.statusCode, equalTo(HttpStatus.OK))
	}

	@Test
	fun `指定したユーザーIDを返す`() {
		// localhost/users に GETリクエストを発行する。
		val response = restTemplate.getForEntity("http://localhost:$port/api/users/1", User::class.java)
		// レスポンスのステータスコードは OK である。
		val user = response.body!!
		assertThat(user.name, equalTo("ymd"))
		assertThat(user.pass, equalTo("423"))
	}

	@Test
	fun `POSTリクエストはuserオブジェクトを格納する`() {
		// localhost/todos に GETリクエストを送り、レスポンスを Todoオブジェクトの配列として解釈する。
		val response = restTemplate.getForEntity("http://localhost:$port/api/users", List::class.java)
		// このときのレスポンスを todos1 として記憶。
		val todos1 = response.body!!
		// localhost/todos に POSTリクエストを送る。このときのボディは {"text": "hello"}
		val request = UserRequest("taro","1234")
		restTemplate.postForEntity("http://localhost:$port/api/users", request, String::class.java)
		// ふたたび localhost/todos に GETリクエストを送り、レスポンスを Todoオブジェクトの配列として解釈する。
		val response2 = restTemplate.getForEntity("http://localhost:$port/api/users", List::class.java)
		// このときのレスポンスを todos2 として記憶。
		val todos2 = response2.body!!
		// 配列 todos2 は、配列 todos1 よりも 1 要素だけ多い。
		assertThat(todos2.size, equalTo(todos1.size + 1))
		// 配列 todos2 には "hello" をもつTodoオブジェクトが含まれている。
	}

//	@Test
//	fun `POSTリクエストはpictureオブジェクトを格納する`() {
//		// localhost/todos に GETリクエストを送り、レスポンスを Todoオブジェクトの配列として解釈する。
//		val response = restTemplate.getForEntity("http://localhost:$port/api/pictures", List::class.java)
//		// このときのレスポンスを todos1 として記憶。
//		val todos1 = response.body!!
//		// localhost/todos に POSTリクエストを送る。このときのボディは {"text": "hello"}
//		val request = PicturesRequest(
//			"",
//			"ピカチュウ",
//			1)
//		restTemplate.postForEntity("http://localhost:$port/api/pictures", request, String::class.java)
//		// ふたたび localhost/todos に GETリクエストを送り、レスポンスを Todoオブジェクトの配列として解釈する。
//		val response2 = restTemplate.getForEntity("http://localhost:$port/api/pictures", List::class.java)
//		// このときのレスポンスを todos2 として記憶。
//		val todos2 = response2.body!!
//		// 配列 todos2 は、配列 todos1 よりも 1 要素だけ多い。
//		assertThat(todos2.size, equalTo(todos1.size + 1))
//		// 配列 todos2 には "hello" をもつTodoオブジェクトが含まれている。
//	}
//
//	@Test
//	fun `絵の削除`(){
//		restTemplate.delete("http://localhost:$port/api/pictures/22")
//		val response = restTemplate.getForEntity("http://localhost:$port/api/pictures", List::class.java)
//		val todos = response.body!!
//		assertThat(todos.size, equalTo(0))
//	}


}

