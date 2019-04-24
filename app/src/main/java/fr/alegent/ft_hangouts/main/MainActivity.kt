package fr.alegent.ft_hangouts.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.alegent.ft_hangouts.new_contact.NewContactActivity
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.models.Contact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    private val datasource = MainAdapter()

    companion object {
        const val NEW_CONTACT = 1
        const val CONTACT_KEY = "contact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AddContactButton.setOnClickListener { handleAdd() }

        ContactsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = datasource
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != NEW_CONTACT && resultCode != RESULT_OK) return

        val contact = data?.extras?.getParcelable<Contact>(CONTACT_KEY) ?: return
        datasource.add(contact)
    }

    private fun handleAdd() {
        val intent = Intent(this, NewContactActivity::class.java)
        startActivityForResult(intent, NEW_CONTACT)
    }

}
