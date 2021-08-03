package space.stanton.technicaltask.ui.postList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.databinding.ActivityPostListBinding
import space.stanton.technicaltask.databinding.ItemPostBinding
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity

class PostAdapter(
    private val items: MutableList<PostsResponse.Post>,
    val onItemClick: (Int) -> Unit
) :
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val postListTabsPagerAdapter = PostListTabsPagerAdapter(this)
        binding.pager.adapter = postListTabsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) "LIVE" else "OFFLINE"
        }.attach()
    }
}

