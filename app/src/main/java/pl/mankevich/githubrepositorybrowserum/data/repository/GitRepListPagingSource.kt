package pl.mankevich.githubrepositorybrowserum.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.mankevich.githubrepositorybrowserum.data.datasource.remote.impl.RemoteDataSourceImpl
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto
import pl.mankevich.githubrepositorybrowserum.data.model.remote.request.GitRepListRequest
import java.io.IOException

class GitRepListPagingSource(
    private val remoteDataSource: RemoteDataSourceImpl,//TODO задавать через интерфейс
    private val request: GitRepListRequest,
) : PagingSource<Int, GitRepSimpleDto>() { //TODO Int надо заменить на стринг

    override fun getRefreshKey(state: PagingState<Int, GitRepSimpleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepSimpleDto> {
        val page = params.key ?: 1
        return try {
            val gitRepSimpleList = remoteDataSource.fetchGitRepSimpleDtoList(request) //TODO надо бы задавать здесь page и мб количество элементов на страницу

            LoadResult.Page(
                data = gitRepSimpleList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (gitRepSimpleList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) { // Поконкретнее обработать ошибки
            return LoadResult.Error(exception)
        }
    }


}