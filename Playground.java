import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;


/**
 * 
 * @author Aaron Kranzler - amk2308, based on jrk Playground.java based on Cay Horstmann
 *	Applet class that contains implementations of all 5 Applet methods
 *	Has ArrayList as data structure to hold various Throw objects, has Applet Timer, and Collision Tester
 * 	During each Action Event, for loop iterates through ArrayList and checks to see if each throw is at a bound, and if so 
 *	bounds are changed to opposite border that was hit
 *  Also during each Action event helper classes are used to handle collisions of throws
 * NOTE: FOR 2.5, I DID OPTIONS 2 AND 4 
 * This is the html file with all the paramaters
 * 
 * <applet code=Playground.java width="1000" height="1000">
 *	<param name="throw" value="rock" />
 *	<param name="fontname" value="Courier" />
 *	<param name="fontsize" value="20" />
 *	<param name="delay" value="100" />
 *	<param name="whereToStartX" value="200" />
 *	<param name="whereToStartY" value="200" />
 *	<param name="velocityX" value="1" />
 *	<param name="velocityY" value="-1" />
 *	</applet>
 *
 */
public class Playground extends Applet {
	private ArrayList<Throw> throwList;
	private Timer appletTimer;
	private int htmlDelay;
	private int velocityX;
	private int velocityY;
	private int counter;
	private CollisionTester collisionTester;
	/**
	 * Idioms for applet initialization, mostly to get parameters; use
	 * convention that incoming parameters are prefixed with "html"
	 * 
	 */
	public void init() {
		throwList = new ArrayList<Throw>();
		Throw rock = new Rock(Integer.parseInt(getParameter("whereToStartX")), Integer.parseInt(getParameter("whereToStartY")), getGraphics());
		Throw scissors = new Scissors(200, 790, getGraphics());
		Throw scissors2 = new Scissors(300, 700, getGraphics());
		Throw paper = new Paper(400, 900, getGraphics());
		Throw paper2 = new Paper(700, 300, getGraphics());
		Throw lizard = new Lizard(345, 200, getGraphics());
		Throw spock = new Spock(50, 270, getGraphics());
		Throw spock1 = new Spock(79, 359, getGraphics());
		Throw blackhole = new BlackHole(100, 200, getGraphics());
		Throw blackhole2 = new BlackHole(300, 100, getGraphics());
		throwList.add(rock);
		throwList.add(scissors);
		throwList.add(scissors2);
		throwList.add(paper);
		throwList.add(paper2);
		throwList.add(lizard);
		throwList.add(spock);
		throwList.add(spock1);
		throwList.add(blackhole);
		throwList.add(blackhole2);
		htmlDelay = Integer.parseInt(getParameter("delay"));
		counter = 0;
		collisionTester = new CollisionTester(throwList);
		// usual Timer idiom
		appletTimer = new Timer(htmlDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < throwList.size(); i++)
			{
				throwList.get(i).changeCoordinates(1 - i, i + 1);
				if (throwList.get(i).getX() + throwList.get(i).getRectangleWidth() < 0)
				{
					throwList.get(i).translate((int)(getWidth()), (int)(throwList.get(i).getY()));
				}
				else if(throwList.get(i).getX() > (int)(getWidth()))
				{
					throwList.get(i).translate(0, (int)(throwList.get(i).getY()));
				}
				if(throwList.get(i).getY() + throwList.get(i).getRectangleY() < 0)
				{
					throwList.get(i).translate(throwList.get(i).getX(), (int)(getHeight()));
				}
				else if(throwList.get(i).getY() + throwList.get(i).getRectangleY() > (int)(getHeight()))
				{
					throwList.get(i).translate(throwList.get(i).getX(), 0);
				}
			}
			collisionTester.checkThrows();//updates all data in collisionTester to be used in CollisionHandler
			CollisionHandler handler = new CollisionHandler(collisionTester, throwList);
			handler.removeLosers();
			handler.handleWinners(getGraphics());
			int indicesOfBlackHoles = collisionTester.blackHoleCollision();//if black holes have collided in this frame, this should not be -1
			int firstIndex = indicesOfBlackHoles/10;
			int secondIndex = indicesOfBlackHoles%10;
			if(indicesOfBlackHoles!=-1)//special case where two black holes collide
			{
				handler.handleCollidingBlackHoles(firstIndex, secondIndex, getGraphics());
			}
			throwList = handler.getCurrentThrowList();
			collisionTester.updateList(throwList);
			collisionTester.resetLosers();
			collisionTester.resetWinners();
			repaint();
		}
		});            
	}
	/**
	*method to start timer
	*/
	public void start() {
		appletTimer.start();
	}
    /**
    * Iterates throw ArrayList and paints each separate Throw 
    */
	public void paint(Graphics g) {
		// reset, if necessary, the paintbrush
		for(int i = 0; i < throwList.size(); i++)
		{
			g.setFont(throwList.get(i).getThrowFont());
			g.drawString(throwList.get(i).getThrowName(), throwList.get(i).getX(), throwList.get(i).getY());
		}
	}

	public void stop() {
		appletTimer.stop();
	}

	public void destroy() {
	}
}
