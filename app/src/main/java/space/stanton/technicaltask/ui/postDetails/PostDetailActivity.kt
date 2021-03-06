package space.stanton.technicaltask.ui.postDetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostUI
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.databinding.ActivityPostDetailsBinding
import space.stanton.technicaltask.ui.postComments.PostCommentsActivity

/**
 * Shows details of a post
 */
@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {
    companion object {
        const val POST_ID_KEY = "POST_ID_KEY"
    }

    private val binding by lazy {
        ActivityPostDetailsBinding.inflate(layoutInflater)
    }

    private val postDetailViewModel: PostDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra(POST_ID_KEY, -1)
        setupCachePost(id.toString())

        binding.cache.setOnClickListener {
            post?.let {
                postDetailViewModel.cachePost(it)
            }
        }

        binding.comments.setOnClickListener {
            startActivity(
                Intent(
                    this@PostDetailActivity,
                    PostCommentsActivity::class.java
                ).putExtra(POST_ID_KEY, id)
            )
        }
    }

    private var post: PostsResponse.Post? = null

    private fun setupPost(postId: String) {
        postDetailViewModel.postUI.observe(this, {
            when (it) {
                is PostUI.PostLoading -> {
                    // TODO - show loading UI
                }
                is PostUI.PostSuccess -> {
                    setPostUI(it.item)
                }
                is PostUI.PostFailure -> {
                    // TODO - handle error
                }
            }
        })

        postDetailViewModel.getPostById(postId)
    }

    private fun setupCachePost(postId: String) {
        postDetailViewModel.cachedPostUI.observe(this, {
            when (it) {
                is PostUI.PostLoading -> {
                    // TODO - show loading UI
                }
                is PostUI.PostSuccess -> {
                    binding.cache.isEnabled = false
                    setPostUI(it.item)
                }
                is PostUI.PostFailure -> {
                    setupPost(postId) //get from live if not in cache, or maybe pass in parcelable post model to this activity?
                }
            }
        })

        postDetailViewModel.observeCachedPostById(postId)
    }

    private fun setPostUI(post: PostsResponse.Post) {
        this.post = post
        post.title.let {
            binding.title.text = it
            this@PostDetailActivity.title = it
        }
        binding.content.text = post.body
    }
}