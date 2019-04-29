package fr.alegent.ft_hangouts.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import fr.alegent.ft_hangouts.edit_contact.EditContactActivity
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.contact.ContactActivity
import fr.alegent.ft_hangouts.services.ContactsService
import fr.alegent.ft_hangouts.services.ThemeService
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity: AppCompatActivity() {
    private lateinit var datasource: MainAdapter
    private var onStopTime: LocalDateTime? = null
    private var inForeground = false

    override fun onStart() {
        super.onStart()
        val time = onStopTime ?: return
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val greeting = getString(R.string.activity_main_greeting)
        val message = "$greeting ${time.format(formatter)}"
        Toast.makeText(this, message, message.length).show()
        onStopTime = null
    }

    override fun onStop() {
        super.onStop()
        if (inForeground) {
            inForeground = false
            return
        }

        onStopTime = LocalDateTime.now()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeService.current)
        setContentView(R.layout.activity_main)
        add_button.setOnClickListener { handleAdd() }
        datasource = MainAdapter { onItemClick(it) }

        recycler_view.apply {
            setHasFixedSize(true)
            adapter = datasource
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.dark_button -> { handleThemeChange(R.style.DarkTheme) }
            R.id.light_button -> { handleThemeChange(R.style.LightTheme) }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        datasource.notifyDataSetChanged()
    }

    private fun handleAdd() {
        val intent = Intent(this, EditContactActivity::class.java)
        inForeground = true
        startActivity(intent)
    }

    private fun handleThemeChange(theme: Int) {
        val intent = intent
        ThemeService.current = theme
        finish()
        startActivity(intent)
    }

    private fun onItemClick(position: Int) {
        val intent = Intent(this, ContactActivity::class.java)
        intent.putExtra(ContactsService.CONTACT_ID_KEY, position)
        inForeground = true
        startActivity(intent)
    }

}
