package fr.alegent.ft_hangouts.services

import fr.alegent.ft_hangouts.models.Contact

object ContactsService {
    const val CONTACT_ID_KEY = "contact_id"
    var contacts = mutableListOf<Contact>()
}