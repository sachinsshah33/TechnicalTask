package space.stanton.technicaltask

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import space.stanton.technicaltest.R
import space.stanton.technicaltest.databinding.ActivityPostListBinding
import space.stanton.technicaltest.databinding.ItemPostBinding

class PostAdapter(val items: MutableList<JSONObject>, val onItemClick: (String) -> Unit) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val itemBinding: ItemPostBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: JSONObject) {
            itemBinding.title.text = data.getString("title")
            itemBinding.content.text = data.getString("body")
            itemBinding.root.setOnClickListener {
                onItemClick(data.getString("id"))
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
class PostListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread {
            ApiCalls.loadAll {
                if (it.second != null) {
                    //TODO - handle error
                } else {
                    var json = JSONArray(it.first!!.string())
                    runOnUiThread {
                        var listOfPosts = mutableListOf<JSONObject>()
                        for (i in 0 until json.length()) {
                            listOfPosts.add(i, json.getJSONObject(i))
                        }
                        binding.postsList.adapter =
                            PostAdapter(listOfPosts, onItemClick = { id ->
                                startActivity(
                                    Intent(this, PostDetailActivity::class.java)
                                        .putExtra(PostDetailActivity.POST_ID_KEY, id)
                                )
                            })
                    }
                }
            }
        }.start()

    }
}

