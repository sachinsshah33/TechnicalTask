package space.stanton.technicaltask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.data.network.ApiService
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {
    val postsUI: MutableLiveData<PostsUI> = MutableLiveData()

    fun getPosts() {
        postsUI.value = PostsUI.PostsLoading
        viewModelScope.launch {
            val postsResponse = apiService.posts()
            if (postsResponse.isSuccessful && postsResponse.body() != null) {
                postsUI.value = PostsUI.PostsSuccess(postsResponse.body()!!)
            } else {
                postsUI.value = PostsUI.PostsFailure
            }
        }
    }
}