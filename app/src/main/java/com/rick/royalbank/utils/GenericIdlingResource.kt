package com.rick.royalbank.utils

import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class GenericIdlingResource : IdlingResource {

    private var mCallback: ResourceCallback? = null

    private val mIsIdleNow = AtomicBoolean(true)

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        mCallback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow && mCallback != null) {
            mCallback!!.onTransitionToIdle()
        }
    }
}