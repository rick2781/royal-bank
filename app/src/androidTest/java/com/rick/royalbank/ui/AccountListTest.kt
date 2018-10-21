package com.rick.royalbank.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rick.royalbank.R
import com.rick.royalbank.ui.accountlist.AccountListActivity
import com.rick.royalbank.ui.accountlist.AccountListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AccountListTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<AccountListActivity>(AccountListActivity::class.java)

    @Test
    fun accountClicked_displayDetail() {

        //TODO: Replace Thread.sleep() with IdlingResource
        Thread.sleep(2000L)
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<AccountListAdapter.ViewHolder>(
                        3,
                        click()))
    }
}