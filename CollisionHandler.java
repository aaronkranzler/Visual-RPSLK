import java.util.ArrayList;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;
/**
*	@author Aaron Kranzler - amk2308
*	Class to handle the various specifications when two Throw objects collide
*	Is isntantiated with the current ArrayList, and updates this ArrayList 
*	Also instantiated with the current CollisionTester object in Playground
*/
public class CollisionHandler{
	private CollisionTester collisionTester;
	private ArrayList<Throw> currentThrowList;
	private ArrayList<Throw> losers;
	private ArrayList<Throw> winners;
	/**
	*	Constructor takes current CollisionTester and ArrayList from Playground
	*	Instantiates new arrays for losers and winners of the specific time and action event, based on updated data in collision tester
	*/
	public CollisionHandler(CollisionTester collisionTester, ArrayList<Throw> throwList)
	{
		this.collisionTester = collisionTester;
		this.currentThrowList = throwList;
		losers = collisionTester.getLosers();
		winners = collisionTester.getWinners();
	}
	/*
	*	Removes the losing Throws from currentThrowList
	*
	*/
	public void removeLosers()
	{
		for(int j = 0; j < losers.size(); j++)
		{
			if((currentThrowList.size() >= losers.size()==true))
			{
				currentThrowList.remove(losers.get(j));
			}
		}
	}
	/**
	*	NOTE: PART 2.5 OPTION 2 IS CODED HERE
	*	Replaces winning Throws with a new Throw object that has all of the same data, except the fontSize is increased
	*	so that it gets bigger and appears to be "stronger"
	*/
	public void handleWinners(Graphics graphics)
	{
		for(int k = 0; k < winners.size(); k++)
		{
			if(currentThrowList.contains(winners.get(k)) && (currentThrowList.size() >= winners.size()==true))
			{
				String newName = winners.get(k).getThrowName();
				String newFont = winners.get(k).getFontName();
				int newFontSize = winners.get(k).getFontSize() + 10;
				int newX = winners.get(k).getX();
				int newY = winners.get(k).getY();
				int newIndex = currentThrowList.indexOf(winners.get(k));
				currentThrowList.set(newIndex, new Throw(newName, newFont, newFontSize, newX, newY, graphics));
			}
		}
	}
	/**
	*	NOTE: PART 2.5 OPTION 4 IS CODED HERE
	*	Called in special case where two black holes collide 
	*	Removes one of the black holes, and replaces the other black hole with another instantiation that 
	*	has the same data, just double the fontSize
	*/
	public void handleCollidingBlackHoles(int firstIndex, int secondIndex, Graphics graphics)
	{
		String newName = currentThrowList.get(firstIndex).getThrowName();
		String newFont = currentThrowList.get(firstIndex).getFontName();
		int newFontSize = currentThrowList.get(firstIndex).getFontSize() * 2;
		int newX = currentThrowList.get(firstIndex).getX();
		int newY = currentThrowList.get(firstIndex).getY();
		currentThrowList.set(firstIndex, new Throw(newName, newFont, newFontSize, newX, newY, graphics));
		currentThrowList.remove(secondIndex);
	}
	/**
	*	Getter for currentThrowList, called after all other methods have been called
	*/
	public ArrayList<Throw> getCurrentThrowList()
	{
		return currentThrowList;
	}
}