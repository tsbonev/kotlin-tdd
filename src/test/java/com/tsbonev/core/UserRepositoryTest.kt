package com.tsbonev.core

import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert.assertThat

class UserRepositoryTest{


    val validator: Validator = Validator { u -> Integer.parseInt(u.age) in 10..100}
    val userDB: UserDB = UserDB()
    val repo: UserRepository = UserRepository(userDB, validator)

    @Test
    fun shouldRegisterUser(){

        val user = User("John", "18")
        repo.registerUser(user)
        assertThat(userDB.contains(user), Is(true))

    }

    @Test
    fun shouldDenyUserRegistration(){

        val user = User("John", "180")
        repo.registerUser(user)
        assertThat(userDB.contains(user), Is(false))

    }

    @Test
    fun registeredUserIsAdult(){

        val user = User("John", "19")
        repo.registerUser(user)
        assertThat(repo.isAdult(user), Is(true))

    }

    @Test
    fun registeredUserIsNotAdult(){
        val user = User("John", "17")
        repo.registerUser(user)
        assertThat(repo.isAdult(user), Is(false))
    }

}