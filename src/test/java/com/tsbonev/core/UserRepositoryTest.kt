package com.tsbonev.core

import org.hamcrest.CoreMatchers.any
import org.jmock.AbstractExpectations.*
import org.jmock.Expectations
import org.jmock.Mockery
import org.jmock.integration.junit4.JUnitRuleMockery
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert.assertThat
import org.junit.Rule

class UserRepositoryTest{


    @Rule
    @JvmField
    val context: JUnitRuleMockery = JUnitRuleMockery()

    private fun Mockery.expecting(block: Expectations.() -> Unit){
            checking(Expectations().apply(block))
    }

    val validatorMock = context.mock(Validator::class.java)
    val userDbMock = context.mock(UserDB::class.java)


    val validator: ValidatorImpl = ValidatorImpl { u -> Integer.parseInt(u.age) in 10..100}

    val userDB: InMemoryUserDB = InMemoryUserDB()
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

    @Test
    fun customAgeForAdults(){

        val repo = UserRepository(userDB, validator, 16)
        val user = User("John", "16")
        repo.registerUser(user)
        assertThat(repo.isAdult(user), Is(true))

    }

    @Test
    fun mockRegistration(){

        val repo = UserRepository(userDbMock, validatorMock)
        val user = User("John", "18")


        context.expecting{
            oneOf(userDbMock).add(user)
            oneOf(validatorMock).validate(user)
            will(returnValue(true))
        }

        repo.registerUser(user)

    }

    @Test
    fun mockIsAdult(){

        val repo = UserRepository(userDbMock, validatorMock)
        val user = User("John", "18")

        context.expecting {
            oneOf(userDbMock).contains(user)
            will(returnValue(true))
        }

        repo.isAdult(user)

    }

}