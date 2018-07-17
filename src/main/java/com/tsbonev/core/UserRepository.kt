package com.tsbonev.core

class UserRepository(private val userDB: UserDB,
                     private val validator: Validator,
                     private val adultAge: Int = 18) {

    private fun String.isAdult(): Boolean{
        if(Integer.parseInt(this) >= adultAge) return true
        return false
    }

    fun registerUser(user: User) {

        if(validator.validate(user)){
            userDB.add(user)
        }

    }

    fun isAdult(user: User): Boolean{
        if(userDB.contains(user)){
            return user.age.isAdult()
        }
        return false
    }

}
