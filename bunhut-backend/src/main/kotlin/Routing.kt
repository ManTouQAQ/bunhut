package me.mantou

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.mantou.me.mantou.bunhut.dto.CreateCommentDto
import me.mantou.me.mantou.bunhut.model.wrapResponse
import me.mantou.me.mantou.bunhut.model.wrapSuccess
import me.mantou.me.mantou.bunhut.service.CommentsService

fun Application.configureRouting() {
    routing {
        route("/comments") {
            get {
                call.respond(CommentsService.getComments().wrapSuccess())
            }
            post {
                try {
                    val commentDto = call.receive<CreateCommentDto>()
                    call.respond(
                        CommentsService.addComment(commentDto).wrapResponse()
                    )
                }catch (e: Exception){
                    println(e.message)
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}