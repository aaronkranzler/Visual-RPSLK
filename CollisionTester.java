import java.util.ArrayList;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;
/*
*	NOTE: PART 2.5 CODED HERE
*	Class that tests whether two Throws in a frame have collided, checks to see which one 
*	wins in RPSLKB warfare
*
*/
public class CollisionTester{
	private ArrayList<Throw> currentThrowList;
	private ArrayList<Throw> losers;
	private ArrayList<Throw> winners;
	public CollisionTester(ArrayList<Throw> currentThrowList)
	{
		this.currentThrowList = currentThrowList;
		losers = new ArrayList<Throw>();
		winners = new ArrayList<Throw>();
	}
	private boolean doTheyCollide(Throw throw1, Throw throw2)
	{
		boolean returnValue = false;
		Rectangle2D rect1 = new Rectangle(throw1.getX(), throw1.getY(), (int)(throw1.getRectangleWidth()), (int)(throw1.getRectangleY()));
		Rectangle2D rect2 = new Rectangle(throw2.getX(), throw2.getY(), (int)(throw2.getRectangleWidth()), (int)(throw2.getRectangleY()));
		if(rect1.intersects(rect2))
		{
			returnValue = true;
			//System.out.println(throw1.getRectangle().getWidth() + " " + throw1.getRectangle().getHeight() + " "  + throw2.getRectangle().getWidth() + " " + throw2.getRectangle().getHeight());
		}
		else
		{
			returnValue = false;
		}
		return returnValue;
	}
	private int transferStringToInt(String value)//changes move value, a String, to an int for doesThisMoveWin() 
	{
		int valueInt = 100000000;//if value is not an acceptable move value, this signals so and alters the output in the Game class 
		String[] allPossibleMoves = {"rock", "paper", "scissors", "spock", "lizard", "blackhole"};
		for(int i = 0; i < allPossibleMoves.length; i++)
		{
			if(value.equals(allPossibleMoves[i]))
				valueInt = i;
		}
		return valueInt;
	}
	private boolean doesThisMoveWin(String throw1Name, String throw2Name)
	{
		boolean winnerOrNo = false;
		int gameNumber = (5 + transferStringToInt(throw1Name) - transferStringToInt(throw2Name)) % 5;//numerical test to determine the winner, based on algorithm from Stack Overflow  
		if((gameNumber==1 || gameNumber==3))//winning case
		{
			winnerOrNo = true;
		}
		else if(gameNumber==2 || gameNumber==4)//losing case
		{
			winnerOrNo = false;
		}
		if(throw1Name.equals("blackhole") && (throw2Name.equals("blackhole")==false))
		{
			winnerOrNo = true;
		}
		else if(throw2Name.equals("blackhole") && (throw1Name.equals("blackhole")==false))
		{
			winnerOrNo = false;
		}
		return winnerOrNo;
	}
	public void updateList(ArrayList<Throw> currentList)
	{
		currentThrowList = currentList;
	}
	/**
	*Gets the winners from the last frame, if any, and sets the ArrayList back
	*to an empty one, as the same items don't win each frame
	*/
	public ArrayList<Throw> getWinners()
	{
		return winners;
	}
	public void resetWinners()
	{
		winners = new ArrayList<Throw>();
	}
	/**
	*Gets the losers from the last frame, if any, and sets the ArrayList back
	*to an empty one, as the same items don't lose each frame
	*/
	public ArrayList<Throw> getLosers()
	{
		return losers;
	}
	public void resetLosers()
	{
		losers = new ArrayList<Throw>();
	}
	public int blackHoleCollision()
	{
		int indices = -1;
		for(int m = 0; m < currentThrowList.size(); m++)
		{
			for(int n = m + 1; n < currentThrowList.size(); n++)
			{
				if((currentThrowList.get(m).getThrowName().equals(currentThrowList.get(n).getThrowName())==true) && (currentThrowList.get(m).getThrowName().equals("blackhole")==true) && (doTheyCollide(currentThrowList.get(m), currentThrowList.get(n))==true))
				{
					indices = (m*10) + n;
				}
			}
		}
		return indices;
	}
	public void checkThrows()
	{
		for(int i = 0; i < currentThrowList.size(); i++)
		{
			for(int j = i + 1; j < currentThrowList.size(); j++)
			{
				if((currentThrowList.get(i).getThrowName().equals(currentThrowList.get(j).getThrowName())==false) && (doTheyCollide(currentThrowList.get(i), currentThrowList.get(j))==true))
				{
					if(doesThisMoveWin(currentThrowList.get(i).getThrowName(), currentThrowList.get(j).getThrowName()))
					{
						losers.add(currentThrowList.get(j));
						winners.add(currentThrowList.get(i));
					}
					else
					{
						losers.add(currentThrowList.get(i));
						winners.add(currentThrowList.get(j));
					}
				}
			}
		}
	}	
}