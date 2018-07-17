package com.tsbonev.core

interface Validator {

    fun validate(user: User): Boolean

}