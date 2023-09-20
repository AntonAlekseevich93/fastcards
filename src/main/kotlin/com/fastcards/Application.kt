package com.fastcards

import com.fastcards.features.login.configureLoginRouting
import com.fastcards.features.registration.configureRegistrationRouting
import com.fastcards.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {

//    Database.connect(
//        url = "jdbc:postgresql://localhost:5432/fastcards",
//        driver = "org.postgresql.Driver",
//        password = "vvfav52p",
//        user = "postgres"
//    )



    embeddedServer(Netty, port = (System.getenv("PORT")?:"8080").toInt()){
        configureRouting()
        configureRoutingT()
        configureLoginRouting()
        configureRegistrationRouting()
        configureSerialization()
    }
        .start(wait = true)
}

fun Application.module() {

}