package app.test.takehomeproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.test.takehomeproject.databinding.ItemAmazonBinding
import app.test.takehomeproject.models.AmazonItem

class AmazonAdapter(private val clickListener: (AmazonItem) -> Unit) :
    ListAdapter<AmazonItem, AmazonsItemViewHolder>(AmazonsItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmazonsItemViewHolder {
        return AmazonsItemViewHolder(
            ItemAmazonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AmazonsItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

}

class AmazonsItemViewHolder(
    private val binding: ItemAmazonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AmazonItem) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}


private class AmazonsItemDiffCallback : DiffUtil.ItemCallback<AmazonItem>() {
    override fun areItemsTheSame(oldItem: AmazonItem, newItem: AmazonItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AmazonItem, newItem: AmazonItem): Boolean {
        return oldItem == newItem
    }
}
