package space.stanton.technicaltask.data.repositories

import space.stanton.technicaltask.data.local.PostDAO
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.network.ApiService

class PostRepository(val apiService: ApiService, val postDAO: PostDAO) {
    suspend fun posts() = apiService.posts()

    suspend fun getPostById(postId: String) = apiService.getPostById(postId)

    suspend fun getPostCommentsById(postId: String) = apiService.getPostCommentsById(postId)


    suspend fun cachePost(post: PostsResponse.Post) = postDAO.cachePost(post)
    suspend fun observeCachedPosts() = postDAO.observeCachedPosts()
    suspend fun observeCachedPostById(postId: String) = postDAO.observeCachedPostById(postId)
}