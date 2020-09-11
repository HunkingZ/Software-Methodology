public class GroceryItem {
    public String name;

    public double price;

    public boolean taxable;

    public GroceryItem(String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }
    //public boolean equals(Object obj) {}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name + " :");
        sb.append(" $" + String.valueOf(price) + " :");
        if (taxable) {
            sb.append(" is taxable");
        } else {
            sb.append(" tax free");
        }
        return sb.toString();
    }

    public boolean equals(GroceryItem compare2) {
        if (!name.equals(compare2.name) || price != compare2.price || taxable != compare2.taxable) {
            return false;
        }
        return true;
    }
}
