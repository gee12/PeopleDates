package com.gee12.peopledates

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.gee12.peopledates.data.model.Person
import com.gee12.peopledates.utils.Utils


class PersonRecyclerViewAdapter(
    private val imageLoader: ImageLoader,
    private val onPersonClicked: (id: Int) -> Unit
) : ListAdapter<Person, PersonRecyclerViewAdapter.ViewHolder>(PersonsCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent)
        holder.itemView.setOnClickListener {
            onPersonClicked(holder.itemId.toInt())
        }
        return holder
    }

    override fun getItemId(position: Int): Long {
        return (getItem(position).id ?: 0).toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_person, parent, false)
    ) {
        private val dateBirthView: TextView? = itemView.findViewById(R.id.tv_person_date_birth)
        private val dateDeathView: TextView? = itemView.findViewById(R.id.tv_person_date_death)
        private val ageView: TextView? = itemView.findViewById(R.id.tv_person_age)
        private val nameView: TextView? = itemView.findViewById(R.id.tv_person_name)
        private val profView: TextView? = itemView.findViewById(R.id.tv_person_prof)
        private val imageView: ImageView? = itemView.findViewById(R.id.iv_person_photo)

        fun bind(person: Person) {
            dateBirthView?.text = Utils.dateToString(person.dateBirth, "%1\$te %1\$tb %1\$tY")
            dateDeathView?.text = Utils.dateToString(person.dateDeath, "%1\$te %1\$tb %1\$tY")
            ageView?.text = "(${person.age})"
            nameView?.text = person.name
            profView?.text = person.prof
            imageView?.load(person.imageUrl, imageLoader) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_delete)
                transformations(CircleCropTransformation())
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + nameView?.text + "'"
        }
    }
}

class PersonsCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}