package space.stanton.technicaltask.ui.postList.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTitle")
fun setTitle(view: TextView, title: String?) {
    view.text = title
}

@BindingAdapter("setContent")
fun setContent(view: TextView, content: String?) {
    view.text = content
}

