package io.gitwalk.data.datasource.impl

import io.gitwalk.base.State
import io.gitwalk.data.datasource.base.GetAuthenticatedUser
import io.gitwalk.data.datasource.dto.AuthenticatedUserDTO
import io.gitwalk.domain.sdk.network.NetworkManager
import io.gitwalk.domain.sdk.network.NetworkProvider
import io.gitwalk.domain.sdk.network.schemas.AuthenticatedUserSchema
import io.gitwalk.utils.toDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class GetAuthenticatedUserImpl: GetAuthenticatedUser {
    private val provider: HttpClient = NetworkProvider().create()
    override suspend fun get(): State<AuthenticatedUserDTO> {
        return try {
            val response = provider.get("https://api.github.com/user")
            val responseBody = response.bodyAsText()
            val schema = Json.decodeFromString<AuthenticatedUserSchema>(responseBody)
            val dto: AuthenticatedUserDTO = schema.toDto()
            State.Success(dto)
        } catch (e: Exception) {
            State.Error(e)
        }
    }
}

//    val client = NetworkProvider
//        .tokenPrimary("seu_token_principal")
//        .logLevel("ALL")
//        .logger(Logger.DEFAULT)
//        .engine(CIO.create())
//        .build()q
//
//val response = client.post(url) {
//    contentType(ContentType.Application.Json)
//    setBody(schema)
//}
//Log.d("PostProfessionaldDataSourceImpl", "Resposta recebida")
//
//val responseBody = response.bodyAsText()
//Log.d("PostProfessionaldDataSourceImpl", "Corpo da resposta: $responseBody")
//
//val professionalResponse: ResponseProfessionalSchema =
//    Json.decodeFromString<ResponseProfessionalSchema>(responseBody)
//val professionalDto: ProfessionalDTO = professionalResponse.toProfessionalDTO()