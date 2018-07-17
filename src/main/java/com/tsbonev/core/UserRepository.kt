package com.tsbonev.core

class UserRepository(private val userDB: UserDB, private val validator: Validator) {

    fun String.isAdult(): Boolean{
        if(Integer.parseInt(this) >= 18) return true
        return false
    }

    fun registerUser(user: User) {

        userDB.add(user, validator)

    }

    fun isAdult(user: User): Boolean{
        if(userDB.contains(user)){
            return user.age.isAdult()
        }
        return false
    }

}
