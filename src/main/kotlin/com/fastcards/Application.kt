package com.fastcards

import com.fastcards.features.login.configureLoginRouting
import com.fastcards.features.registration.configureRegistrationRouting
import com.fastcards.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/fastcards",
        driver = "org.postgresql.Driver",
        password = "vvfav52p",
        user = "postgres"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegistrationRouting()
    configureSerialization()
}
