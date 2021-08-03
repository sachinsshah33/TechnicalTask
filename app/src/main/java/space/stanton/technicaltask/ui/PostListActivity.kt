package space.stanton.technicaltask.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.databinding.ActivityPostListBinding
import space.stanton.technicaltask.databinding.ItemPostBinding

class PostAdapter(val items: MutableList<PostsResponse.Post>, val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val itemBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: PostsResponse.Post) {
            itemBinding.title.text = data.title
            itemBinding.content.text = data.body
            itemBinding.root.setOnClickListener {
                onItemClick(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

/**
 * Displays a list of posts
 */
@AndroidEntryPoint
class PostListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostListBinding.inflate(layoutInflater)
    }

    private val postListViewModel: PostListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        postListViewModel.postsUI.observe(this, {
            when (it) {
                is PostsUI.PostsLoading -> {
                    // TODO - show loading UI
                }
                is PostsUI.PostsSuccess -> {
                    binding.postsList.adapter =
                        PostAdapter(it.items) { id ->
                            startActivity(
                                Intent(
                                    this@PostListActivity,
                                    PostDetailActivity::class.java
                                ).putExtra(PostDetailActivity.POST_ID_KEY, id)
                            )
                        }
                }
                is PostsUI.PostsFailure -> {
                    // TODO - handle error
                }
            }
        })

        postListViewModel.getPosts()
    }
}

