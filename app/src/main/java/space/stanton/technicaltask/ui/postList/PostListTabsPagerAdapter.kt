package space.stanton.technicaltask.ui.postList

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class PostListTabsPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) =
        if (position == 0) PostListLiveFragment() else PostListOfflineFragment()
}