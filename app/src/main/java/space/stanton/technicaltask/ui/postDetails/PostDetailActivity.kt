package space.stanton.technicaltask.ui.postDetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostUI
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
        setupPost(id.toString())

        binding.comments.setOnClickListener {
            startActivity(
                Intent(
                    this@PostDetailActivity,
                    PostCommentsActivity::class.java
                ).putExtra(POST_ID_KEY, id)
            )
        }
    }

    private fun setupPost(postId: String) {
        postDetailViewModel.postUI.observe(this, {
            when (it) {
                is PostUI.PostLoading -> {
                    // TODO - show loading UI
                }
                is PostUI.PostSuccess -> {
                    it.item.title.let {
                        binding.title.text = it
                        this@PostDetailActivity.title = it
                    }
                    binding.content.text = it.item.body
                }
                is PostUI.PostFailure -> {
                    // TODO - handle error
                }
            }
        })

        postDetailViewModel.getPostById(postId)
    }
}