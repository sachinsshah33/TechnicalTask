package space.stanton.technicaltask.data.models

class PostCommentsResponse : ArrayList<PostCommentsResponse.Comment>() {
    data class Comment(
        val body: String,
        val email: String,
        val id: Int,
        val name: String,
        val postId: Int
    )
}

sealed class PostCommentsUI {
    object PostCommentsLoading : PostCommentsUI()
    class PostCommentsSuccess(val items: MutableList<PostCommentsResponse.Comment>) :
        PostCommentsUI()

    object PostCommentsFailure : PostCommentsUI()
}