import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Throw{
	private String throwName;
	private String htmlFontName;
	private int htmlFontSize;
	private int x;
	private int y;
	private Font throwFont;
	private Rectangle2D throwRectangle;
	private Graphics getGraphics;
	public Throw(String throwName, String htmlFontName, int htmlFontSize, int startX, int startY, Graphics getGraphics)
	{
		this.throwName = throwName;
		this.htmlFontName = htmlFontName;
		this.htmlFontSize = htmlFontSize;
		this.x = startX;
		this.y = startY;
		this.getGraphics = getGraphics;
		this.throwFont = new Font(htmlFontName, Font.BOLD, htmlFontSize);
		Graphics2D g2D = (Graphics2D) getGraphics;
		FontRenderContext throwContext = g2D.getFontRenderContext();
		throwRectangle = throwFont.getStringBounds(throwName, throwContext);
	}
	public void translate(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	public void changeCoordinates(int velocityX, int velocityY)
	{
		x += velocityX;
		y += velocityY;
	}
	public String getFontName()
	{
		return htmlFontName;
	}
	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	public int getFontSize()
	{
		return htmlFontSize;
	}
	public Rectangle2D getRectangle()
	{
		return throwRectangle;
	}
	public double getRectangleWidth()
	{
		return throwRectangle.getWidth();
	}
	public double getRectangleY()
	{
		return throwRectangle.getHeight();
	}
	public Font getThrowFont()
	{
		return throwFont;
	}
	public String getThrowName()
	{
		return throwName;
	}
}