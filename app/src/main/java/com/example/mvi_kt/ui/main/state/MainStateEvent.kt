package com.example.mvi_kt.ui.main.state

sealed class MainStateEvent {
    class GetBlogEvent : MainStateEvent()

    class GetUserEvent(
        val Userid: String
    ): MainStateEvent()

    class None: MainStateEvent()
}