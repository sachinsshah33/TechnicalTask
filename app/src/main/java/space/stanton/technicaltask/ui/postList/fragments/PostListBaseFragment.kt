package space.stanton.technicaltask.ui.postList.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.databinding.FragmentPostListBinding
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity
import space.stanton.technicaltask.ui.postList.PostListViewModel
import space.stanton.technicaltask.ui.postList.adapter.PostAdapter
import space.stanton.technicaltask.ui.postList.adapter.PostSelectedListener

@AndroidEntryPoint
open class PostListBaseFragment : Fragment() {

    private var binding: FragmentPostListBinding? = null

    protected val postListViewModel: PostListViewModel by viewModels()

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
        binding?.postsList?.adapter = postAdapter
        setupPosts()
    }

    protected val postAdapter by lazy {
        PostAdapter(object : PostSelectedListener {
            override fun postClicked(postId: Int) {
                startActivity(
                    Intent(
                        requireActivity(),
                        PostDetailActivity::class.java
                    ).apply {
                        putExtra(PostDetailActivity.POST_ID_KEY, postId)
                    }
                )
            }
        })
    }

    open fun setupPosts() {}
}