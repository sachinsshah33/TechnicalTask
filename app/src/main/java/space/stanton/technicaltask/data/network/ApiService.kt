package space.stanton.technicaltask.data.network

import space.stanton.technicaltask.App

class ApiService(private val apiEndpoints: ApiEndpoints) {
    suspend fun posts() = if (App.isNetworkAvailable()) apiEndpoints.posts() else null

    suspend fun getPostById(postId: String) =
        if (App.isNetworkAvailable()) apiEndpoints.getPostById(postId) else null

    suspend fun getPostCommentsById(postId: String) =
        if (App.isNetworkAvailable()) apiEndpoints.getPostCommentsById(postId) else null
}



