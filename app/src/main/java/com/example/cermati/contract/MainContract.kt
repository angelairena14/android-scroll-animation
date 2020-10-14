package com.example.cermati.contract

import com.example.cermati.model.githubusersresponse.GitHubUsersResponse
import com.example.cermati.setup.MainView

interface MainContract {
    interface View : MainView {
        fun bindData(d : GitHubUsersResponse, currentPage : Int, totalPage :Int)
        fun usersNotFound()
        fun showLoading()
        fun dismissLoading()
    }
    interface Presenter{
        fun fetchData(keywords : String,page : Int)
    }
}