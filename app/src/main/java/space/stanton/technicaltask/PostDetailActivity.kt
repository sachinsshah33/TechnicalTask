package space.stanton.technicaltask

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import space.stanton.technicaltest.R
import space.stanton.technicaltest.databinding.ActivityPostDetailsBinding
import space.stanton.technicaltest.databinding.ActivityPostListBinding

/**
 * Shows details of a post
 */
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
        val id = intent.getStringExtra(POST_ID_KEY)

        Thread {
            ApiCalls.getPostById(id!!) {
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