package space.stanton.technicaltask.ui.postList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsUI
import space.stanton.technicaltask.databinding.FragmentPostListBinding
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity
import space.stanton.technicaltask.ui.postList.adapter.PostAdapter
import space.stanton.technicaltask.ui.postList.adapter.PostSelectedListener

@AndroidEntryPoint
class PostListLiveFragment : Fragment() {

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
        binding?.postsList?.adapter = postAdapter
        setupPosts()
    }

    private val postAdapter by lazy {
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

    private fun setupPosts() {
        postListViewModel.postsUI.observe(requireActivity(), {
            when (it) {
                is PostsUI.PostsLoading -> {
                    // TODO - show loading UI
                }
                is PostsUI.PostsSuccess -> {
                    postAdapter.submitList(it.items)
                }
                is PostsUI.PostsFailure -> {
                    // TODO - handle error
                }
            }
        })

        postListViewModel.getPosts()
    }
}