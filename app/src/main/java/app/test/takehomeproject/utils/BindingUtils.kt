package app.test.takehomeproject.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.test.takehomeproject.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.bindImageViewImage(source: String) {
    Picasso.get().load(source).placeholder(R.drawable.ic_thumb_placeholder).into(this)
}

@BindingAdapter("isVisible")
fun View.bindVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}