package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.log

class MainViewModel: ViewModel() {

     private val model = Blog(title = "Here's the updated text!")
    val uiTextLiveData = MutableLiveData<String>()
    fun getUpdatedText() {
        val updatedText = model.title
        uiTextLiveData.postValue(updatedText)
    }

    //
    var lst = MutableLiveData<ArrayList<Blog>>()
    private var newlist = arrayListOf<Blog>()

    fun add(blog: Blog){
        newlist.add(blog)
        lst.value=newlist
    }

    fun remove(blog: Blog){
        newlist.remove(blog)
        lst.value=newlist
    }
}

