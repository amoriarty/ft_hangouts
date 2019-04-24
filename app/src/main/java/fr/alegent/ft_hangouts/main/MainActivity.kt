package fr.alegent.ft_hangouts.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.alegent.ft_hangouts.new_contact.NewContactActivity
import fr.alegent.ft_hangouts.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    private val datasource = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AddContactButton.setOnClickListener { handleAdd() }

        ContactsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = datasource
        }
    }

    private fun handleAdd() {
        val intent = Intent(this, NewContactActivity::class.java)
        startActivity(intent)
    }

}
