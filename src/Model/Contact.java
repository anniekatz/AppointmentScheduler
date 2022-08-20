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
    public int GetContactID() {
        return ContactID;
    }
    public String GetName() {
        return Name;
    }
    public String GetEmail() {
        return Email;
    }

}
