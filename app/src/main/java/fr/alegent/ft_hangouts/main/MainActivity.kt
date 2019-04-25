package fr.alegent.ft_hangouts.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.alegent.ft_hangouts.edit_contact.EditContactActivity
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.services.ContactsService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    private lateinit var datasource: MainAdapter

    companion object {
        const val NEW_CONTACT = 0
        const val CONTACT_KEY = "contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_button.setOnClickListener { handleAdd() }
        datasource = MainAdapter { ContactsService.contacts }

        recycler_view.apply {
            setHasFixedSize(true)
            adapter = datasource
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != NEW_CONTACT && resultCode != RESULT_OK) return

        val contact = data?.extras?.getParcelable<Contact>(CONTACT_KEY) ?: return
        ContactsService.contacts+= contact
        datasource.notifyItemInserted(ContactsService.contacts.size - 1)
    }

    private fun handleAdd() {
        val intent = Intent(this, EditContactActivity::class.java)
        startActivityForResult(intent, NEW_CONTACT)
    }

}
