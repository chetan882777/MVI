package com.example.mvi_kt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvi_kt.ui.main.state.MainStateEvent
import com.example.mvi_kt.ui.main.state.MainViewState
import com.example.mvi_kt.util.AbsentLiveData

class MainViewModel : ViewModel(){

    private val _stateEvent : MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState : MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
     get() = _viewState

    val dataState : LiveData<MainStateEvent> = Transformations
        .switchMap(_stateEvent){
            it?.let {
                handleStateEvent(it)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainStateEvent>{
        return when(stateEvent){
            is MainStateEvent.GetBlogEvent -> {
                AbsentLiveData.create()
            }
            is MainStateEvent.GetUserEvent -> {
                AbsentLiveData.create()
            }

            is MainStateEvent.None -> {
                AbsentLiveData.create()
            }

        }
    }
}