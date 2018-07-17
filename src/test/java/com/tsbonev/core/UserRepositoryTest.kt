package com.tsbonev.core

import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert.assertThat

class UserRepositoryTest{


    val validator: Validator = Validator()
    val userDB: UserDB = UserDB()
    val repo: UserRepository = UserRepository(userDB, validator)

    @Test
    fun shouldRegisterUser(){

        val user = User("John")
        repo.registerUser(user)
        assertThat(userDB.contains(user), Is(true))


    }

}