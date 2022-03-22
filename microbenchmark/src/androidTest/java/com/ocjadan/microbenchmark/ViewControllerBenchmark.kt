package com.ocjadan.microbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ocjadan.benchmarkable.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class ViewControllerBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun benchmark_init_questionsListViewController() {
        activityRule.scenario.onActivity {
            benchmarkRule.measureRepeated {
                it.initQuestionsListVC()
            }
        }
    }

    @Test
    fun benchmark_init_navDrawerViewController() {
        activityRule.scenario.onActivity {
            benchmarkRule.measureRepeated {
                it.initNavDrawer()
            }
        }
    }
}