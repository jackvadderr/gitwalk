package io.gitwalk.data.datasource.base

import io.gitwalk.base.State
import io.gitwalk.data.datasource.dto.AuthenticatedUserDTO

fun interface GetAuthenticatedUser {
    suspend fun get(): State<AuthenticatedUserDTO>
}