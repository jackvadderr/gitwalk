package io.gitwalk.utils.http

class HttpSetup private constructor(
    val url: String,
    val headers: HttpHeaders
) {
    data class Builder(
        var url: String = "https://api.github.com/",
        var headers: HttpHeaders = HttpHeaders()
    ) {
        fun url(url: String) = apply { this.url = url }
        fun headers(headers: HttpHeaders) = apply { this.headers = headers }
        fun build() = HttpSetup(url, headers)
    }
}

data class HttpHeaders(
    val accept: String = "Accept: application/vnd.github+json",
    val authorization: String = "Authorization: Bearer <YOUR-TOKEN>",
    val xGitHubApiVersion: String = "X-GitHub-Api-Version: 2022-11-28"
) {
    fun withToken(token: String) = copy(authorization = "Authorization: Bearer $token")
}