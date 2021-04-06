package com.example.mvi_kt.ui

import com.example.mvi_kt.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}