package io.nihonkaeritai.himajin.Interfaces

interface IAuth {
    fun register(email: String, password : String, handler: IHandlesAuthentication)
    fun login(email: String, password : String, handler: IHandlesAuthentication)
    fun isLoggedIn() : Boolean
    fun getUserId() : String?
    fun getEmail() : String?
}