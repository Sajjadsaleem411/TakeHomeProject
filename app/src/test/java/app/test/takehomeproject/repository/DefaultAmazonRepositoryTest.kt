package app.test.takehomeproject.repository

import app.test.takehomeproject.BaseTest
import app.test.takehomeproject.api.ApiService
import app.test.takehomeproject.models.AmazonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class DefaultAmazonRepositoryTest:BaseTest() {
    @InjectMocks
    private lateinit var repository: DefaultAmazonRepository

    @Mock
    private lateinit var service: ApiService

    //When api returns data LoadSuccess is returned
    @ExperimentalCoroutinesApi
    @Test
    fun apiSuccess() = runBlockingTest {
        Mockito.`when`(service.getAmazonList()).thenReturn(AmazonResponse(emptyList()))
        repository.loadData().observeForever {
            assert(it is LoadSuccess)
        }
    }

    //When api fails LoadError is returned
    @ExperimentalCoroutinesApi
    @Test
    fun apiFailed() = runBlockingTest {
        Mockito.`when`(service.getAmazonList()).thenThrow(RuntimeException("API FAILED"))
        repository.loadData().observeForever {
            assert(it is LoadFailure)
        }
    }
}