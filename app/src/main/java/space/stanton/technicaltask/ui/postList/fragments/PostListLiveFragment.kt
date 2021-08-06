package space.stanton.technicaltask.ui.postList.fragments

import dagger.hilt.android.AndroidEntryPoint
import space.stanton.technicaltask.data.models.PostsUI

@AndroidEntryPoint
class PostListLiveFragment : PostListBaseFragment() {
    override fun setupPosts() {
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