package pl.mankevich.githubrepositorybrowserum.data.remote.error

import okhttp3.ResponseBody
import pl.mankevich.githubrepositorybrowserum.data.remote.error.ErrorEntity

abstract class CustomErrorEntity(
    val errorBody: ResponseBody?,
    errorMessage: String): ErrorEntity(errorMessage)