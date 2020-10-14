package com.example.cermati.networks

import com.example.cermati.model.githubusersresponse.GitHubUsersResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/users")
    fun getGitHubUsers(@Query("q") keywords : String,
                       @Query("page") page : Int,
                       @Query("per_page") total : Int): Flowable<GitHubUsersResponse>
}