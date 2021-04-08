package com.example.mvi_kt.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvi_kt.R
import com.example.mvi_kt.ui.DataStateListener
import com.example.mvi_kt.ui.main.state.MainStateEvent
import com.example.mvi_kt.ui.main.state.MainStateEvent.*
import java.lang.ClassCastException

class MainFragment : Fragment(){

    lateinit var viewModel: MainViewModel

    lateinit var dataStatehandler : DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            println("DEBUG : dataState : ${dataState}")

            // handle loading and message
            dataStatehandler.onDataStateChange(dataState)

            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->
                    mainViewState.blogPosts?.let {
                        viewModel.setBlogListData(it)
                    }

                    mainViewState.user?.let {
                        viewModel.setUserData(it)
                    }
                }

            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            viewState.blogPosts?.let {
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.blogPosts}")
            }

            viewState.user?.let {
                // set User data to widgets
                println("DEBUG: Setting User data: ${viewState.user}")
            }

        })

    }

    fun triggerGetUserEvent(){
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    fun triggerGetBlogEvent(){
        viewModel.setStateEvent(GetBlogEvent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,  menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_user -> triggerGetUserEvent()

            R.id.action_get_blogs -> triggerGetBlogEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStatehandler = context as DataStateListener
        } catch (e: ClassCastException) {
            println("DEBUG: $context must implement DataStateListener")
        }
    }
}