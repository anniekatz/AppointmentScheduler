package Model;

public class Contact {
    private int ContactID;
    private String Name;
    private String Email;

    // Contact constructor
    public Contact(int ContactID, String Name, String Email) {
        this.ContactID = ContactID;
        this.Name = Name;
        this.Email = Email;
    }

    // Get methods
    public int getContactID() { return ContactID; }
    public String getName() { return Name; }
    public String getEmail() {
        return Email;
    }

}
