package space.stanton.technicaltask.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.network.ApiService
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {
    suspend fun posts(): Response<PostsResponse> {
        return apiService.posts()
    }
}