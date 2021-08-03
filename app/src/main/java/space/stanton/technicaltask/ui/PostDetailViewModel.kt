package space.stanton.technicaltask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import space.stanton.technicaltask.data.models.PostUI
import space.stanton.technicaltask.data.network.ApiService
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {
    val postUI: MutableLiveData<PostUI> = MutableLiveData()

    fun getPostById(postId: String) {
        postUI.value = PostUI.PostLoading
        viewModelScope.launch {
            val postsResponse = apiService.getPostById(postId)
            if (postsResponse.isSuccessful && postsResponse.body() != null) {
                postUI.value = PostUI.PostSuccess(postsResponse.body()!!)
            } else {
                postUI.value = PostUI.PostFailure
            }
        }
    }
}