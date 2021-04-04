package com.example.mvi_kt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mvi_kt.api.RetrofitBuilder
import com.example.mvi_kt.ui.main.state.MainViewState
import com.example.mvi_kt.util.ApiEmptyResponse
import com.example.mvi_kt.util.ApiSuccessResponse

object Repository {

    fun getBlogPosts(): LiveData<MainViewState> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
                object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse) {
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    blogPosts =  apiResponse.body
                                )
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }
                        }
                    }
                }
            }
    }


    fun getUser(userId : String): LiveData<MainViewState> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
                object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse) {
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    user =  apiResponse.body
                                )
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }
                        }
                    }
                }
            }
    }
}