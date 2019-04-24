package fr.alegent.ft_hangouts.new_contact

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.main.MainActivity
import fr.alegent.ft_hangouts.models.Contact
import kotlinx.android.synthetic.main.activity_new_contact.*

class NewContactActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_contact, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.MenuSaveButton -> { handleSave() }
            R.id.MenuCancelButton -> { finish() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun handleSave() {
        val name = NewContactNameTextField.text
        val number = NewContactPhoneTextField.text

        if (name!!.isEmpty()) {
            val message = getString(R.string.new_contact_empty_name_message)
            showAlert(message)
            return
        }

        if (number!!.isEmpty()) {
            val message = getString(R.string.new_contact_empty_phone_message)
            showAlert(message)
            return
        }

        val contact = Contact(name.toString(), number.toString())
        val intent = Intent()
        intent.putExtra(MainActivity.CONTACT_KEY, contact)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.new_contact_invalid_contact_title)
            .setMessage(message)
            .setCancelable(true)
            .show()
    }
}
