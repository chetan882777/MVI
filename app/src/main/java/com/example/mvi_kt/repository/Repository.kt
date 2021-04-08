package com.example.mvi_kt.repository

import androidx.lifecycle.LiveData
import com.example.mvi_kt.api.RetrofitBuilder
import com.example.mvi_kt.models.BlogPost
import com.example.mvi_kt.models.User
import com.example.mvi_kt.ui.main.state.MainViewState
import com.example.mvi_kt.util.ApiSuccessResponse
import com.example.mvi_kt.util.DataState
import com.example.mvi_kt.util.GenericApiResponse

object Repository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<BlogPost>, MainViewState> () {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(
                    data = MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }

        }.asLiveData()
    }


    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<User, MainViewState> () {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}