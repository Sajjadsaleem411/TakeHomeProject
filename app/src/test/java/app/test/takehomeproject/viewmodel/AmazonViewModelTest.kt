package app.test.takehomeproject.viewmodel

import androidx.lifecycle.liveData
import app.test.takehomeproject.BaseTest
import app.test.takehomeproject.models.AmazonItem
import app.test.takehomeproject.models.AmazonResponse
import app.test.takehomeproject.repository.AmazonRepository
import app.test.takehomeproject.repository.LoadFailure
import app.test.takehomeproject.repository.LoadResult
import app.test.takehomeproject.repository.LoadSuccess
import app.test.takehomeproject.viewmodels.AmazonViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class AmazonViewModelTest : BaseTest() {

    @Mock
    lateinit var repository: AmazonRepository

    lateinit var viewModel: AmazonViewModel

    @Before
    fun setup() {
        viewModel = AmazonViewModel(repository)
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `When data is loaded successfully, loader is stopped, nonEmpty list is returned`() = runBlockingTest {
        Mockito.`when`(repository.loadData()).thenReturn(liveData<LoadResult<AmazonResponse>> {
            emit(LoadSuccess(getDummyList()))
        })
        viewModel.readAmazonData().observeForever {
            assert(it.isNotEmpty())
        }
        assert(viewModel.isLoading.value == false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When data is requested, loader is set true, errors are cleared`() = runBlockingTest {
        viewModel.isLoading.observeForever {
            assert(it)
        }
        viewModel.isError.observeForever {
            assert(!it)
        }
        viewModel.readAmazonData()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When data request fails, error is set, loader is stopped and empty list is returned`() = runBlockingTest {
        Mockito.`when`(repository.loadData()).thenReturn(liveData<LoadResult<AmazonResponse>> {
            emit(LoadFailure(""))
        })
        viewModel.readAmazonData().observeForever {
            assert(it.isEmpty())
        }
        assert(viewModel.isError.value == true)
        assert(viewModel.isLoading.value == false)
    }

    private fun getDummyList() =
        AmazonResponse(listOf(AmazonItem("", "", "", emptyList(), emptyList())))
}