package fr.alegent.ft_hangouts.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class SMSReceiver: BroadcastReceiver() {

    companion object {
        const val SMS_BUNDLE = "pdus"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        messages.forEach {
            Toast.makeText(context, it.messageBody, it.messageBody.length).show()
        }
    }

}