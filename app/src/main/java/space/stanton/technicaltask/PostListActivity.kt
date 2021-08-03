package space.stanton.technicaltest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class PostAdapter(val items: MutableList<JSONObject>, val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = items[position].getString("title")
        holder.itemView.findViewById<TextView>(R.id.content).text =
            items[position].getString("body")
        holder.itemView.setOnClickListener {
            onItemClick(items[position].getString("id"))
        }
    }

    override fun getItemCount(): Int = items.size

}

/**
 * Displays a list of posts
 */
class PostListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                        findViewById<RecyclerView>(R.id.postsList).adapter =
                            PostAdapter(listOfPosts, onItemClick = { id ->
                                startActivity(
                                    Intent(this, PostDetailActivity::class.java)
                                        .putExtra("postId", id)
                                )
                            })
                    }
                }
            }
        }.start()

    }
}

