package space.stanton.technicaltask.ui.postList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.databinding.ActivityPostListBinding


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

