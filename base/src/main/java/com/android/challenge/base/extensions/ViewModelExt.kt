package com.android.challenge.base.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.android.challenge.base.common.exception.AppException
import com.android.challenge.base.di.base

/**
 * Extension function to attach a behavior to any [LiveData]
 *
 * @param liveData The current [LiveData]
 * @param body The action to execute
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

/**
 * Extension function to attach a behavior to fullbankException [LiveData]
 *
 * @param liveData The current [LiveData]
 * @param body The action to execute using the [AppException]
 */
fun <L : LiveData<AppException>> LifecycleOwner.failure(
    liveData: L,
    body: (AppException?) -> Unit
) = liveData.observe(this, Observer(body))

val viewModelFactory by lazy { base().viewModelFactory }

inline fun <reified VM : ViewModel> AppCompatActivity.provideViewModel() = lazy {
    ViewModelProvider(this, viewModelFactory).get(VM::class.java)
}