package space.stanton.technicaltask.data.network


import retrofit2.Response
import retrofit2.http.GET
import space.stanton.technicaltask.data.models.PostsResponse

interface ApiEndpoints {
    @GET("posts")
    suspend fun posts(): Response<PostsResponse>
}