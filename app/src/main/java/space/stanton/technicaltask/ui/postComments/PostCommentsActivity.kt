package space.stanton.technicaltask.ui.postComments

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostCommentsUI
import space.stanton.technicaltask.databinding.ActivityPostCommentsBinding
import space.stanton.technicaltask.ui.postComments.adapter.PostCommentAdapter
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity


/**
 * Shows details of a post
 */
@AndroidEntryPoint
class PostCommentsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPostCommentsBinding.inflate(layoutInflater)
    }

    private val postCommentsViewModel: PostCommentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra(PostDetailActivity.POST_ID_KEY, -1)
        setupPostComments(id.toString())
    }

    private fun setupPostComments(postId: String) {
        postCommentsViewModel.postCommentsUI.observe(this, {
            when (it) {
                is PostCommentsUI.PostCommentsLoading -> {
                    // TODO - show loading UI
                }
                is PostCommentsUI.PostCommentsSuccess -> {
                    binding.postCommentsList.adapter = PostCommentAdapter(it.items)
                }
                is PostCommentsUI.PostCommentsFailure -> {
                    // TODO - handle error
                }
            }
        })

        postCommentsViewModel.getPostCommentsById(postId)
    }
}