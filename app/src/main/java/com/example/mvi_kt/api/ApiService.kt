package com.example.mvi_kt.api

import com.example.mvi_kt.models.Blogs
import com.example.mvi_kt.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts() : List<Blogs>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ) : User

}