package com.example.mvi_kt.ui.main.state

import com.example.mvi_kt.models.BlogsPost
import com.example.mvi_kt.models.User

data class MainViewState(
    var blogPosts : List<BlogsPost>? = null,
    var user: User? = null
)