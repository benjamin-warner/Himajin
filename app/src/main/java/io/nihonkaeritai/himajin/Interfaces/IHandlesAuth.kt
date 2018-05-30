package io.nihonkaeritai.himajin.Interfaces

import io.nihonkaeritai.himajin.Exceptions.AuthException

interface IHandlesAuth {
    fun handleRegisterAttempt(success: Boolean, exception: AuthException?)
    fun handleAuthAttempt(success: Boolean, exception: AuthException?)
}
