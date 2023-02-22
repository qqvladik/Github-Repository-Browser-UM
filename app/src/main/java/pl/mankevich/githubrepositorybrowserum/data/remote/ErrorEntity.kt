package pl.mankevich.githubrepositorybrowserum.data.remote

sealed class ErrorEntity(errorMessage: String) : Throwable(errorMessage) {

    object NoError : ErrorEntity("")
    class ServerTimeoutError(message: String? = null) : ErrorEntity(message ?: "Server timeout")
    class NoConnectionError(message: String? = null) : ErrorEntity(message ?: "No network")
    class UnknownError(message: String? = null) : ErrorEntity(message ?: "Unknown")
    class InternalServerError(message: String? = null) : ErrorEntity(message ?: "Internal server error")
    class ServerReturnNoDataError(message: String? = null) : ErrorEntity(message ?: "The server did not return any data")

    //Apollo error 4xx
    class UnauthorizedError(message: String? = null) : ErrorEntity(message ?: "User not authorized")
    class NotFoundError(message: String? = null) : ErrorEntity(message ?: "Could not resolve to a User with such login")

    //Error in response
    class ResponseError(message: String? = null) : ErrorEntity(message ?: "Response error")

}
