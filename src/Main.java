import java.util.*;

public class Main {
    //Definitions to define which armor type
    private static final String HELMET = "helmet";
    private static final String CHEST = "chest";
    private static final String LEGS = "leggings";
    private static final String BOOTS = "boots";

    //Driver Method
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


    //Initiate algorithm of building the most best armor Set
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

    //Used to help figure out next highest Armor Set, if can't buy, recursively call it
    private static ArmorSet highestArmor(ArmorSet armorSet, int crowns, ArrayList<Armor> helmetArmor, ArrayList<Armor> chestArmor, ArrayList<Armor> legArmor, ArrayList<Armor> bootsArmor, ArrayList<Armor> allArmor, ListIterator helmetIt, ListIterator chestIt, ListIterator legArmorIt, ListIterator bootsIt, ListIterator allArmorIt, int countH, int countC, int countL, int countB, int countA) {

        //Highest Armor
        if (armorSet.getCost() <= crowns) {
            return armorSet;
        } else {

            //First, get the change in difference for each type of armor


            int helmetDifference = helmetdifference(armorSet, helmetIt);
            int chestDifference = chestdifference(armorSet, chestIt);
            int legDifference = legdifference(armorSet, legArmorIt);
            int bootDifference = bootdifference(armorSet, bootsIt);
            int extraArmorDifference = alldifference(armorSet, allArmorIt);

            //Get the Value/Cost for making best decision between minimal differences
            //tmp is used to return the iterator to previous posistion
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

            //What is the smallest differences
            Integer differences[] = {helmetDifference, chestDifference, legDifference, bootDifference, extraArmorDifference};
            int min = Collections.min(Arrays.asList(differences));


            //what is the best V:C
            Double maxValue[] = {helmetVCNext, chestVCNext, legVCNext, bootVCNext, allVCNext};
            double max = Collections.max(Arrays.asList(maxValue));

            //Getting the minimal change in armor Value
            ArrayList<Integer> minimals = new ArrayList<>();
            for (int k = 0; k < differences.length; k++) {
                if (differences[k] == min) {
                    minimals.add(k); //Putting in indexes
                }
            }
            //Get an array of the Vc with minimal
            ArrayList<Double> maximals = new ArrayList<>();
            for (int y = 0; y < minimals.size(); y++) {
                maximals.add(maxValue[minimals.get(y)]); //Minimals have indexes of armor who's smallest. take those indexes, and take the respective maximal VC value
            }
            //Find the highest Vc that we know is a minimal
            Double maxVc = maximals.get(0);
            for (int p = 0; p < maximals.size(); p++) {
                if (maxVc < maximals.get(p)) {
                    maxVc = maximals.get(p);
                }

            }
            //Get the position of the maxValue in the maxValue array

            int position = 0;
            for (int r = 0; r < maxValue.length; r++) {
                if (maxVc == maxValue[r]) {
                    position = r; //return the position
                }
            }
            //Now since we have the maximal Vc value of the smallest difference, set whoever has the maxVc value to the change in armor
            //Intuitively, the maxVc value corresponds to the smallest change in difference..so just grab whoever maxVc value equals the armos value
            //Since we kept track of indexes, we know the position equals a certain armor

            try {
                switch (position) {
                    case 0:
                        // 0 is helmet
                        if (helmetIt.hasNext()) {
                            armorSet.setHelmet((Armor) helmetIt.next());
                            countH += 1;

                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        } else {
                            //Render inactive..so set a dummy armor at the end of respective array..so even if has minimal..it wont get picked again.
                            helmetArmor.add(new Armor("IGNORE", "IGNORE", Integer.MAX_VALUE, Integer.MIN_VALUE));
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        }

                    case 1:
                        //1 is chest
                        if (chestIt.hasNext()) {
                            armorSet.setChest((Armor) chestIt.next());
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        } else {
                            //Render inactive..so set a dummy armor at the end of respective array..so even if has minimal..it wont get picked again.
                            chestArmor.add(new Armor("IGNORE", "IGNORE", Integer.MAX_VALUE, Integer.MIN_VALUE));
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        }
                    case 2:
                        //2 is legs
                        if (legArmorIt.hasNext()) {
                            countL += 1;
                            armorSet.setLeggings((Armor) legArmorIt.next());
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        } else {
                            //Render inactive..so set a dummy armor at the end of respective array..so even if has minimal..it wont get picked again.
                            legArmor.add(new Armor("IGNORE", "IGNORE", Integer.MAX_VALUE, Integer.MIN_VALUE));
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        }
                    case 3:
                        //3 is boots
                        if (bootsIt.hasNext()) {
                            countB += 1;
                            armorSet.setBoots((Armor) bootsIt.next());
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        } else {
                            //Render inactive..so set a dummy armor at the end of respective array..so even if has minimal..it wont get picked again.
                            bootsArmor.add(new Armor("IGNORE", "IGNORE", Integer.MAX_VALUE, Integer.MIN_VALUE));
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        }
                    case 4:
                        //Four is extra piece
                        if (allArmorIt.hasNext()) {
                            countA += 1;
                            armorSet.setExtraPiece((Armor) allArmorIt.next());
                        } else {
                            //Render inactive..so set a dummy armor at the end of respective array..so even if has minimal..it wont get picked again.
                            allArmor.add(new Armor("IGNORE", "IGNORE", Integer.MAX_VALUE, Integer.MIN_VALUE));
                            return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);
                        }
                    default:
                        //Default extra piece because its the safest best of trying all the armor before giving up
                        countA += 1;
                        armorSet.setExtraPiece((Armor) allArmorIt.next());
                        return highestArmor(armorSet, crowns, helmetArmor, chestArmor, legArmor, bootsArmor, allArmor, helmetIt, chestIt, legArmorIt, bootsIt, allArmorIt, countH, countC, countL, countB, countA);

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

    //Inventory list from website. Pre-sorted to avoid confusion.
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
