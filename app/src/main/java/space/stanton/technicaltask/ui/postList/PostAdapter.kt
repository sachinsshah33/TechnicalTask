package space.stanton.technicaltask.ui.postList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import space.stanton.technicaltask.data.models.PostsResponse
import space.stanton.technicaltask.databinding.ItemPostBinding


class PostAdapter(val onItemClick: (Int) -> Unit) :
    ListAdapter<PostsResponse.Post, PostAdapter.PostViewHolder>(PostDiffUtil()) {
    companion object {
        class PostDiffUtil : DiffUtil.ItemCallback<PostsResponse.Post>() {
            override fun areItemsTheSame(
                oldItem: PostsResponse.Post,
                newItem: PostsResponse.Post
            ) = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: PostsResponse.Post,
                newItem: PostsResponse.Post
            ) =
                oldItem == newItem
        }
    }

    inner class PostViewHolder(private val itemBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: PostsResponse.Post) {
            itemBinding.title.text = data.title
            itemBinding.content.text = data.body
            itemBinding.root.setOnClickListener {
                onItemClick(data.id)
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
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}