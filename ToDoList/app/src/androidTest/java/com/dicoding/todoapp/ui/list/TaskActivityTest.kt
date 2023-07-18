package com.dicoding.todoapp.ui.list

import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.junit.Rule
import org.junit.Test
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import junit.framework.TestCase

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
class TaskActivityTest {

    @get: Rule
    var activityRule = ActivityScenarioRule(TaskActivity::class.java)

    @Test
    fun checkAddTask() {
        Espresso.onView(ViewMatchers.withId(R.id.fab)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(ViewActions.click())
        }

        val addTask = getAddTaskActivity()
        TestCase.assertTrue(addTask?.javaClass == AddTaskActivity::class.java)
    }

    private fun getAddTaskActivity(): Activity? {
        var activity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                activity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)
            }
        }
        return activity
    }
}