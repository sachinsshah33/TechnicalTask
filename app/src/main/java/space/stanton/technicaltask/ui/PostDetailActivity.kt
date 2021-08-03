package space.stanton.technicaltask.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject
import space.stanton.technicaltask.data.network.ApiCalls
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

        lifecycleScope.launch {
            val postResponse = postDetailViewModel.getPostById(id.toString())
            if(postResponse.isSuccessful && postResponse.body() != null){
                postResponse.body()!!.title.let {
                    binding.title.text = it
                    this@PostDetailActivity.title = it
                }
                binding.content.text = postResponse.body()!!.body
            }
            else{
                // TODO - handle error
            }
        }
    }
}