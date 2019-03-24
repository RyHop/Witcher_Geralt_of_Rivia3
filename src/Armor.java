public class Armor implements Comparable {
    String Name, Type;
    int Cost, Value;
    double Vc;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public double getVc() {
        return Vc;
    }

    public void setVc(float vc) {
        Vc = vc;
    }

    public Armor(String name, String type, int cost, int value) {
        this.Name = name;
        this.Type = type;
        this.Cost = cost;
        this.Value = value;
        this.Vc = (double) Value / Cost;
    }

    @Override
    public int compareTo(Object o) {
        int comparing = ((Armor) o).getValue();
        return comparing - this.Value;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
