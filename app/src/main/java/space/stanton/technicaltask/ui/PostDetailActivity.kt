package space.stanton.technicaltask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostUI
import space.stanton.technicaltask.databinding.ActivityPostDetailsBinding

/**
 * Shows details of a post
 */
@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity() {
    companion object {
        const val POST_ID_KEY = "POST_ID_KEY"
    }

    val binding by lazy {
        ActivityPostDetailsBinding.inflate(layoutInflater)
    }

    private val postDetailViewModel: PostDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra(POST_ID_KEY, -1)

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

        postDetailViewModel.getPostById(id.toString())
    }
}