package app.test.takehomeproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import app.test.takehomeproject.api.ApiService
import app.test.takehomeproject.models.AmazonResponse
import javax.inject.Inject


class DefaultAmazonRepository @Inject constructor(private val service: ApiService) :
    AmazonRepository {

    override suspend fun loadData(): LiveData<LoadResult<AmazonResponse>> = liveData {
        try {
            emit(LoadSuccess(service.getAmazonList()))
        } catch (e: Exception) {
            emit(LoadFailure<AmazonResponse>(e.message ?: ""))
        }
    }
}

interface AmazonRepository {
    suspend fun loadData(): LiveData<LoadResult<AmazonResponse>>
}

sealed class LoadResult<T>
data class LoadSuccess<T>(val data: T) : LoadResult<T>()
data class LoadFailure<T>(val error: String) : LoadResult<T>()