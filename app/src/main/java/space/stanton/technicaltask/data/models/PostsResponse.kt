package space.stanton.technicaltask.data.models

class PostsResponse : ArrayList<PostsResponse.Post>() {
    data class Post(
        val body: String,
        val id: Int,
        val title: String,
        val userId: Int
    )
}