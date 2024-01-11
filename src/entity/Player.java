package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2 -(gp.tileSize/2);


        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(0, 0,gp.tileSize/2, gp.tileSize);
    }
    public void setDefaultValues(){
        speed = 4;
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/Myself.png")));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void update(){



            if (keyH.upPressed) {
                worldY -= speed;
                direction = "up";
            }
            if (keyH.downPressed) {
                worldY += speed;
                direction = "down";
            }
            if (keyH.rightPressed) {
                worldX += speed;
                direction = "right";
            }
            if (keyH.leftPressed) {
                worldX -= speed;
                direction = "left";

            }
            if (keyH.mainMenu) {
                // main menu

            }


    }

    public void draw(Graphics2D g2){

        BufferedImage image;
        // add animation to character
        image = up1;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);




    }
}
