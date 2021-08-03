package space.stanton.technicaltask.data.models

class PostsResponse : ArrayList<PostsResponse.Post>() {
    data class Post(
        val body: String,
        val id: Int,
        val title: String,
        val userId: Int
    )
}


sealed class PostsUI {
    object PostsLoading : PostsUI()
    class PostsSuccess(val items: MutableList<PostsResponse.Post>) : PostsUI()
    object PostsFailure : PostsUI()
}

sealed class PostUI {
    object PostLoading : PostUI()
    class PostSuccess(val item: PostsResponse.Post) : PostUI()
    object PostFailure : PostUI()
}