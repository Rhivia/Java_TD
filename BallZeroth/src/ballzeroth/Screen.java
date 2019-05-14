package ballzeroth;

import java.awt.*; // Importa todas as classes AWT para o projeto
import java.awt.image.*; // Importa todas as classes Image para o projeto
import javax.swing.*; // Importa todas as classes SWING para o projeto
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Screen extends JPanel implements Runnable {
    public Thread gameLoop = new Thread(this);
    
    private boolean running = false;
    
    public static Image[] tileset_ground = new Image[100];
    public static Image[] tileset_air = new Image[100];
    public static Map map;
    public static MapConstruct mapConstruct;
    public static int screenWidth, screenHeight;
    public static boolean start = true;
    private ImageIcon image;
    
    public Screen() {
        setBackground(Color.BLUE);
        running = true;
        gameLoop.start();
    }
    
    // Paint and repaint the screen
    public void paintComponent (Graphics g) {
        if(start) {
            try {
                start();
                start = false;
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g.clearRect(0, 0, getWidth(), getHeight());
        map.draw(g); // Draw the map and update it
    }
    
    public void start () throws FileNotFoundException, IOException {
        screenWidth = getWidth(); // function comes from JPanel extension
        screenHeight = getHeight(); // function comes from JPanel extension
        
        map = new Map();
        
        for (int i = 0; i < tileset_ground.length; i++) {
            image = new ImageIcon(SpriteIDs.imagesDIR);
            //image = new ImageIcon(getClass().getClassLoader().getResource(SpriteIDs.imagesDIR));
            tileset_ground[i] = image.getImage();
            tileset_ground[i] = createImage( new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 64 * i, 64, 64)) );
        }
        
        try {
            String linha="";
            int counter = 1;
            
            FileReader file = new FileReader(SpriteIDs.mapsDIR);
            System.out.println("");
            BufferedReader sc = new BufferedReader(file);
            
            while((linha = sc.readLine()) != null){
                String caracteres[] = linha.split(" ");
                
                for (int y = 0; y < counter; y++) {
                    for (int x = 0; x < caracteres.length; x++) {
                        map.block[y][x].terrainID = Integer.parseInt(caracteres[x]);
                        System.out.println("terrain: " + map.block[y][x].terrainID);
                    }
                }
                
                counter++;
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
    
        while(running) {
            if(!start){
                
            }
            
            repaint();
            
            try {
                Thread.sleep(1); // Wait for a second?
            } catch (Exception e) {
                
            }
        }
    }
}
