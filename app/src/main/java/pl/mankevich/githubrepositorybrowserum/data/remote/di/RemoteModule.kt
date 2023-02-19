package pl.mankevich.githubrepositorybrowserum.data.remote.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mankevich.githubrepositorybrowserum.core.utils.SERVER_URL
import pl.mankevich.githubrepositorybrowserum.data.remote.GitApolloInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideApolloClient(
        loggingInterceptor: LoggingInterceptor,
        gitApolloInterceptor: GitApolloInterceptor
    ): ApolloClient {
        return ApolloClient.Builder()
            .addHttpInterceptor(loggingInterceptor)
            .addInterceptor(gitApolloInterceptor)
            .serverUrl(SERVER_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }
}