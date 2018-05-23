package io.nihonkaeritai.himajin.Interfaces

import io.nihonkaeritai.himajin.Exceptions.AuthException

interface IHandlesAuth {
    fun handleAuthAttempt(success: Boolean, exception: AuthException?)
}
