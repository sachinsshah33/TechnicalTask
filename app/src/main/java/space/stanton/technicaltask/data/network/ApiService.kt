package space.stanton.technicaltask.data.network

import retrofit2.Response
import space.stanton.technicaltask.data.models.PostsResponse

class ApiService(private val apiEndpoints: ApiEndpoints) {
    suspend fun posts(): Response<PostsResponse> {
        return apiEndpoints.posts()
    }
}



