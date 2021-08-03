package space.stanton.technicaltask.ui.postList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.databinding.FragmentPostListBinding
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity

@AndroidEntryPoint
class PostListOfflineFragment : Fragment() {

    private var binding: FragmentPostListBinding? = null

    private val postListViewModel: PostListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPosts()
    }


    private fun setupPosts() {

    }
}