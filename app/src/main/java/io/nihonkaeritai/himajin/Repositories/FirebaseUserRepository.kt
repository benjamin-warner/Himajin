package io.nihonkaeritai.himajin.Repositories

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import io.nihonkaeritai.himajin.Auth.Auth
import io.nihonkaeritai.himajin.DBModels.FirebaseUserModel
import io.nihonkaeritai.himajin.Interfaces.IUser
import io.nihonkaeritai.himajin.Interfaces.IUserRepository

class FirebaseUserRepository : IUserRepository {

    companion object {
        private const val USERS_DIRECTORY = "Users/"
        private const val USER_DISPLAY_NAME = "displayName"
    }

    override fun addNewUser(newUser: IUser) {
        val userId = Auth().getUserId()
        val firebaseUserModel = newUser as FirebaseUserModel
        FirebaseDatabase.getInstance()
                .getReference(USERS_DIRECTORY)
                .child(userId)
                .setValue(firebaseUserModel)
                .addOnCompleteListener { result ->
                    if(!result.isSuccessful)
                        Log.e(this.javaClass.simpleName, "New User creation Failed :(")
                }
    }

    override fun updateDisplayName(newName: String) {
        val userId = Auth().getUserId()
        FirebaseDatabase.getInstance()
                .getReference(USERS_DIRECTORY)
                .child(userId)
                .child(USER_DISPLAY_NAME)
                .setValue(newName)
                .addOnCompleteListener { result ->
                    if(!result.isSuccessful)
                        Log.e(this.javaClass.simpleName, "User name update Failed :(")
                }
    }
}
