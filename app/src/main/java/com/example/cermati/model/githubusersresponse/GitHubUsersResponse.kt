package com.example.cermati.model.githubusersresponse

data class GitHubUsersResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)