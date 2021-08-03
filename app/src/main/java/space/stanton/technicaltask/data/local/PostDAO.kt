package space.stanton.technicaltask.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import space.stanton.technicaltask.data.models.PostsResponse

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    /*suspend */fun cachePost(post: PostsResponse.Post): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cachePosts(posts: List<PostsResponse.Post>): LongArray?

    @Query("SELECT * FROM posts")
    fun observeCachedPosts(): Flow<List<PostsResponse.Post>>

    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    fun observeCachedPostById(
        postId: String
    ): Flow<List<PostsResponse.Post>>
}
