package com.example.mvi_kt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvi_kt.models.BlogsPost
import com.example.mvi_kt.models.User
import com.example.mvi_kt.repository.Repository
import com.example.mvi_kt.ui.main.state.MainStateEvent
import com.example.mvi_kt.ui.main.state.MainViewState
import com.example.mvi_kt.util.AbsentLiveData
import com.example.mvi_kt.util.DataState

class MainViewModel : ViewModel(){

    private val _stateEvent : MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState : MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
     get() = _viewState

    val dataState : LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){
            it?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>>{
        println("DEBUG: New StateEvent detected: $stateEvent")

        return when(stateEvent){
            is MainStateEvent.GetBlogEvent -> {
                return Repository.getBlogPosts()
            }
            is MainStateEvent.GetUserEvent -> {
                return Repository.getUser(stateEvent.Userid)
            }

            is MainStateEvent.None -> {
                AbsentLiveData.create()
            }

        }
    }

    fun setBlogListData(blogPosts: List<BlogsPost>){
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUserData(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent){
        val state: MainStateEvent
        state = event
        _stateEvent.value = state
    }

}

fun main(){

    val names = arrayOf("H", "E", "L", "L", "O");
    for(n in names){
        print(" $n ")
    }

}