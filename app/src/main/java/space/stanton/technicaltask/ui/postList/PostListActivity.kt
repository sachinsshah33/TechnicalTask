package space.stanton.technicaltask.ui.postList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.databinding.ActivityPostListBinding


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

        val postListTabsPagerAdapter = PostListTabsPagerAdapter(this)
        binding.pager.adapter = postListTabsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if (position == 0) "LIVE" else "OFFLINE"
        }.attach()

        setupCachesPostCount()
    }

    private fun setupCachesPostCount() {
        postListViewModel.cachedPostsUI.observe(this, {
            when (it) {
                is PostsUI.PostsSuccess -> {
                    val count = it.items.size
                    binding.tabLayout.getTabAt(1)?.let {
                        if(count>0){
                            val badge = it.orCreateBadge
                            badge.number = count
                        }
                        else{
                            it.removeBadge()
                        }
                    }
                }
                else -> {
                    binding.tabLayout.getTabAt(1)?.removeBadge()
                }
            }
        })

        postListViewModel.getCachedPosts()
    }
}

