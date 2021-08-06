package space.stanton.technicaltask

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import retrofit2.Response
import space.stanton.technicaltask.data.local.PostDAO
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.network.ApiEndpoints
import space.stanton.technicaltask.data.network.ApiService
import space.stanton.technicaltask.data.repositories.PostRepository
import space.stanton.technicaltask.ui.postDetails.PostDetailViewModel

class ApiInterfaceUnitTest {

    @Mock
    lateinit var apiEndpoints: ApiEndpoints

    @Mock
    lateinit var postDAO: PostDAO

    lateinit var apiService: ApiService

    lateinit var postRepository: PostRepository

    lateinit var postDetailViewModel: PostDetailViewModel


    @Before
    fun before() {
        apiEndpoints = mock(ApiEndpoints::class.java)
        postDAO = mock(PostDAO::class.java)
        apiService = ApiService(apiEndpoints)
        postRepository = PostRepository(apiService, postDAO)
        postDetailViewModel = PostDetailViewModel(postRepository)
    }


    /*@Test
    fun server_returns_matching_post() {
        val aPost = PostsResponse.Post(body = "", id = 1, title = "", userId = 0)
        runBlocking {
            doReturn(Response.success(listOf(aPost))).`when`(apiEndpoints).getPostById("1")
            postDetailViewModel.getPostById("1")
        }
        assertEquals(
            aPost, (postDetailViewModel.cachedPostUI.value)
        )
    }*/
}