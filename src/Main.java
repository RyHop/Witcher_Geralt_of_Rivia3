import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static final String HELMET = "helmet";
    private static final String CHEST = "chest";
    private static final String LEGS = "leggings";
    private static final String BOOTS = "boots";

    public static void main(String[] args) {
        //Constructing Armor List and Sets
        ArrayList<Armor> helmetArmor = new ArrayList<>();
        ArrayList<Armor> chestArmor = new ArrayList<>();
        ArrayList<Armor> legArmor = new ArrayList<>();
        ArrayList<Armor> bootsArmor = new ArrayList<>();
        addData(helmetArmor, chestArmor, legArmor, bootsArmor);

        // Menu
        System.out.println("Welcome to the armory. I hear you want to buy the best armor. How many crowns can you commit?");
        Scanner scan = new Scanner(System.in);
        int crowns = scan.nextInt();
        scan.close();
        //Display armor that can be bought
        System.out.println("You have " + crowns);

        System.out.println("The best armor set you can buy is: " + findBestArmor(crowns, helmetArmor, chestArmor, legArmor, bootsArmor));


    }

    public static int change(int hello) {
        return hello += 1;

    }

    public static ArmorSet findBestArmor(int currency, ArrayList<Armor> helmetArmor, ArrayList<Armor> chestArmor, ArrayList<Armor> legArmor, ArrayList<Armor> bootsArmor) {
        int crowns = currency;
        ArrayList<Armor> allArmor = new ArrayList<>();
        allArmor.addAll(helmetArmor);
        allArmor.addAll(chestArmor);
        allArmor.addAll(legArmor);
        allArmor.addAll(bootsArmor);
        allArmor.sort(Armor::compareTo);


        //Keep up which armor to pick and choose from
        ListIterator helmetIt = helmetArmor.listIterator();
        ListIterator chestIt = chestArmor.listIterator();
        ListIterator legArmorIt = legArmor.listIterator();
        ListIterator bootsIt = bootsArmor.listIterator();
        ListIterator allArmorIt = allArmor.listIterator();
        ArmorSet armorSet;
        armorSet = new ArmorSet((Armor) chestIt.next(), (Armor) legArmorIt.next(), (Armor) helmetIt.next(), (Armor) bootsIt.next(), (Armor) allArmorIt.next());
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;


        //Highest Armor
        if (armorSet.getCost() <= currency) {
            return armorSet;
        } else {
            //Figure out the next highest armor
            return highestArmor(armorSet, currency, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, counter1, counter2, counter3, counter4, counter5);

        }


    }

    private static ArmorSet highestArmor(ArmorSet armorSet, int crowns, ArrayList<Armor> helmetArmor, ArrayList<Armor> chestArmor, ArrayList<Armor> legArmor, ArrayList<Armor> bootsArmor, ArrayList<Armor> allArmor, ListIterator helmetIt, ListIterator chestIt, ListIterator legArmorIt, ListIterator bootsIt, ListIterator allArmorIt, int countH, int countC, int countL, int countB, int countA) {

        //Highest Armor
        if (armorSet.getCost() <= crowns) {
            return armorSet;
        } else {
            //Figure out the next highest armor
            //First, determine the armor with the least change in difference
            //First, get the change in difference for each type of armor


            int helmetDifference = helmetdifference(armorSet, helmetIt);
            int chestDifference = chestdifference(armorSet, chestIt);
            int legDifference = legdifference(armorSet, legArmorIt);
            int bootDifference = bootdifference(armorSet, bootsIt);
            int extraArmorDifference = alldifference(armorSet, allArmorIt);

            double helmetVCNext = ((Armor) helmetIt.next()).Vc;
            int tmp = ((Armor) helmetIt.previous()).Value;

            double chestVCNext = ((Armor) chestIt.next()).Vc;
            tmp = ((Armor) chestIt.previous()).Value;

            double legVCNext = ((Armor) legArmorIt.next()).Vc;
            tmp = ((Armor) legArmorIt.previous()).Value;

            double bootVCNext = ((Armor) bootsIt.next()).Vc;
            tmp = ((Armor) bootsIt.previous()).Value;

            double allVCNext = ((Armor) allArmorIt.next()).Vc;
            tmp = ((Armor) allArmorIt.previous()).Value;

            Double maxValue[] = {helmetVCNext, chestVCNext, legVCNext, bootVCNext, allVCNext};
            double max = Collections.max(Arrays.asList(maxValue));

            Integer differences[] = {helmetDifference, chestDifference, legDifference, bootDifference, extraArmorDifference};
            //Find Vc of duplicates
            Integer counters[] = {countA, countB, countC, countH, countL};
            int least = Collections.min(Arrays.asList(counters));


            int min = Collections.min(Arrays.asList(differences));

            try {
                if (min == helmetDifference && helmetVCNext == max) {
                    armorSet.setHelmet((Armor) helmetIt.next());
                    countH += 1;
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

                } else if (min == chestDifference && chestVCNext == max) {
                    countC += 1;
                    armorSet.setChest((Armor) chestIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

                } else if (min == legDifference && legVCNext == max) {
                    countL += 1;
                    armorSet.setLeggings((Armor) legArmorIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

                } else if (min == bootDifference && bootVCNext == max) {
                    countB += 1;
                    armorSet.setBoots((Armor) bootsIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

                } else if (min == extraArmorDifference && allVCNext == max) {
                    countA += 1;
                    armorSet.setExtraPiece((Armor) allArmorIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                } else if (helmetDifference == min) {
                    countH += 1;
                    armorSet.setHelmet((Armor) helmetIt.next());

                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                } else if (chestDifference == min) {
                    countC += 1;
                    armorSet.setChest((Armor) chestIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

                } else if (legDifference == min) {
                    countL += 1;
                    armorSet.setLeggings((Armor) legArmorIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                } else if (bootDifference == min) {
                    countB += 1;
                    armorSet.setBoots((Armor) bootsIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                } else if (extraArmorDifference == min) {
                    armorSet.setExtraPiece((Armor) allArmorIt.next());
                    return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                } else {
                    return null;
                }
            } catch (NoSuchElementException e) {
                System.out.println(" There is nothing you can buy!");
                return null;
            }
        }
    }

    public static int helmetdifference(ArmorSet armorSet, ListIterator iterator) {

        int currentValue = armorSet.getHelmet().getValue();
        int potentialValue = ((Armor) iterator.next()).Value;
        int tmp = ((Armor) iterator.previous()).Value;

        return currentValue - potentialValue;
    }

    public static int chestdifference(ArmorSet armorSet, ListIterator iterator) {
        int currentValue = armorSet.getChest().getValue();
        int potentialValue = ((Armor) iterator.next()).Value;
        int tmp = ((Armor) iterator.previous()).Value;

        return currentValue - potentialValue;
    }

    public static int legdifference(ArmorSet armorSet, ListIterator iterator) {
        int currentValue = armorSet.getLeggings().getValue();
        int potentialValue = ((Armor) iterator.next()).Value;
        int tmp = ((Armor) iterator.previous()).Value;

        return currentValue - potentialValue;
    }

    public static int bootdifference(ArmorSet armorSet, ListIterator iterator) {
        int currentValue = armorSet.getBoots().getValue();
        int potentialValue = ((Armor) iterator.next()).Value;
        int tmp = ((Armor) iterator.previous()).Value;

        return currentValue - potentialValue;
    }

    public static int alldifference(ArmorSet armorSet, ListIterator iterator) {
        int currentValue = armorSet.getExtraPiece().getValue();
        int potentialValue = ((Armor) iterator.next()).Value;
        int tmp = ((Armor) iterator.previous()).Value;

        return currentValue - potentialValue;
    }

    public static void addData(ArrayList<Armor> helmetArmor, ArrayList<Armor> chestArmor, ArrayList<Armor> legArmor, ArrayList<Armor> bootsArmor) {


        //helmets

        helmetArmor.add(new Armor("Keeton Mask", HELMET, 77, 24));
        helmetArmor.add(new Armor("Serpentine Cruz Headpiece", HELMET, 90, 23));
        helmetArmor.add(new Armor("Ornate Helmet of Cagampan", HELMET, 60, 16));
        helmetArmor.add(new Armor("Feline Visor", HELMET, 68, 16));
        helmetArmor.add(new Armor("Offner Protector", HELMET, 54, 15));
        helmetArmor.add(new Armor("Leather Helmet", HELMET, 49, 13));
        helmetArmor.add(new Armor("Silgar's Noggin Protector", HELMET, 46, 12));
        helmetArmor.add(new Armor("Glass Bowl", HELMET, 44, 12));


        //Chest
        chestArmor.add(new Armor("Chestpiece of Vachon", CHEST, 64, 23));
        chestArmor.add(new Armor("Armor de Jandro", CHEST, 67, 22));
        chestArmor.add(new Armor("Kaer Morhen Armor", CHEST, 62, 21));
        chestArmor.add(new Armor("Cured Leather Chestpiece", CHEST, 59, 20));
        chestArmor.add(new Armor("Dented Plate Armor", CHEST, 57, 19));
        chestArmor.add(new Armor("Jeweled Drake Tunic", CHEST, 55, 19));
        chestArmor.add(new Armor("Ginger's Gided Armor", CHEST, 54, 18));
        chestArmor.add(new Armor("Garcia Guard", CHEST, 50, 17));
        chestArmor.add(new Armor("Smith's Plated Chestguard", CHEST, 58, 10));


        //Legs


        legArmor.add(new Armor("Famed Pon Leggings", LEGS, 87, 22));
        legArmor.add(new Armor("Ursine Trousers", LEGS, 78, 18));
        legArmor.add(new Armor("Hanen's Breeches", LEGS, 69, 17));
        legArmor.add(new Armor("Wolven Shinguards", LEGS, 75, 15));
        legArmor.add(new Armor("Tanned Leg Protection", LEGS, 59, 15));
        legArmor.add(new Armor("Mail Emares Leggings", LEGS, 53, 14));
        legArmor.add(new Armor("Tattered Shorts", LEGS, 42, 13));
        legArmor.add(new Armor("Manticore Braces", LEGS, 56, 12));
        legArmor.add(new Armor("Griffin Pants", LEGS, 62, 11));
        legArmor.add(new Armor("Woven Leggings", LEGS, 47, 11));
        legArmor.add(new Armor("Silken Pants", LEGS, 45, 10));

        //Boots

        bootsArmor.add(new Armor("Tate's Spiked Cleats", BOOTS, 52, 20));
        bootsArmor.add(new Armor("Diamond Boots", BOOTS, 64, 18));
        bootsArmor.add(new Armor("Steel Boots", BOOTS, 52, 14));
        bootsArmor.add(new Armor("Leather Lunde Shoes", BOOTS, 35, 7));
        bootsArmor.add(new Armor("Cloth Shoes", BOOTS, 33, 5));


    }
}
