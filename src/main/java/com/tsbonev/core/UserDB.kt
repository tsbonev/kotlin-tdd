package com.tsbonev.core

interface UserDB {

    fun contains(user: User): Boolean
    fun add(user: User)

}