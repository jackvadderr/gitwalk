package io.gitwalk.domain.sdk.network

import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkManager private constructor() {
    var bearerTokenPrimary: String? = null
    var bearerTokenSecondary: String? = null
    var engineHttp: HttpClientEngine? = null
    var logLevel: LogLevel = LogLevel.NONE
    var logger: Logger = Logger.DEFAULT

    fun tokenPrimary(token: String?) = apply { this.bearerTokenPrimary = token }
    fun tokenSecondary(token: String?) = apply { this.bearerTokenSecondary = token }
    fun engine(engine: HttpClientEngine?) = apply { this.engineHttp = engine }
    fun logLevel(level: String) = apply {
        this.logLevel = when (level.uppercase()) {
            "ALL" -> LogLevel.ALL
            "HEADERS" -> LogLevel.HEADERS
            "BODY" -> LogLevel.BODY
            else -> LogLevel.NONE
        }
    }
    fun logger(logger: Logger) = apply { this.logger = logger }

    fun build(): HttpClient {
        return HttpClient(engineHttp ?: CIO.create()) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(bearerTokenPrimary.orEmpty(), bearerTokenSecondary.orEmpty())
                    }
                }
            }

            install(HttpRedirect) {
                checkHttpMethod = false
            }

            install(Logging) {
                level = this@NetworkManager.logLevel
                logger = this@NetworkManager.logger
            }

            defaultRequest {
                headers {
                    append(HttpHeaders.ContentType, Json.toString())
                    append(HttpHeaders.Accept, Json.toString())
                }
            }
            expectSuccess = false
        }
    }

    companion object {
        fun newInstance() = NetworkManager()
    }
}

object NetworkProvider {
    private val client: HttpClient by lazy {
        NetworkManager.newInstance()
            .tokenPrimary("seu_token_principal")
            .logLevel("ALL")
            .logger(Logger.DEFAULT)
            .engine(CIO.create())
            .build()
    }

    fun getClient(): HttpClient = client
}
