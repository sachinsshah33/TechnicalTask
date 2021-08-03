package space.stanton.technicaltask.ui.postDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.stanton.technicaltask.data.models.PostUI
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.network.ApiService
import space.stanton.technicaltask.data.repositories.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(val postRepository: PostRepository) : ViewModel() {
    val postUI: MutableLiveData<PostUI> = MutableLiveData()

    fun getPostById(postId: String) {
        postUI.value = PostUI.PostLoading
        viewModelScope.launch {
            val postsResponse = postRepository.getPostById(postId)
            if (postsResponse.isSuccessful && postsResponse.body() != null) {
                postUI.value = PostUI.PostSuccess(postsResponse.body()!!)
            } else {
                postUI.value = PostUI.PostFailure
            }
        }
    }

    fun cachePost(post: PostsResponse.Post) {
        CoroutineScope(Dispatchers.IO).launch {
            postRepository.cachePost(post)
        }
    }
}