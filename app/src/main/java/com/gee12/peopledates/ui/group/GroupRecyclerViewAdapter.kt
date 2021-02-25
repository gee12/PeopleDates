package com.gee12.peopledates

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import com.gee12.peopledates.data.model.Group


class GroupRecyclerViewAdapter(
//    private val imageLoader: ImageLoader,
    private val onGroupClicked: (id: Int) -> Unit,
    private val onGroupEditClicked: (id: Int) -> Unit
) : ListAdapter<Group, GroupRecyclerViewAdapter.ViewHolder>(GroupsCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent)
        holder.itemView.setOnClickListener {
            onGroupClicked(holder.itemView.tag as Int)
        }
        return holder
    }

    override fun getItemId(position: Int): Long {
//        return (getItem(position).id ?: 0).toLong()
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_group, parent, false)
    ) {
        private val nameView: TextView? = itemView.findViewById(R.id.tv_group_name)
        private val urlView: TextView? = itemView.findViewById(R.id.tv_group_url)
        private val editView: ImageButton? = itemView.findViewById(R.id.ib_group_edit)

        fun bind(group: Group) {
            itemView.tag = group.id!!
            nameView?.text = group.name
            urlView?.text = group.url
//            imageView?.load(group.imageUrl, imageLoader) {
//                crossfade(true)
//                placeholder(android.R.drawable.ic_menu_gallery)
//                error(android.R.drawable.ic_menu_delete)
//                transformations(CircleCropTransformation())
//            }
            editView?.setOnClickListener {
                onGroupEditClicked(group.id!!)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + nameView?.text + "'"
        }
    }
}

class GroupsCallback : DiffUtil.ItemCallback<Group>() {
    override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
        return oldItem == newItem
    }
}