package Model;

public class Restaurants {
    private String Name;
    private long Contact;

    public Restaurants(String Name, long Contact) {
        this.Name = Name;
        this.Contact = Contact;


    }

    public Restaurants() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public long getContact() {
        return Contact;
    }

    public void setContact(long contact) {
        Contact = contact;
    }
}
