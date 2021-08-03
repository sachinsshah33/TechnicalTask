package space.stanton.technicaltask.ui.postList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.data.network.ApiService
import space.stanton.technicaltask.data.repositories.PostRepository
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val postRepository: PostRepository) : ViewModel() {
    val postsUI: MutableLiveData<PostsUI> = MutableLiveData()

    fun getPosts() {
        postsUI.value = PostsUI.PostsLoading
        CoroutineScope(Dispatchers.IO).launch {
            val postsResponse = postRepository.posts()
            viewModelScope.launch {
                if (postsResponse.isSuccessful && !postsResponse.body().isNullOrEmpty()) {
                    postsUI.value = PostsUI.PostsSuccess(postsResponse.body()!!)
                } else {
                    postsUI.value = PostsUI.PostsFailure
                }
            }
        }
    }

    val cachedPostsUI: MutableLiveData<PostsUI> = MutableLiveData()

    fun getCachedPosts() {
        cachedPostsUI.value = PostsUI.PostsLoading
        CoroutineScope(Dispatchers.IO).launch {
            postRepository.observeCachedPosts().collectLatest {
                viewModelScope.launch {
                    if(it.isNotEmpty()){
                        cachedPostsUI.value = PostsUI.PostsSuccess(it.toMutableList())
                    }
                    else{
                        cachedPostsUI.value = PostsUI.PostsFailure
                    }
                }
            }
        }
    }
}