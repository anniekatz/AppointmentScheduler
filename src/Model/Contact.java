package Model;

/**
 * This class represents the model for a contact.
 */
public class Contact {

    private int contactID;
    private String name;
    private String email;

    /**
     * This is the constructor for a contact.
     * @param contactID Int value of contact ID
     * @param name String value of contact name
     * @param email String value of contact email
     */
    public Contact(int contactID, String name, String email) {
        this.contactID = contactID;
        this.name = name;
        this.email = email;
    }

    /**
     * This method gets contact ID
     * @return int contact ID
     */
    public int getContactID() { return contactID; }

    /**
     * This method gets name
     * @return String value of contact name
     */
    public String getName() { return name; }

    /**
     * This method gets email
     * @return String value of contact email
     */
    public String getEmail() {return email;}

}
