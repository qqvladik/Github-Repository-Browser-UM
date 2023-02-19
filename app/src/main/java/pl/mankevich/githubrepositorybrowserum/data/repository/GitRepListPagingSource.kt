package pl.mankevich.githubrepositorybrowserum.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.RemoteDataSource
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import java.io.IOException

class GitRepListPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val ownerLogin: String,
) : PagingSource<String, GitRepSimpleDto>() {

    override fun getRefreshKey(state: PagingState<String, GitRepSimpleDto>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
            state.pages.getOrNull(anchorPageIndex + 1)?.prevKey ?: state.pages.getOrNull(anchorPageIndex - 1)?.nextKey
        }


    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, GitRepSimpleDto> {
        val cursor = params.key
        return try {
            val gitRepsResponse = remoteDataSource.fetchGitRepsResponse(
                GitRepListRequest(
                    ownerLogin = ownerLogin,
                    cursor = cursor
                )
            )

            LoadResult.Page(
                data = gitRepsResponse.gitRepSimpleList,
                prevKey = gitRepsResponse.startCursor,
                nextKey = gitRepsResponse.endCursor
            )
        } catch (exception: IOException) {
            //TODO Поконкретнее обработать ошибки, ведь в мапперах я просто Extension кидаю, кстати ответы 400 я тоже превратил в ошибки
            return LoadResult.Error(exception)
        }
    }


}