package com.wangmir.calliope.testutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

/*
 * Need to pass coroutineScope by this with using runBlocking.
 */
suspend fun <T> Flow<T>.getOrAwaitValue(
    coroutineScope: CoroutineScope,
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    dispatcher: CoroutineDispatcher = StandardTestDispatcher()
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val job = coroutineScope.launch(dispatcher) {
        this@getOrAwaitValue.collect {
            data = it
            latch.countDown()
        }
    }

    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    job.cancel()

    @Suppress("UNCHECKED_CAST")
    return data as T
}
