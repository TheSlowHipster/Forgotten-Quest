public class Character {
    public String[] inven;
    public int health;
    public int gold;

    public Character(String[] inv, int health, int gold) {
        for (String item : inv) {
            addInven(item);
        }
        this.health = health;
        this.gold = gold;
    }

    public Character() {

    }

    public void addInven(String toAdd) {
        if (inven == null) {
            inven = new String[5];
        }
        for (int i = 0; i < inven.length; ++i) {
            if (inven[i] == null) {
                inven[i] = toAdd;
            } else if (i == inven.length - 1) {
                String[] temp = new String[inven.length * 2];
                for (int j = 0; j < inven.length; ++j) {
                    temp[j] = inven[j];
                }
                inven = temp;
            }
        }
    }
}
