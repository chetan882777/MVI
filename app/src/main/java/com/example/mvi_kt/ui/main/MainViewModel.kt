package com.example.mvi_kt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvi_kt.models.BlogsPost
import com.example.mvi_kt.models.User
import com.example.mvi_kt.ui.main.state.MainStateEvent
import com.example.mvi_kt.ui.main.state.MainViewState
import com.example.mvi_kt.util.AbsentLiveData

class MainViewModel : ViewModel(){

    private val _stateEvent : MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState : MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
     get() = _viewState

    val dataState : LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){
            it?.let {
                handleStateEvent(it)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState>{
        println("DEBUG: New StateEvent detected: $stateEvent")

        return when(stateEvent){
            is MainStateEvent.GetBlogEvent -> {
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val blogList: ArrayList<BlogsPost> = ArrayList()
                        blogList.add(
                            BlogsPost(
                                pk = 0,
                                title = "Vancouver PNE 2019",
                                body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.jpg"
                            )
                        )
                        blogList.add(
                            BlogsPost(
                                pk = 1,
                                title = "Ready for a Walk",
                                body = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                            )
                        )
                        value = MainViewState(
                            blogPosts = blogList
                        )
                    }
                }
            }
            is MainStateEvent.GetUserEvent -> {
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val user = User(
                            email = "mitch@tabian.ca",
                            username = "mitch",
                            image = "https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
                        )
                        value = MainViewState(
                            user = user
                        )
                    }
                }
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
        val value = viewState.value?.let{
            it
        }?: MainViewState()
        return value
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