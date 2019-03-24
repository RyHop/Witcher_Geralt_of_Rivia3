public class ArmorSet {
    private Armor Chest;
    private Armor Leggings;
    private Armor Helmet;
    private Armor Boots;
    private Armor ExtraPiece;
    private int Value;
    private int Cost;

    public Armor getChest() {
        return Chest;
    }

    public void setChest(Armor chest) {

        this.Chest = chest;
        resetCost();
        resetValue();

    }

    public Armor getLeggings() {
        return Leggings;
    }

    public void setLeggings(Armor leggings) {
        Leggings = leggings;
        resetCost();
        resetValue();
    }

    public Armor getHelmet() {
        return Helmet;
    }

    public void setHelmet(Armor helmet) {
        Helmet = helmet;
        resetCost();
        resetValue();
    }

    public Armor getBoots() {
        return Boots;
    }

    public void setBoots(Armor boots) {
        Boots = boots;
        resetCost();
        resetValue();
    }

    public Armor getExtraPiece() {
        return ExtraPiece;
    }

    public void setExtraPiece(Armor extraPiece) {
        ExtraPiece = extraPiece;
        resetCost();
        resetValue();
    }


    public int getValue() {
        return Value;
    }

    public int getCost() {
        return Cost;
    }


    public ArmorSet(Armor chest, Armor legs, Armor helmet, Armor boots, Armor extrapiece) {
        this.Chest = chest;
        this.Leggings = legs;
        this.Helmet = helmet;
        this.Boots = boots;
        this.ExtraPiece = extrapiece;
        this.Value = Chest.Value + Leggings.Value + Helmet.Value + Boots.Value + ExtraPiece.Value;
        this.Cost = Chest.Cost + Leggings.Cost + Helmet.Cost + Boots.Cost + ExtraPiece.Cost;
    }

    public ArmorSet() {


    }

    public void Empty() {
        this.Chest = null;
        this.Leggings = null;
        this.Helmet = null;
        this.Boots = null;
        this.ExtraPiece = null;
        this.Value = 0;
        this.Cost = 0;

    }

    @Override
    public String toString() {
        return "\n" + Helmet.Name + "\n " + Chest.Name + "\n " + Leggings.Name + "\n " + Boots.Name + "\n " + ExtraPiece.Name;
    }

    private void resetCost() {
        this.Cost = this.Chest.Cost + this.Helmet.Cost + this.Boots.Cost + this.ExtraPiece.Cost + this.Leggings.Cost;
    }

    private void resetValue() {
        this.Value = this.Chest.Value + this.Helmet.Value + this.Boots.Value + this.ExtraPiece.Value + this.Leggings.Value;
    }
}
