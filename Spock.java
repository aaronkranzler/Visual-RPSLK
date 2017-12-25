import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Spock extends Throw{
	private String throwName;
	private String htmlFontName;
	private int htmlFontSize;
	private int x;
	private int y;
	private Font throwFont;
	private Rectangle2D throwRectangle;
	private Graphics getGraphics;
	public Spock(int startX, int startY, Graphics getGraphics)
	{
		super("spock", "Comic Sans MS", 25, startX, startY, getGraphics);
	}
}