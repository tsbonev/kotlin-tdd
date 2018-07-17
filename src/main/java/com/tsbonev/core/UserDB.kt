package com.tsbonev.core

import java.util.*

class UserDB {

    val users: LinkedList<User> = LinkedList()

    fun contains(user: User): Boolean {

        return users.contains(user)

    }

    fun add(user: User, validator: Validator) {

        if(validator.validate(user)){
            users.add(user)
        }

    }

}
