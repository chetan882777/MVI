package com.example.mvi_kt.api

import androidx.lifecycle.LiveData
import com.example.mvi_kt.models.BlogsPost
import com.example.mvi_kt.models.User
import com.example.mvi_kt.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts() : LiveData<GenericApiResponse<List<BlogsPost>>>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ) : LiveData<GenericApiResponse<User>>

}