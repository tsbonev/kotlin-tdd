package com.tsbonev.core

import java.util.*

class InMemoryUserDB : UserDB {

    private val users: LinkedList<User> = LinkedList()

    override fun contains(user: User): Boolean {

        return users.contains(user)

    }

    override fun add(user: User) {
            users.add(user)
    }

}
