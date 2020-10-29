

fun main(args : Array<String>) {
    test()
}
fun test() {
    val contact1 = ContactInfo(
        "gardener",
        null,
        mutableSetOf(PhoneNumber("Mobile", "+4(344)6483920")),
        null
    )

    val contact2 = ContactInfo(
        "Collin",
        "Kaplan",
        mutableSetOf(
            PhoneNumber("Mobile", "+7(911)7655844"),
            PhoneNumber("Office", "+7(900)6474747")
        ),
        mutableSetOf(Email("email", "collinkap1@gmail.com"))
    )

    val contact3 = ContactInfo(
        "Newman",
        "Wade",
        mutableSetOf(
            PhoneNumber("Mobile", "+7(500)2385928"),
            PhoneNumber("Home", "+7(350)7659375")
        ),
        mutableSetOf(Email("company","descogroups@co.ng"))
    )

    val contact4 = ContactInfo(
        "Trainer",
        null,
        mutableSetOf(
            PhoneNumber("Mobile", "+7(911)8574766")
        ),
        mutableSetOf(
            Email("Email", "idealmanny@yahoo.com")
        )
    )

    val phoneBook = Phonebook(mutableSetOf(contact1, contact2, contact3, contact4))

    // Print all contacts
    println("All contacts in phonebook")
    println(phoneBook)

    // remove contact
    phoneBook.deleteContact(ContactInfo(
        "Newman",
        "Wade",
        mutableSetOf(
            PhoneNumber("Mobile", "+7(500)2385928"),
            PhoneNumber("Home", "+7(350)7659375")
        ),
        mutableSetOf(Email("company","descogroups@co.ng"))
    ))
    //print contacts after deleting
    println("Phonebook after deleting a contact (Newman Wade)")
    println("\n$phoneBook")

    // show all contacts that match the pattern
    println("Contacts that matches the pattern [ai]")
    println(phoneBook.find("ai"))

    // Print all contacts and  edit a contact
    println("Phonebook before editing and adding new contacts")
    println("\n$phoneBook")

    // Change gardener to James
    val newEdit = phoneBook.getContact(0)
    newEdit.name = "James"

    // add new contact
    val newContact = ContactInfo (
        "Patrick",
        "Steel",
        mutableSetOf(PhoneNumber("Mobile", "+234(813)366987")),
        mutableSetOf(
            Email("Email", "patrickstormy@mail.ru")
        )
    )
    phoneBook.addContact(newContact)
    println("Phonebook after editing and adding new contacts")
    println("\n$phoneBook")

}
