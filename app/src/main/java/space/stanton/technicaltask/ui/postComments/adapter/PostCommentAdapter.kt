package space.stanton.technicaltask.ui.postComments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.stanton.technicaltask.data.models.PostCommentsResponse
import space.stanton.technicaltask.databinding.ItemCommentBinding

class PostCommentAdapter(private val items: MutableList<PostCommentsResponse.Comment>) :
    RecyclerView.Adapter<PostCommentAdapter.PostCommentViewHolder>() {

    inner class PostCommentViewHolder(private val itemBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: PostCommentsResponse.Comment) {
            itemBinding.model = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        return PostCommentViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}