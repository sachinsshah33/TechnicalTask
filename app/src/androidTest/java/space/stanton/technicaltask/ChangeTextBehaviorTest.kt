package space.stanton.technicaltask


import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import space.stanton.technicaltask.ui.postDetails.PostDetailActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<PostDetailActivity> =
        ActivityScenarioRule(PostDetailActivity::class.java)

    @Before
    fun initValidString() {
        stringToBetyped = "Post content"
    }

    @Test
    fun populatePostContent() {
        onView(withId(R.id.content))
            .perform(setTextInTextView(stringToBetyped))
        onView(withId(R.id.content))
            .check(matches(withText(stringToBetyped)))
    }

    private fun setTextInTextView(value: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(
                        TextView::class.java
                    )
                )
            }

            override fun perform(uiController: UiController, view: View) {
                (view as TextView).text = value
            }

            override fun getDescription(): String {
                return "replace text"
            }
        }
    }
}