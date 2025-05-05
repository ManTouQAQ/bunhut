package me.mantou

import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import me.mantou.me.mantou.bunhut.dto.CreateCommentDto
import me.mantou.me.mantou.bunhut.model.ApiCodes
import me.mantou.me.mantou.bunhut.model.ApiResponse
import me.mantou.me.mantou.bunhut.model.CommentModel
import kotlin.test.Test
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun testCommentsService() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        run {
            val response = client.get("/comments")
            val result = response.body<ApiResponse<List<CommentModel>>>()
            assertTrue { result.data?.isEmpty() ?: true }
        }

        run {
            val response = client.post("/comments"){
                header(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json
                )
                setBody(CreateCommentDto("ManTou", "test"))
            }

            val result = response.body<ApiResponse<Unit>>()
            assertTrue { result.code == ApiCodes.SUCCESS }
        }

        run {
            val response = client.get("/comments")
            val result = response.body<ApiResponse<List<CommentModel>>>()
            assertTrue { result.data?.isNotEmpty() ?: false }
            assertTrue { result.data!!.first().author == "ManTou" }
        }
    }
}