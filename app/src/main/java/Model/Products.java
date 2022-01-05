package Model;

public class Products {
    private String Name;
    private int Amount;

    public Products(String Name, int Amount) {
        this.Name = Name;
        this.Amount = Amount;


    }

    public Products() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
