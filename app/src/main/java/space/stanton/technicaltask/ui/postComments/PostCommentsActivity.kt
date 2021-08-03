package space.stanton.technicaltask.ui.postComments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostCommentsResponse
import space.stanton.technicaltask.data.models.PostCommentsUI
import space.stanton.technicaltask.databinding.ActivityPostCommentsBinding
import space.stanton.technicaltask.databinding.ItemCommentBinding
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity


class PostCommentAdapter(private val items: MutableList<PostCommentsResponse.Comment>) :
    RecyclerView.Adapter<PostCommentAdapter.PostCommentViewHolder>() {

    inner class PostCommentViewHolder(private val itemBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: PostCommentsResponse.Comment) {
            itemBinding.title.text = data.name
            itemBinding.content.text = data.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        return PostCommentViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

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