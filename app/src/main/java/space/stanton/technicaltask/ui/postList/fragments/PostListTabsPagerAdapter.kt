package space.stanton.technicaltask.ui.postList.fragments

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import space.stanton.technicaltask.ui.postList.fragments.PostListLiveFragment
import space.stanton.technicaltask.ui.postList.fragments.PostListOfflineFragment


class PostListTabsPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) =
        if (position == 0) PostListLiveFragment() else PostListOfflineFragment()
}