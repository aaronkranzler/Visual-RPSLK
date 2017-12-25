import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Lizard extends Throw{
	private String throwName;
	private String htmlFontName;
	private int htmlFontSize;
	private int x;
	private int y;
	private Font throwFont;
	private Rectangle2D throwRectangle;
	private Graphics getGraphics;
	public Lizard(int startX, int startY, Graphics getGraphics)
	{
		super("lizard", "Garamond", 30, startX, startY, getGraphics);
	}
}