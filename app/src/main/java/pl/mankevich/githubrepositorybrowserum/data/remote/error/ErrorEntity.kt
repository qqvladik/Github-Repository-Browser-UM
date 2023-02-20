package pl.mankevich.githubrepositorybrowserum.data.remote.error

sealed class ErrorEntity(private val errorMessage: String) : Throwable(errorMessage) {
    object NoError : ErrorEntity("")
    class ServerTimeoutError(override val message: String? = null) : ErrorEntity(message ?: "Server timeout")
    class NoConnectionError(override val message: String? = null) : ErrorEntity(message ?: "No network")
    data class UnknownError(override val message: String? = null) : ErrorEntity(message ?: "Unknown")
    class InternalServerError(override val message: String? = null) : ErrorEntity(message ?: "Internal server error")
    class ServerReturnNoDataError(override val message: String? = null) : ErrorEntity(message ?: "The server did not return any data")

    //Apollo error 4xx
    class UnauthorizedError(override val message: String? = null) : ErrorEntity(message ?: "User not authorized")
    class NotFoundError(override val message: String? = null) : ErrorEntity(message ?: "Could not resolve to a User with such login")

    //Error in response
    class ResponseError(override val message: String? = null) : ErrorEntity(message ?: "Response error")

}
