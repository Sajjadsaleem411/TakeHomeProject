package app.test.takehomeproject.api

import app.test.takehomeproject.models.AmazonResponse
import retrofit2.http.GET

interface ApiService {
    @GET("default/dynamodb-writer")
    suspend fun getAmazonList(): AmazonResponse
}