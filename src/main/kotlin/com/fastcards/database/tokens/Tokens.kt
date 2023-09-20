package com.fastcards.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 75)

    fun insert(tokenDto: TokenDto) {
        transaction {
            Tokens.insert {
                it[login] = tokenDto.login
                it[id] = tokenDto.id
                it[token] = tokenDto.token
            }
        }
    }

//    fun fetchToken(login: String): TokenDto {
//        val userModule = Tokens.select {
//            Tokens.login.eq(login)
//        }.single()
//        return UserDto(
//            login = userModule[Tokens.login],
//            password = userModule[Tokens.password],
//            username = userModule[Tokens.username],
//            email = userModule[Tokens.email],
//        )
//    }
}