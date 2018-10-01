


/*
TITLE : FORGOTTEN QUEST

AUTHOR : GARNET DROPPO

DATE : 8/15/18

VERSION 0.02.0
*/

import java.io.*;
import java.util.*;

public class Game {
    static String commands;
    static Player player;
    st atic World world;
 
    // DiceRoll
    st atic Random rand;
 
    static Menu menu;

    public static void main(String[] args) {

        Menu.welcome();

    parseCommands();if(commands=="")return;else if(commands.equals("help"))
    {
        strin[] help = null;
        try {
            help = helpReader();
        } catch (FileNotFoundException ex) {
            System.out.println("Help File Not found" + ex);
            ex.printStackTrace();
        }
        for (String line : help) {
            if (line != null)
                System.out.println(line);
        }
    }else if(commands.toLowerCase().equals("look"))
    {
        System.out.println();
        World.printRoom(player.location);

    }
    
    
    }
}
                                       
                        

                  

           
                    
            
            
                
                 
                
            
                
                 
                
            
                
                 
                
            
                
                 
                
            

        

         

              
                           

            
                
                          
                 
                                                
                
                
                                                
                          
                                                  
                             

             
                                                  
          
         
        
        
 
             
             
             
             
            
         

             
             
             
             
             
        

              
             
              
             
               
        

             
                 
               
             
            
          
             
             
                 
                
            
        

               
             
             
             
                        
            
        

              
               
            
        
    
 
  
 
                                         
            

           
                    
            
            
                
                 
                
            
                
                 
                
            
                
                 
                
            
                
                 
                
            

        

         

              
                           

            
                
                          
                 
                                                
                
                
                                                
                          
                                                  
                             

          
                                                  
          
         
        
        

             
             
             
             
            
        

             
             
             
             
            
        

             
             
             
             
            
        

             
             
             
             
            
        

             
             
             
             
            
        

             
             
             
             
            
        

             
             
             
             
            
        
    



                                         
                                