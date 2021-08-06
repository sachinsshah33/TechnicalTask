package space.stanton.technicaltask.ui.postComments.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setCommentTitle")
fun setCommentTitle(view: TextView, title: String?) {
    view.text = title
}

@BindingAdapter("setCommentContent")
fun setCommentContent(view: TextView, content: String?) {
    view.text = content
}

