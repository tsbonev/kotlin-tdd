package com.tsbonev.core

class ValidatorImpl(private val validation: (User) -> Boolean) : Validator {
    override fun validate(user: User): Boolean {
        return validation(user)
    }
}