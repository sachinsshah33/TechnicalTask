package space.stanton.technicaltest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

/**
 * Shows details of a post
 */
class PostDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        val id = intent.getStringExtra("postId")

        Thread {
            ApiCalls.getPostById(id!!) {
                if (it.second != null) {
                    // TODO - handle error
                } else {
                    val post = JSONObject(it.first!!.string())
                    runOnUiThread {

                        findViewById<TextView>(R.id.title).text = post.getString("title")
                        findViewById<TextView>(R.id.content).text = post.getString("body")

                        this@PostDetailActivity.title = post.getString("title")

                    }
                }
            }

        }.start()
    }
}