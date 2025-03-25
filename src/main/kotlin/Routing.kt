package umfg

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import umfg.application.payloads.CreateUserPayload
import umfg.application.responses.UserCreatedResponse
import umfg.infra.repository.UserRepository

fun Application.configureRouting() {
    routing {
        val repository = UserRepository()
        get("/") {
            call.respondText("Hello World!")
        }
        post("/users") {
            val payload = call.receive<CreateUserPayload>()
            val id = repository.create(payload)
            val response = UserCreatedResponse(id)
            call.respond(HttpStatusCode.Created, response)
        }
        get("/users/{id}") {
            val id: Int = call.parameters["id"]?.toInt()
                ?: throw IllegalArgumentException("ID deve ser informado!")

            val user = repository.findById(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/get/users") {
            val user = repository.findAll()
            if(user.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NoContent)
            }
        }

        put("/put/users/{id}") {
            val id: Int = call.parameters["id"]?.toInt()
                ?: throw IllegalArgumentException("ID deve ser informado!")

            val existUser = repository.findById(id)
            if (existUser == null) {
                call.respond(HttpStatusCode.NotFound)
                return@put
            }

            val user = call.receive<CreateUserPayload>()
            repository.updateById(id, user)

            val entity = repository.findById(id)
            print(entity)
            if (entity != null) {
                call.respond(HttpStatusCode.OK, entity)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Erro ao atualizar usuário.")
            }
        }

        delete("delete/users/{id}") {
            val id: Int = call.parameters["id"]?.toInt()
                ?: throw IllegalArgumentException("Id deve ser informado!")

            val user = repository.delete(id)
        }
    }
}
