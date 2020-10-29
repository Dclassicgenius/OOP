
class Phonebook(contacts: MutableSet<ContactInfo>?) {

    private var contacts = contacts ?: mutableSetOf()

    fun addContact(contact: ContactInfo) {
        contacts.add(contact)
    }

    fun getAllContacts() : Set<ContactInfo> {
        return contacts.toSet()
    }

    fun getContact(requiredContact: ContactInfo) : ContactInfo? {
        return contacts.find {contact -> requiredContact == contact}
    }

    fun getContact(index: Int) : ContactInfo {
        if (index < 0 || index >= contacts.size)
            throw IndexOutOfBoundsException("Index is out of range.")

        return contacts.elementAt(index)
    }

    fun deleteContact(contact: ContactInfo) : Boolean {
        return contacts.remove(contact)
    }

    fun deleteContact(index : Int){
        if (index < 0 || index >= contacts.size)
            throw IndexOutOfBoundsException("Index is out of range.")

        val removable = contacts.elementAt(index)

        contacts.remove(removable)
    }

    fun find(pattern : String) : List<ContactInfo>? {
        val found = ArrayList<ContactInfo>()

        for (contact in contacts) {
            if (contact.name.contains(pattern) || contact.surname.contains(pattern)) {
                found.add(contact)
                continue
            }

            for (phoneNumber in contact.getAllPhoneNumbers()) {
                if (phoneNumber.number.contains(pattern)) {
                    found.add(contact)
                    continue
                }
            }

            for (email in contact.getAllEmails()) {
                if (email.address.contains(pattern)) {
                    found.add(contact)
                    continue
                }
            }
        }

        return if (contacts.isEmpty()) null else found.toList()
    }

    override fun toString(): String {
        var phoneBookString = ""
        for (contact in contacts)
            phoneBookString += "$contact\n\n"

        return phoneBookString
    }
}