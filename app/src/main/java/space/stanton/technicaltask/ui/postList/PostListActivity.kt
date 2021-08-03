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

