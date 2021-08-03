package space.stanton.technicaltask.data.network


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import space.stanton.technicaltask.data.models.PostsResponse

interface ApiEndpoints {
    @GET("posts")
    suspend fun posts(): Response<PostsResponse>

    @GET("posts/{POST_ID}")
    suspend fun getPostById(@Path("POST_ID") postId: String): Response<PostsResponse.Post>
}