package fr.alegent.ft_hangouts.new_contact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import fr.alegent.ft_hangouts.R

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
            R.id.MenuCancelButton -> { handleCancel() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun handleSave() {
        Log.d("NewContactActivity", "handle save")
    }

    private fun handleCancel() {
        Log.d("NewContactActivity", "handle cancel")
    }
}
