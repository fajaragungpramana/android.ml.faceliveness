package com.github.fajaragungpramana.faceliveness.data.extension

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

inline fun <T> AppResult<T>.onResultListener(
    onLoading: (Boolean) -> Unit,
    onSuccess: (T?) -> Unit,
    onFailure: (Int?, T?) -> Unit,
    onError: (Throwable) -> Unit
) {
    when (this) {
        is AppResult.OnLoading -> {
            onLoading(this.isLoading)
        }
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
        run.invoke()
    } catch (e: Throwable) {
        AppResult.OnError(e)
    }

fun <T : Any> Flow<AppResult<T>>.flowIO(): Flow<AppResult<T>> =
    onStart { emit(AppResult.OnLoading(isLoading = true)) }
        .onCompletion { emit(AppResult.OnLoading(isLoading = false)) }
        .catch {
            emit(AppResult.OnError(it))
            emit(AppResult.OnLoading(isLoading = false))
        }
        .flowOn(Dispatchers.IO)
