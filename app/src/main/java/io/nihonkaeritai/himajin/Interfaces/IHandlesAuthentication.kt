package io.nihonkaeritai.himajin.Interfaces

import io.nihonkaeritai.himajin.Exceptions.AuthException

interface IHandlesAuthentication {
    fun handleRegisterAttempt(success: Boolean, exception: AuthException?)
    fun handleAuthAttempt(success: Boolean, exception: AuthException?)
}
