
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class AirPlaneGame extends JFrame   {
	
	MyPanel panel;  
	AirPlaneGame(){
		
		panel=new MyPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(panel.PANEL_WIDTH,panel.PANEL_HIGHT);
		
	}
	
	public static void main(String[] args) {	
		new AirPlaneGame();
	
	}
			
	public class MyPanel extends JPanel implements  ActionListener, MouseMotionListener,MouseInputListener {

		
	//	Random r=new Random();
		final int PANEL_WIDTH=1000;
		final int PANEL_HIGHT=1000;
		Image player;	
		Image back;
		Image Pshadow;
		
		Timer timer;
		double xPlayerVel;   //player speed 
		double yPlayerVel;
		double yPlayer=450;	 //player position on the screen
		double xPlayer=450;
		int mx, my;          //mouse coordinate
		
		int yBackground=0;   
		int xBackground=0;
		
		Bullet bull;
		LinkedList<Bullet> bulLs=new LinkedList<Bullet>();
			
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);  //make the cursor invisible.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		cursorImg, new Point(0, 0), "blank cursor");
		 boolean shooting;

		MyPanel(){
			addMouseListener(this);
			addMouseMotionListener(this);	
			this.setCursor(blankCursor);		
			this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HIGHT));		
			bull= new Bullet(mx,my);
			player=new ImageIcon("PLAYER.png").getImage();
			Pshadow=new ImageIcon("Pshadow.png").getImage();
			switch (3) {                                                     // usually the background are piked up randomly by r.nextInt
			 case 0: back=new ImageIcon("backgrounddetailed1.png").getImage();
			 break;
			 case 1: back=new ImageIcon("backgrounddetailed2.png").getImage();
			 break;
			 case 2: back=new ImageIcon("backgrounddetailed3.png").getImage();
			 break;
			 case 3: back=new ImageIcon("backgrounddetailed4.png").getImage();
			 break;
			
			}
			timer=new Timer(13,this);
			timer.start();
			
		}
		public void paint(Graphics g) {
			Graphics2D g2D= (Graphics2D)g;
				
			g2D.drawImage(back,xBackground,yBackground-1000 ,null);			//crate the background above window 
			g2D.drawImage(back,xBackground+500,yBackground-1000 ,null);
			g2D.drawImage(back,xBackground,yBackground-500 ,null);
			g2D.drawImage(back,xBackground+500,yBackground-500 ,null);
			
			g2D.drawImage(back,xBackground,yBackground ,null);				//crate the background inside the window 
			g2D.drawImage(back,xBackground+500,yBackground ,null);
			g2D.drawImage(back,xBackground,yBackground+500 ,null);
			g2D.drawImage(back,xBackground+500,yBackground+500 ,null);
			
			
			g2D.drawImage(Pshadow,(int) xPlayer+50,(int)yPlayer+100 ,null);	 //draw shadow
			g2D.drawImage(player,(int)xPlayer-25 ,(int)yPlayer-22 ,null);	 //draw player    
			bull.paintB(g);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {    //this method is the "game loop" 
			
			
			xPlayerVel=(0.04*(mx-xPlayer));			//compute the movement speed of the player
			yPlayerVel=(0.04*(my-yPlayer));
				
			xPlayer+=xPlayerVel;				//update the player position on the screen
			yPlayer+=yPlayerVel;					
			
			
			
			yBackground=yBackground+10;      //scroll background
			if(yBackground>=1000) {			//reset background 
			yBackground=0;
			}
			
			if( shooting) {
				
				bulLs.add(bull);
				
			}
			bull.moveB();	
			repaint();
		}
		@Override
		public void mouseDragged(MouseEvent m) {
			mx=m.getX();
			my=m.getY();
			
		}
		@Override
		public void mouseMoved(MouseEvent m) {
			mx=m.getX();
			my=m.getY();
			
		}
		@Override
		public void mouseClicked(MouseEvent m) {
				
		}
		@Override
		public void mouseEntered(MouseEvent m) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent m) {
			
			
		}
		@Override
		public void mousePressed(MouseEvent m) {
			shooting=true;
				

		}
		@Override
		public void mouseReleased(MouseEvent m) {
		
			shooting=false;
		
	}

}}
