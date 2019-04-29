package fr.alegent.ft_hangouts.sms

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import fr.alegent.ft_hangouts.R
import fr.alegent.ft_hangouts.services.ContactsService
import fr.alegent.ft_hangouts.services.ThemeService
import kotlinx.android.synthetic.main.activity_sms.*
import java.security.Permission
import java.util.jar.Manifest

class SMSActivity : AppCompatActivity() {
    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeService.current)
        setContentView(R.layout.activity_sms)
        send_button.setOnClickListener { handleSend() }
        id = intent.getIntExtra(ContactsService.CONTACT_ID_KEY, -1)
    }

    private fun handleSend() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
            return
        }

        val contact = ContactsService.contacts[id]
        val manager = SmsManager.getDefault()
        val text = text_field.text.toString()
        manager.sendTextMessage(contact.number, null, text, null, null)
        text_field.text.clear()
    }

    private fun requestPermission() {
        val permissions = arrayOf(android.Manifest.permission.SEND_SMS)
        ActivityCompat.requestPermissions(this, permissions, 123)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != 123 || grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) return
        handleSend()
    }
}
