package app.test.takehomeproject.models


import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class AmazonResponse(
    @Json(name = "results") val results: List<AmazonItem>
) : Serializable

@Keep
data class AmazonItem(
    @Json(name = "uid") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "price") val price: String,
    @Json(name = "image_urls_thumbnails") val thumbnailUrls: List<String>,
    @Json(name = "image_urls") val fullImageUrls: List<String>
) : Serializable {
    val thumbnail = thumbnailUrls.firstOrNull()
    val fullImageUrl = fullImageUrls.firstOrNull()
}