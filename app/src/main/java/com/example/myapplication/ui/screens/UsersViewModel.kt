package com.example.myapplication.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val pagination: Pager<Int, User>
): ViewModel() {
    val users: Flow<PagingData<User>> = pagination.flow.cachedIn(viewModelScope)

}