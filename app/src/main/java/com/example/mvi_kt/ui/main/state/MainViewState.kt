package com.example.mvi_kt.ui.main.state

import com.example.mvi_kt.models.Blogs
import com.example.mvi_kt.models.User

data class MainViewState(
    var blogPosts : List<Blogs>? = null,
    var user: User? = null
)