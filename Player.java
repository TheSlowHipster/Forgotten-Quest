class Player{

    public String[] inventory;

    public Location location;

    private int invenLength;

    public Player(){
        inventory = null;
        location = new Location();
    }

    public String[] addInven(String toAdd){
        if (inventory == null){
            inventory =  new String[] {toAdd};
            invenLength = 1;
        }
        else if (invenLength >= inventory.length){
            String[] newText = new String[inventory.length*2];
            int counter = 0;
            while (counter < inventory.length){
                newText[counter] = inventory[counter];
                ++counter;
            }
            inventory = newText;
            inventory[invenLength] = toAdd;
            ++invenLength;
        }
        else{
            inventory[inventory.length] = toAdd;
            ++invenLength;
        }
        return inventory;
    }

    public void printInven(){
        System.out.println("*************Inventory****************");
        System.out.println("Your Inventory contains:");
        if(inventory!=null) {
            for (String item : inventory) {
                System.out.println();
                System.out.println(item+"\n");
            }
        }
        else{
            System.out.println("\n"+"\n");
            System.out.println();
            System.out.println();
        }
    }

    public boolean invenHas(String item){
        if(inventory!=null) {
            for (String thing : inventory) {
                if (thing.equals(item))
                    return true;
            }
        }
        return false;
    }

}
