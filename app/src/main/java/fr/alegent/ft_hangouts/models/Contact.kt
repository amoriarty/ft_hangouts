package fr.alegent.ft_hangouts.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(val name: String, val number: String): Parcelable