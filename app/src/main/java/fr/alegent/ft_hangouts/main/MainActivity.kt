package fr.alegent.ft_hangouts.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.alegent.ft_hangouts.edit_contact.EditContactActivity
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.contact.ContactActivity
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.services.ContactsService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    private lateinit var datasource: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_button.setOnClickListener { handleAdd() }
        datasource = MainAdapter { onItemClick(it) }

        recycler_view.apply {
            setHasFixedSize(true)
            adapter = datasource
        }
    }

    override fun onResume() {
        super.onResume()
        datasource.notifyDataSetChanged()
    }

    private fun handleAdd() {
        val intent = Intent(this, EditContactActivity::class.java)
        startActivity(intent)
    }

    private fun onItemClick(position: Int) {
        val intent = Intent(this, ContactActivity::class.java)
        intent.putExtra(ContactsService.CONTACT_ID_KEY, position)
        startActivity(intent)
    }

}
