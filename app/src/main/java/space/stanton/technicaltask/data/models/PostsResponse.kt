package space.stanton.technicaltask.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

class PostsResponse : ArrayList<PostsResponse.Post>() {

    @Entity(tableName = "posts")
    data class Post(
        val body: String,

        @PrimaryKey
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