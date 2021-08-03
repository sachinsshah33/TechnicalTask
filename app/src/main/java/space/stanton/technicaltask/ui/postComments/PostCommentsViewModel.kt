package space.stanton.technicaltask.ui.postComments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import space.stanton.technicaltask.data.models.PostCommentsUI
import space.stanton.technicaltask.data.network.ApiService
import javax.inject.Inject

@HiltViewModel
class PostCommentsViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {
    val postCommentsUI: MutableLiveData<PostCommentsUI> = MutableLiveData()

    fun getPostCommentsById(postId: String) {
        postCommentsUI.value = PostCommentsUI.PostCommentsLoading
        viewModelScope.launch {
            val postCommentsResponse = apiService.getPostCommentsById(postId)
            if (postCommentsResponse.isSuccessful && !postCommentsResponse.body().isNullOrEmpty()) {
                postCommentsUI.value =
                    PostCommentsUI.PostCommentsSuccess(postCommentsResponse.body()!!)
            } else {
                postCommentsUI.value = PostCommentsUI.PostCommentsFailure
            }
        }
    }
}