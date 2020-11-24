import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
double x,y;
Image bullet;
	Bullet(double x,double y){
		bullet=new ImageIcon("bullet.png").getImage();
		this.x=x;
		this.y=y;
	}

void moveB(){
	y-=5;	
}

void paintB(Graphics g) {
	Graphics2D g2D= (Graphics2D)g;
	g2D.drawImage(bullet,(int)x ,(int)y ,null);	
}

}
