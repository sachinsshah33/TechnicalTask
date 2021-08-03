package space.stanton.technicaltask

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith
import space.stanton.technicaltask.ui.postList.PostListActivity


@RunWith(AndroidJUnit4ClassRunner::class)
class PostListActivityTest {
    @Test
    fun test_ViewPagerVisibleAndTabsVisible() {
        ActivityScenario.launch(PostListActivity::class.java)
        onView(withId(R.id.pager)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_layout)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}