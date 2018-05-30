package io.nihonkaeritai.himajin.DBModels

import com.google.firebase.database.IgnoreExtraProperties
import io.nihonkaeritai.himajin.Interfaces.IUserDBModel


@IgnoreExtraProperties
class FirebaseUserModel : IUserDBModel {
    var displayName: String? = null
    var email: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(displayName: String?, email: String?) {
        this.displayName = displayName
        this.email = email
    }
}