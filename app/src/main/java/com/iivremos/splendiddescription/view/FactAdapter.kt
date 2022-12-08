package com.iivremos.splendiddescription.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iivremos.splendiddescription.R
import com.iivremos.splendiddescription.data.model.FactResponseItem
import com.iivremos.splendiddescription.databinding.FactItemBinding
import com.iivremos.splendiddescription.other.RENDER_DISTANCE

class FactAdapter(
    private val onEndReached: () -> Unit
) : ListAdapter<FactResponseItem, FactAdapter.FactViewHolder>(FactDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(
            FactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        if (itemCount - position == RENDER_DISTANCE) {
            onEndReached()
        }
        var liked = false
        var rated = false
        holder.binding.fact.text = getItem(position).fact
        holder.binding.like.setOnClickListener {
            liked = if (!liked) {
                holder.binding.like.setImageResource(
                    R.drawable.liked
                )
                true
            } else {
                holder.binding.like.setImageResource(
                    R.drawable.unliked
                )
                false
            }
        }
        holder.binding.rate.setOnClickListener {
            rated = if (!rated) {
                holder.binding.rate.setImageResource(
                    R.drawable.rated
                )
                true
            } else {
                holder.binding.rate.setImageResource(
                    R.drawable.unrated
                )
                false
            }
        }
    }

    class FactViewHolder(val binding: FactItemBinding) : RecyclerView.ViewHolder(binding.root)
}

class FactDiffCallBack : DiffUtil.ItemCallback<FactResponseItem>() {
    override fun areItemsTheSame(oldItem: FactResponseItem, newItem: FactResponseItem): Boolean {
        return oldItem.fact == newItem.fact
    }

    override fun areContentsTheSame(oldItem: FactResponseItem, newItem: FactResponseItem): Boolean {
        return oldItem == newItem
    }

}