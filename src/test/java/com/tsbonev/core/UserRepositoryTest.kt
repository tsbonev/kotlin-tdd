package com.tsbonev.core

import org.junit.Test

class UserRepositoryTest{


    val validator: Validator = Validator()
    val user: UserDB = UserDB()
    val repo: UserRepository = UserRepository(user, validator)

    @Test
    fun shouldRegisterUser(){



    }

}