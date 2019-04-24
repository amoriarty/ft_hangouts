package fr.alegent.ft_hangouts.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.R

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var items = arrayOf<Contact>()

    fun add(item: Contact) {
        items += item
        notifyItemInserted(items.size - 1)
    }

    // MARK:- Recycler View Adapter

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val contactNameTextView: TextView = itemView.findViewById(R.id.ContactNameTextView)
        val contactNumberTextView: TextView = itemView.findViewById(R.id.ContactNumberTextView)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        val cell = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_cell, parent, false)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactNameTextView.text = items[position].name
        holder.contactNumberTextView.text = items[position].number
    }

}