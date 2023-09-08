package com.example.testhotels.ui

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

object PhoneNumberFormatter {

    fun formatPhoneNumber(phoneNumber: String): String {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val phoneNumberObj = phoneNumberUtil.parse(phoneNumber, "RU")

            return phoneNumberUtil.format(phoneNumberObj, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
        } catch (e: NumberParseException) {
            e.printStackTrace()
        }

        return phoneNumber
    }
}
