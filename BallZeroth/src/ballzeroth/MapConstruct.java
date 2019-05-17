package ballzeroth;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapConstruct {
    public void loadMap (File loadPath) {
        try {
            Scanner scan = new Scanner(loadPath);
            
            while(scan.hasNext()){
                for (int y = 0; y < Screen.map.world.length; y++) {
                    for (int x = 0; x < Screen.map.world[0].length; x++) {
                        Screen.map.world[y][x].terrainID = scan.nextInt();
                        System.out.println("Number: " + Screen.map.world[y][x].terrainID);
                    }
                }
            }
            
            scan.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapConstruct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
