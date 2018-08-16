/*
TITLE : FORGOTTEN QUEST

AUTHOR : GARNET DROPPO

DATE : 8/15/18

VERSION 0.01.1
*/

import java.io.*;
import java.util.*;

public class Game {

    static String commands = "";
    static Player player = new Player();

    //DiceRoll
    static Random rand = new Random();

    //Locations
    static Location start = new Location();
    static Location path = new Location();
    static Location cabin = new Location();
    static Location field = new Location();
    static Location garden = new Location();
    static Location woods = new Location();
    static Location cave = new Location();
    static Location cavern = new Location();
    static Location clearing = new Location();
    static Location woodA = new Location();
    static Location woodB = new Location();
    static Location woodC = new Location();
    static Location woodD = new Location();
    static Location woodE = new Location();
    static Location woodF = new Location();
    static Location elves = new Location();
    static Location wolfDen = new Location();
    static Location escape = new Location();
    static Location entryway = new Location();
    static Location grandHall = new Location();
    static Location rooms = new Location();
    static Location stoneKitchen = new Location();
    static Location wineCellar = new Location();
    static Location mushroomCaves = new Location();
    static Location dragonRoom = new Location();

    static String[] helpReader() throws FileNotFoundException {

        HelpFile text = new HelpFile();

        String line = null;

        try {
            FileReader fileReader =
                    new FileReader("help.txt");

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine())!=null){
                text.add(line);
            }
            bufferedReader.close();
        } catch(FileNotFoundException ex){
            System.out.println("Unable to open file 'help.txt'");
        }
        catch( IOException ex){
            System.out.println("Unable to open file 'help.txt'");
        }

        return text.text;
    }

    public static void main(String[] args) {
        System.out.println("\n\nHELLO! And welcome to FORGOTTEN QUEST!");
        System.out.println("For a list of commands available Please type help\n");
        testLocations();
        for (String line:start.description){
            System.out.println(line);
        }
        System.out.println();
        player.location = start;
        Scanner scanner = new Scanner(System.in);
        while (!commands.toLowerCase().equals("quit")){
            if (scanner.hasNext()) {
                commands = scanner.next();
            }

            parseCommands();
            //lostWoods(rand);
        }
        scanner.close();

    }

    public static void parseCommands(){
        if (commands == "")
            return;
        else if (commands.equals("help")){
            String[] help = null;
            try{
                help = helpReader();
            }catch(FileNotFoundException ex){
                System.out.println("Help File not found" + ex);
                ex.printStackTrace();
            }
            for(String line:help){
                if (line!=null)
                    System.out.println(line);
            }
        }
        else if (commands.toLowerCase().equals("look")){
            System.out.println();
            String[] description = player.location.description;
            if(description!=null){
                for (String line:description){
                    System.out.println(line);
                }
                System.out.println();
            }else{
                System.out.println("You are floating through a void of nothingness");
                System.out.println("You have a feeling of existential dread just being here");
                System.out.println("Please tell 'garnetdroppo@gmail.com' how you got here if you don't think this was on purpose.");
            }
        }

        
        
        //inventory
        else if(commands.toLowerCase().equals("inven")){
            player.printInven();
        }

        //bad word
        else if(commands.toLowerCase().contains("dick")){
            //user suggestion
            System.out.println("You find yourself overcome with the desire to slap yourself in the face.");
        }
        else if(commands.toLowerCase().equals("check")){
            System.out.println(player.location.name);
        }



        //directional commands
        else if ((commands.toLowerCase().equals("west"))||
                 (commands.toLowerCase().equals("east"))||
                 (commands.toLowerCase().equals("south"))||
                 (commands.toLowerCase().equals("north"))){
                        switch (commands.toLowerCase()) {
                            case "north" :
                                go(player.location.north);
                                //lostWoods(rand);
                                break;
                            case "south" :
                                go(player.location.south);
                                //lostWoods(rand);
                                break;
                            case "east" :
                                go(player.location.east);
                                //lostWoods(rand);
                                break;
                            case "west" :
                                go(player.location.west);
                                //lostWoods(rand);
                                break;
                 }
        }


        // location based branches/choices
        else if ((player.location == start)) {
            if (start.hasItem("WALKING STICK")) {
                if ((commands.toLowerCase().contains("stick") || commands.toLowerCase().contains("branch"))) {
                    start.removeItem("WALKING STICK");
                    player.addInven("WALKING STICK");
                    System.out.println("\nYou picked up the WALKING STICK\n");
                }
            }
        }

        else if (player.location == cave) {
            if (cave.hasItem("BLUEBERRIES")) {
                if (commands.toLowerCase().contains("blue") || commands.toLowerCase().contains("berries")) {
                    cave.removeItem("BLUEBERRIES");
                    player.addInven("BLUEBERRIES");
                    System.out.println("\nYou picked up the BLUEBERRIES.\n");
                }

            }
        }


        else if (!commands.toLowerCase().equals("quit")){
            System.out.println("\nI don't understand that command.\n");
        }


    }

    public static void go(Location next){
        //can you go there?
        if (next!=null){
            //art there no requirements?
            if(next.requirements!=null) {
                int i = 0;
                //what are you missing?
                for (String require : next.requirements) {
                    if (!player.invenHas(require)) {
                        System.out.print("You are missing a/an ");
                        System.out.print(require);
                        System.out.println("!");
                        ++i;
                    }
                }
                if (i > 0) return;
            }
            player.location = next;
        }
        //you can't go there
        else{
            System.out.println("You can't go that direction!");
            return;
        }

        //prints descriptions in multiple lines.
        System.out.println();
        if(player.location.description!=null) {
            for (String line : player.location.description) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

    public static void testLocations(){


        //Start's definition
        start.description = new String[]
                {
                    "You are in a clearing in the woods!",
                    "There is a path to your north.",
                    "As well as some woods to the south east and west.",
                    "In the clearing there is a pile of sticks and branches."
                };
        start.name = ("start");
        //places you can go
        start.addNorth(start,path);
        start.addEast(start,woods);
        start.addSouth(start,woods);
        start.addWest(start,woods);
        //Items at start
        start.addItem("WALKING STICK");

        //Path's definition
        path.description = new String[]
                {
                    "You are on a winding path through the woods.",
                    "The clearing is to your south.",
                    "To your east there is some smoke, and to your west is a small field",
                    "The sound of spring comforts your ears."
                };
        path.name = ("path");
        //places you can go
        path.addNorth(path,cave);
        path.addSouth(path,start);
        path.addEast(path,cabin);
        path.addWest(path,field);

        //cabin's definition
        cabin.description = new String[] 
            {
                "You have come across a small cabin in the woods.",
                "The door is locked, but to the east behind it, there appears to be a small garden."
            };
        cabin.name = "cabin";
        cabin.addWest(cabin,path);
        cabin.addEast(cabin,garden);

        //garden's description
        garden.description = new String[] 
            {
                "Behind the cabin is a small garden.",
                "Whoever lives here is growing many different types of fruits and vegitables.",
                "There are carrots lying on the ground that must have been freshly harvested."
            };
        garden.name = "garden";
        garden.addWest(garden,cabin);
        garden.addItem("CARROT");

        //field's defintion
        field.description = new String [] 
            {
                "Congratulations.",
                "You are out standing in this field.",
                "All puns aside, there isn't much to do here."  
            };
        field.name = "field";
        field.addWest(field,path);

        //Wood's definition
        woods.description = new String[]
                {
                  "The woods are dark and dank.",
                  "You seem to be lost"
                };
        woods.name = ("woods");
        //randomizes the woods at the start of the game
        lostWoods(rand);

        //cave's definition
        cave.description = new String[]
                {
                  "You have entered a cave, the wind comes from the entrance to the south.",
                  "There is a whistling coming from a crack in the wall to your north.",
                  "There are elaborate drawings on the eastern and western walls.",
                  "On the ground there is a leather pouch, and inside are some blueberries."
                };

        cave.addSouth(cave,path);
        cave.addNorth(cave,clearing);

        cave.requirements = new String[]{"WALKING STICK"};
        cave.name = ("cave");

        //Items in cave
        cave.addItem("BLUEBERRIES");

        //Cavern's definition
        cavern.description = new String[] 
            {
                "You find yourself in a huge, massive cavern.",
                "There are stalactites and stalagmites protruding from the floor and celing.",
                "The tunnel stretches on to the north and there is a small crack in the wall",
                "to the south that you might be able to slip through."
            };
        cavern.addNorth(cavern, clearing);
        cavern.addSouth(cavern,cave);
        cavern.name = "cavern";

        //Clearing's definition
        clearing.description = new String[] 
            {
                "You find yourself in a clearing in the woods.",
                "There are flowers around you in many different colors, and the plant life seems",
                "    very robust.",
                "To your south there is a tunnel leading to a cavern in the hill.",
                "In every other direction the woods sprawl out in front of you."
            };
        clearing.addNorth(clearing,woodD);
        clearing.addWest(clearing,woodC);
        clearing.addEast(clearing,woodA);
        clearing.addSouth(clearing,cavern);
        clearing.name = "clearing";

        //woodA's definition
        woodA.description = new String[]
            {
                "There is a clearing in the woods to your west.",
                "To your east there is a path that leads to more woods after jogging north.",
                "To the south there is a path that seems quite windy."
            };
        woodA.addEast(woodA,woodB);
        woodA.addWest(woodA,clearing);
        woodA.addSouth(woodA,woodE);
        woodA.name = "woodA";

        //woodB's definition
        woodB.description = new String[]
        {
            "To you north is more woods.",
            "To the east is more woods.",
            "To the west is more woods.",
            "To the south is a path that jogs west."
        };
        woodB.addSouth(woodB,woodA);
        woodB.addNorth(woodB,woodF);
        woodB.addWest(woodB,woodD);
        woodB.addEast(woodB,woodE);
        woodB.name = "woodB";

        //woodC's definition
        woodC.description = new String[] 
        {
            "To your east is a clearing in the woods.",
            "To your west is more woods.",
            "To your north there is a winding path."
        };
        woodC.addNorth(woodC,woodD);
        woodC.addEast(woodC,clearing);
        woodC.addWest(woodC,woodE);
        woodC.name = "woodC";

        //woodD's definition
        woodD.description = new String[]
            {
                "To your north is a winding path.",
                "There is a clearing in the woods to you south.",
                "To your east is more woods."
            };
        woodD.addNorth(woodD,woodC);
        woodD.addSouth(woodD,clearing);
        woodD.addEast(woodD,woodB);
        woodD.name = "wooodD";

        //woodE's definition
        woodE.description = new String[]
            {
                "You hear sweet music coming from the north.",
                "To your east and west there are more woods.",
                "There is a winding path to your south."
            };
        woodE.addNorth(woodE,elves);
        woodE.addEast(woodE,woodC);
        woodE.addSouth(woodE,woodA);
        woodE.addWest(woodE,woodB);
        woodE.name = "Woody";

        //woodF's defintion
        woodF.description = new String[] 
            {
                "To your east there is a glittering in the trees.",
                "To your south there are more woods.",
                "There is a wolf's den to your west."
            };
        woodF.addEast(woodF,elves);
        woodF.addWest(woodF,wolfDen);
        woodF.addSouth(woodF,woodB);
        woodF.name = "woodF";

        //elves's definition
        elves.description = new String[] 
            {
                "You find yourself in a glittering city in the trees, you feel relaxed by sweet music.",
                "It seems as though there was once a great people that once inhabeted these dwellings,",
                "    and maybe they will return some day.",
                "To your south the woods stretch onward.",
                "There is a worn path to the caves in the north.",
                "To the west there is more woods."
            };   
        elves.addSouth(elves,woodE);
        elves.addWest(elves,woodF);
        elves.addNorth(elves,entryway);
        elves.name = "elf city";
        //wolfDen's definition
        wolfDen.description = new String[] 
            {
                "You are standing in the middle of an empty wolf den.",
                "Perhaps someday they will return with a new batch of puppies.",
                "To your north there is a small cave filled with mushrooms.",
                "To your east the woods stretch on."
            };
        wolfDen.addWest(wolfDen,woodF);
        wolfDen.addNorth(wolfDen,mushroomCaves);
        wolfDen.name = "wolf den";

        //mushroomCaves's defintion
        mushroomCaves.description = new String[] 
            {
                "You find yourself in a cave filled with small mushrooms.",
                "To your south there is the Wolf Den"
            };
        mushroomCaves.addSouth(mushroomCaves,wolfDen);
        mushroomCaves.name = "Mushroom Caves";

        //entryway's definition
        entryway.description = new String[] 
            {
                "You find yourself in a grand entryway, with craftsmanship unparalleled.",
                "There are intricate carvings of Dwarves on either side of the room.",
                "There is an exit leading back to the Elven city to the south.",
                "There are majestic iron doors to the north."
            };
        entryway.addSouth(entryway,elves);
        entryway.addNorth(entryway,grandHall);
        entryway.name = "Caves / Entryway";


        //grandHall's definition
        grandHall.description = new String[] 
            {   
                "You are now standing in the grand hall. You can tell that the dwarves who used to live here left in quite a hurry.",
                "There is a table stretching through the center of the room that is still set.",
                "There are doors on the western wall with soot covering the frames.",
                "There are doors on the eastern wall that seem to lead to some sort of living quarters.",
                "To the north there is the entrance to a kitchen made of stone."
            };
        grandHall.addSouth(grandHall,entryway);
        grandHall.addNorth(grandHall,stoneKitchen);
        grandHall.addEast(grandHall,rooms);
        grandHall.addWest(grandHall,dragonRoom);
        grandHall.name = "Grand Hall";

        //rooms's definition
        rooms.description = new String[] 
            {   
                "You are standing in some kind of living quarters.",
                "To your north there is a hallway that reeks of booze.",
                "To your east there are doors to the grand hall."
            };
        rooms.addNorth(rooms,wineCellar);
        rooms.addWest(rooms,grandHall);
        rooms.name = "bedrooms";

        //wineCellar's defintion
        wineCellar.description = new String[] 
            {
                "The room stinks of booze, there are giant barrels of various alchohols scattered around the room.",
                "To the east is a kitchen made out of stone.",
                "To the north there is a hallway that leads back to the living quarters."
            };
        wineCellar.addNorth(wineCellar,rooms);
        wineCellar.addEast(wineCellar,stoneKitchen);
        wineCellar.name = "Wine Cellar";

        //stoneKitchen's defintion
        stoneKitchen.description = new String[] 
            {
                "You are standing in a kitchen made of stone. There is rotten food on the counters, and a scorched area of the wall above the stove.",
                "To the west is the wine cellar.",
                "To the south is the grand hall."
            };
        stoneKitchen.addEast(stoneKitchen, wineCellar);
        stoneKitchen.addSouth(stoneKitchen,grandHall);
        stoneKitchen.name = "Stone Kitchen";

        //dragonRoom's definition
        dragonRoom.description = new String [] 
            {
                "OH nOES tHeRe IS a DrAGoN!!!!! is probably something I would say if there was a dragon in here.",
                "To the North is a hallway leading back to the grand hall.",
                "To the west is a cave system."
            };
        dragonRoom.addNorth(dragonRoom,grandHall);
        dragonRoom.addWest(dragonRoom,escape);
        dragonRoom.name = "Dragon's Room";
        //escape's definition
        escape.description = new String[]
            {
                "You are in a system of caves.",
                "To the east is an open room containing a Dragon."
            };
        escape.addEast(escape,dragonRoom);
        escape.name = "escape route";
    }

    //todo: figure out where I put this so that it randomizes every step.
    static void lostWoods(Random rand){
            int n = rand.nextInt(6)+1;
            //System.out.println("case "+n);
            switch (n) {
                case 1:

                    woods.addNorth(woods,start);
                    woods.addEast(woods,woods);
                    woods.addSouth(woods,woods);
                    woods.addWest(woods,woods);
                    break;
                case 2:

                    woods.addNorth(woods,start);
                    woods.addEast(woods,woods);
                    woods.addWest(woods,woods);
                    woods.addSouth(woods,woods);
                    break;
                case 3:

                    woods.addNorth(woods,woods);
                    woods.addEast(woods,woods);
                    woods.addWest(woods,woods);
                    woods.addSouth(woods,start);
                    break;
                case 4:

                    woods.addNorth(woods,woods);
                    woods.addEast(woods,start);
                    woods.addWest(woods,woods);
                    woods.addSouth(woods,woods);
                    break;
                case 5:

                    woods.addNorth(woods,woods);
                    woods.addEast(woods,start);
                    woods.addWest(woods,woods);
                    woods.addSouth(woods,woods);
                    break;
                case 6:

                    woods.addNorth(woods,woods);
                    woods.addEast(woods,woods);
                    woods.addWest(woods,start);
                    woods.addSouth(woods,woods);
                    break;
                case 7:

                    woods.addNorth(woods,woods);
                    woods.addEast(woods,woods);
                    woods.addWest(woods,start);
                    woods.addSouth(woods,woods);
                    break;
            }
        }


}


class Location{
    public String[] description;
    public String[] requirements;
    public String[] items;
    public Location north;
    public Location south;
    public Location east;
    public Location west;
    public String name;
    private int numItems;

    public Location(){
        description = null;
        north = null;
        south = null;
        east = null;
        west = null;
        requirements = null;
        name = null;
        items = null;
    }

    public void addNorth(Location start, Location to){
        start.north = to;
    }

    public void addSouth(Location start, Location to){
        start.south = to;
    }

    public void addEast(Location start, Location to){
        start.east = to;
    }

    public void addWest(Location start, Location to){
        start.west = to;
    }

    public void addItem(String toAdd){
        if (items == null){
            items = new String[] {toAdd};
            numItems = 1;
        }
        else if(numItems >= items.length){
            String[] temp = new String[items.length*2];
            for (int i = 0; i < items.length;++i){
                temp[i]=items[i];
            }
            temp[numItems] = toAdd;
            ++numItems;
        }
        else{
            items[numItems] = toAdd;
            ++numItems;
        }
    }

    public void removeItem(String toRemove){
        if(items == null){

        }
        else{
            int i = 0;
            while(items[i]!=toRemove){
                ++i;
            }
            if (i == items.length){
                return;
            }
            items[i] = null;
            if(items.length > i+1){
                int j = i+1;
                while(items[j]!=null && j < items.length){
                    ++j;
                }
                items[i] = items[j];
                items[j] = null; 
            }
            --numItems;
            description[description.length-1] = "";
        }
    }

    public boolean hasItem(String toFind){
        for(String thing:items){
            if(thing!=null && thing.equals(toFind)){
                return true;
            }
        }
        return false;
    }

    public void addDescription(String toAdd){
        int i = description.length;
        String[] temp = new String[i+1];
        int j = 0;
        for(String line:description){
            temp[j]=line;
            ++j;
        }
        temp[i] = toAdd;
        description = temp;
    }

}
