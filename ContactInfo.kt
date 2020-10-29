class ContactInfo(
    var name: String,
    surname: String?,
    private var phoneNumbers: MutableSet<PhoneNumber>,
    emailAddresses: MutableSet<Email>?
) {

    var surname = surname ?: ""
    private var emailAddresses = emailAddresses ?: mutableSetOf()

    fun addPhoneNumber(type: String, number: String) {
        phoneNumbers.add(PhoneNumber(type, number))
    }

    fun getPhoneNumber(type: String, number: String): PhoneNumber? {
        val num = PhoneNumber(type, number)

        return phoneNumbers.find { phoneNumber -> num == phoneNumber}
    }


    fun getAllPhoneNumbers(): Set<PhoneNumber> {
        return phoneNumbers.toSet()
    }


    fun deletePhoneNumber(type: String, number: String) : Boolean {
        return phoneNumbers.remove(PhoneNumber(type, number))
    }


    fun addEmail(type: String, address: String) {
        emailAddresses.add(Email(type, address))
    }

    fun getEmail(type: String, address: String) : Email? {
        val requiredEmail = Email(type, address)
        return emailAddresses.find {email -> requiredEmail == email}
    }


    fun getAllEmails() : Set<Email> {
        return emailAddresses.toSet()
    }

    fun deleteEmail(type: String, address: String) : Boolean {
        return emailAddresses.remove(Email(type, address))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactInfo

        if (name != other.name) return false
        if (surname != other.surname) return false
        if (phoneNumbers != other.phoneNumbers) return false
        if (emailAddresses != other.emailAddresses) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (surname.hashCode() ?: 0)
        result = 31 * result + phoneNumbers.hashCode()
        result = 31 * result + (emailAddresses.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        val nameString = name
        val surnameString = " $surname\n"

        var phoneNumbersString = ""
        for (number in phoneNumbers)
            phoneNumbersString += "$number\n"

        var emailsString = ""
        for (email in emailAddresses)
            emailsString += "$email\n"

        return nameString + surnameString + phoneNumbersString + emailsString
    }

}
