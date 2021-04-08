package com.example.mvi_kt.ui.main.state

import com.example.mvi_kt.models.BlogPost
import com.example.mvi_kt.models.User

data class MainViewState(
    var blogPosts : List<BlogPost>? = null,
    var user: User? = null
)