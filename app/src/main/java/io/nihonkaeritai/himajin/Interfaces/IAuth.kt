package io.nihonkaeritai.himajin.Interfaces

interface IAuth {
    fun register(email: String, password : String)
    fun login(email: String, password : String)
}