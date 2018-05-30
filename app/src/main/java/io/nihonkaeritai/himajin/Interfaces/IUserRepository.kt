package io.nihonkaeritai.himajin.Interfaces

interface IUserRepository {
    fun addNewUser(newUser: IUser)
    fun updateDisplayName(newName: String)
}