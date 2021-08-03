package space.stanton.technicaltask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra(POST_ID_KEY, -1)

        Thread {
            ApiCalls.getPostById(id!!.toString()) {
                if (it.second != null) {
                    // TODO - handle error
                } else {
                    val post = JSONObject(it.first!!.string())
                    runOnUiThread {
                        post.getString("title").let {
                            binding.title.text = it
                            this@PostDetailActivity.title = it
                        }
                        binding.content.text = post.getString("body")
                    }
                }
            }

        }.start()
    }
}