package com.github.fajaragungpramana.faceliveness.data.extension

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

inline fun <T> AppResult<T>.onResultListener(
    onSuccess: (T?) -> Unit,
    onFailure: (Int?, T?) -> Unit,
    onError: (Throwable) -> Unit
) {
    when (this) {
        is AppResult.OnSuccess -> {
            onSuccess(this.data)
        }
        is AppResult.OnFailure -> {
            onFailure(this.code, this.data)
        }
        is AppResult.OnError -> {
            onError(this.throwable)
        }
    }
}

inline fun <T> connection(run: () -> AppResult<T>): AppResult<T> =
    try {
        run()
    } catch (e: Throwable) {
        AppResult.OnError(e)
    }

suspend fun <T> Flow<T>.flowAsValue(): T? {
    val job = CoroutineScope(Dispatchers.IO).async {
        var value: T? = null

        this@flowAsValue.collectLatest { value = it }

        value
    }

    return job.await()
}
