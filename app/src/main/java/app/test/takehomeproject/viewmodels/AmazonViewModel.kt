package app.test.takehomeproject.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.test.takehomeproject.models.AmazonItem
import app.test.takehomeproject.repository.AmazonRepository
import app.test.takehomeproject.repository.DefaultAmazonRepository
import app.test.takehomeproject.repository.LoadFailure
import app.test.takehomeproject.repository.LoadSuccess

class AmazonViewModel @ViewModelInject constructor(
    private val repository: AmazonRepository
) : ViewModel() {

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _isError: MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> get() = _isError

    suspend fun readAmazonData(): LiveData<List<AmazonItem>> {
        _isLoading.value = true
        _isError.value = false
        return Transformations.map(repository.loadData()) {
            _isLoading.value = false
            when (it) {
                is LoadSuccess -> it.data.results
                is LoadFailure -> {
                    _isError.value = true
                    emptyList()
                }
            }
        }
    }
}