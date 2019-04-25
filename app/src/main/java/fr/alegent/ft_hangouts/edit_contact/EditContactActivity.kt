package fr.alegent.ft_hangouts.edit_contact

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.services.ContactsService
import kotlinx.android.synthetic.main.activity_edit_contact.*

class EditContactActivity: AppCompatActivity() {
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        val id = intent.getIntExtra(ContactsService.CONTACT_ID_KEY, -1)
        if (id == -1) return
        this.id = id

        val contact = ContactsService.contacts[id]
        title = getString(R.string.activity_edit_contact_edit_contact_title)
        name_text_field.setText(contact.name)
        number_text_field.setText(contact.number)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_contact, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save_button -> { handleSave() }
            R.id.cancel_button -> { finish() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun handleSave() {
        val name = name_text_field.text
        val number = number_text_field.text

        if (name!!.isEmpty()) {
            val message = getString(R.string.activity_edit_contact_empty_name_message)
            showAlert(message)
            return
        }

        if (number!!.isEmpty()) {
            val message = getString(R.string.activity_edit_contact_empty_phone_message)
            showAlert(message)
            return
        }

        val contact = Contact(name.toString(), number.toString())

        if (id != null) {
            ContactsService.contacts[id!!] = contact
        } else {
            ContactsService.contacts.add(contact)
        }

        finish()
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.activity_edit_contact_invalid_contact_title)
            .setMessage(message)
            .setCancelable(true)
            .show()
    }
}
