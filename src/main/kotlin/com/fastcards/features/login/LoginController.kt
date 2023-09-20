package com.fastcards.features.login

import com.fastcards.cache.InMemoryCache
import com.fastcards.cache.TokenCache
import com.fastcards.database.tokens.TokenDto
import com.fastcards.database.tokens.Tokens
import com.fastcards.database.users.Users
import com.fastcards.features.registration.RegistrationResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()

        val userDto = Users.fetchUser(receive.login)

        if (userDto == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDto.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDto(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))

            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}