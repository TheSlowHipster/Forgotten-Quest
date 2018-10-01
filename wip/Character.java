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

    public addInven (String toAdd)
    {
        if(inven==null)
        {
            inven = new String[5];
        }
        for (i = 0; i < inven.length; ++i)
        {
            if(inven[i]==null)
            {
                inven[i] = toAdd;
            }
            else if(i == inven.length-1)
            {
                temp = new String[inven.length*2];
                for(int j = 0; j < inven.length; ++j)
                {
                    temp[j] = inven[j];
                }
                inven = temp; 
            }
        }
    }
}
