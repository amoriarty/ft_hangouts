package fr.alegent.ft_hangouts.contact

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.edit_contact.EditContactActivity
import fr.alegent.ft_hangouts.main.MainActivity
import fr.alegent.ft_hangouts.models.Contact
import fr.alegent.ft_hangouts.services.ContactsService
import fr.alegent.ft_hangouts.services.ThemeService
import fr.alegent.ft_hangouts.sms.SMSActivity
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeService.current)
        setContentView(R.layout.activity_contact)
        id = intent.getIntExtra(ContactsService.CONTACT_ID_KEY, -1)
        sms_button.setOnClickListener { handleSMS() }
        edit_button.setOnClickListener { handleEdit() }
        delete_button.setOnClickListener { handleDelete() }
    }

    override fun onResume() {
        super.onResume()
        val contact = ContactsService.contacts[id]
        title = contact.name
        name_text_view.text = contact.name
        number_text_view.text = contact.number
    }

    private fun handleSMS() {
        val intent = Intent(this, SMSActivity::class.java)
        intent.putExtra(ContactsService.CONTACT_ID_KEY, id)
        startActivity(intent)
    }

    private fun handleEdit() {
        val intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra(ContactsService.CONTACT_ID_KEY, id)
        startActivity(intent)
    }

    private fun handleDelete() {
        ContactsService.contacts.removeAt(id)
        finish()
    }

}
