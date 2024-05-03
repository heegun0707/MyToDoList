/*
 * Copyright (c) 2016-2024 HYUNDAI HT Co., Ltd. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 * HYUNDAI HT ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with HYUNDAI HT.
 */

package hee.study.hiltstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hee.study.hiltstudy.databinding.ItemNewsBinding
import hee.study.hiltstudy.model.NewsItem

class NewsAdapter(private val onItemClickListener: (String) -> Unit) :
    ListAdapter<NewsItem, NewsAdapter.NewsViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClickListener)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem, onItemClickListener: (String) -> Unit) {
            binding.newsItem = newsItem
            itemView.setOnClickListener {
                onItemClickListener(newsItem.link ?: "")
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}