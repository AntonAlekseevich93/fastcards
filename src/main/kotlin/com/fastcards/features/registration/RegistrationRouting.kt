package com.fastcards.features.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/register") {
            val registrationController = RegistrationController(call)
            registrationController.registerNewUser()
        }
    }
}