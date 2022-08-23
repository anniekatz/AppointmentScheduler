package Model;

// Model to represent a contact
public class Contact {
    private int contactID;
    private String name;
    private String email;

    // Contact constructor
    public Contact(int contactID, String name, String email) {
        this.contactID = contactID;
        this.name = name;
        this.email = email;
    }

    // Get contact information methods
    public int getContactID() { return contactID; }
    public String getName() { return name; }
    public String getEmail() {
        return email;
    }

}
