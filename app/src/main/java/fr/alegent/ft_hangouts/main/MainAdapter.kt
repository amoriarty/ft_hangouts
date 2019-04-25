package fr.alegent.ft_hangouts.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.services.ContactsService

class MainAdapter(private val onItemClick: (position: Int) -> Unit):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val items: MutableList<Contact>
        get() = ContactsService.contacts

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById(R.id.name_text_view)
        val number: TextView = itemView.findViewById(R.id.number_text_view)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        val cell = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_main_contact_row, parent, false)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.number.text = items[position].number
        holder.itemView.setOnClickListener { onItemClick(position) }
    }

}