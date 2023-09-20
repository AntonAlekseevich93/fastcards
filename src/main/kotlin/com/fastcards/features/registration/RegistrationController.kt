package com.fastcards.features.registration

import com.fastcards.cache.InMemoryCache
import com.fastcards.cache.TokenCache
import com.fastcards.database.tokens.TokenDto
import com.fastcards.database.tokens.Tokens
import com.fastcards.database.users.UserDto
import com.fastcards.database.users.Users
import com.fastcards.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegistrationController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registrationReceiveRemote = call.receive<RegistrationReceiveRemote>()
        if (!registrationReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, message = "Email is not valid")
        }

        val userDto = Users.fetchUser(registrationReceiveRemote.login)
        if (userDto != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDto(
                        login = registrationReceiveRemote.login,
                        email = registrationReceiveRemote.email,
                        password = registrationReceiveRemote.password,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            Tokens.insert(
                TokenDto(
                    id = UUID.randomUUID().toString(),
                    login = registrationReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegistrationResponseRemote(token = token))
        }
    }
}