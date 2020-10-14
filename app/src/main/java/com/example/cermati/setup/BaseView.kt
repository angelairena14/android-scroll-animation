package com.example.cermati.setup

interface BaseView<V : MainView> {
    fun attachView(baseInterface: V)
    fun detachView()
}