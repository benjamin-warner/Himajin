package io.nihonkaeritai.himajin.Interfaces

interface IUserRepository {
    fun addNewUser(newUser: IUserDBModel)
    fun updateDisplayName(newName: String)
}