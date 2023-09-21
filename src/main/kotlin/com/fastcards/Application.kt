package com.fastcards

import com.fastcards.features.login.configureLoginRouting
import com.fastcards.features.registration.configureRegistrationRouting
import com.fastcards.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}
//fun main() {
//
////    Database.connect(
////        url = "jdbc:postgresql://localhost:5432/fastcards",
////        driver = "org.postgresql.Driver",
////        password = "vvfav52p",
////        user = "postgres"
////    )
//    embeddedServer(CIO, port = (System.getenv("PORT")?:"8080").toInt()){
//        configureRouting()
//        configureRoutingT()
////        configureLoginRouting()
////        configureRegistrationRouting()
////        configureSerialization()
//    }
//        .start(wait = true)
//}

