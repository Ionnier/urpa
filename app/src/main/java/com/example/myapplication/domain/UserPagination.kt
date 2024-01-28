package com.example.myapplication.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.models.User

class UserPagination(
    private val useCase: GetUsersUseCase
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: 1

        val response = useCase.execute(
            page = pageIndex,
            results = params.loadSize
        ) ?: return LoadResult.Error(Exception())

        val nextKey = pageIndex + 1
        return LoadResult.Page(
            data = response,
            prevKey = if (nextKey == 2) null else nextKey - 1,
            nextKey = if (nextKey == 4) null else nextKey
        )
    }
}