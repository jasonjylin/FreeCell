// The "FinalGame" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class FinalGame extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    private boolean OKtoMove = false;
    private boolean drawFlag = false;
    private Graphics g;
    private Button buttonNew = new Button ("New Game");
    private Button buttonLoad = new Button ("Load Game");
    private Button buttonHelp = new Button ("Help");
    private Label legalMove = new Label ("Move");
    private TextField textFieldLegal = new TextField (7);
    private FoundationPileClass[] foundationArray = new FoundationPileClass [4];
    private FreeCellsClass[] freeArray = new FreeCellsClass [4];
    private PlayingPileClass[] playingArray = new PlayingPileClass [8];
    private Image[] [] ImageArray = new Image [13] [4];
    private Image King;
    private DeckClass transferDeck;
    private int playingPileHeight;
    private int insideNum;

    public void init ()
    {
	setSize (1028, 576);

	g = getGraphics ();

	buttonNew.addActionListener (this);  // this is the button
	buttonLoad.addActionListener (this);
	buttonHelp.addActionListener (this);

	add (buttonNew);
	add (buttonLoad);
	add (buttonHelp);
	add (legalMove);
	add (textFieldLegal);

	addMouseListener (this);
	addMouseMotionListener (this);

	repaint ();


	for (int i = 0 ; i < 4 ; i++)
	{
	    foundationArray [i] = new FoundationPileClass ();
	}

	for (int i = 0 ; i < 4 ; i++)
	{
	    freeArray [i] = new FreeCellsClass ();
	}

	for (int i = 0 ; i < 8 ; i++)
	{
	    playingArray [i] = new PlayingPileClass ();
	}

	DeckClass d1 = new DeckClass (1);
	d1.shuffle (51);

	int ctr = 0;
	for (int i = 0 ; i < 51 ; i++)
	{
	    playingArray [ctr].startAdd (d1.dealCard (0));
	    ctr += 1;
	    if (ctr > 7)
	    {
		ctr = 0;
	    }
	}

	String a = "";
	String b = "";
	for (int i = 1 ; i < 14 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		if (i == 1)
		{
		    a = "a";
		}
		if (i > 1 && i < 10)
		{
		    a = "" + i;
		}
		if (i == 10)
		{
		    a = "t";
		}
		if (i == 11)
		{
		    a = "j";
		}
		if (i == 12)
		{
		    a = "q";
		}
		if (i == 13)
		{
		    a = "k";
		}

		if (j == 0)
		{
		    b = "d";
		}
		if (j == 1)
		{
		    b = "c";
		}
		if (j == 2)
		{
		    b = "h";
		}
		if (j == 3)
		{
		    b = "s";
		}

		ImageArray [i - 1] [j] = getImage (getCodeBase (), "../cards/" + a + b + ".gif");

	    }

	}

	King = getImage (getCodeBase (), "../cards/" + "King" + ".gif");
    } // init method


    public void paint (Graphics g)
    {

	if (drawFlag)
	{
	    Dimension appletSize = this.getSize ();
	    int appletHeight = appletSize.height;
	    int appletWidth = appletSize.width;

	    playingPileHeight = appletHeight / 2;
	    setBackground (Color.green);

	    //g.drawImage (King, 50, 150, 100, 100, this);

	    for (int i = 0 ; i < 8 ; i++)
	    {
		playingArray [i].setCentreX (appletWidth / 9 * (i + 1));
		playingArray [i].setCentreY (playingPileHeight);
		playingArray [i].draw (g, ImageArray, this);
	    }
	}

	// Place the body of the drawing method here
    } // paint method


    public boolean action (Event e, Object o)
    {
	handleAction (e.target);
	return true;
    }


    public void actionPerformed (ActionEvent e)
    {
	handleAction (e.getSource ());
    }


    public void handleAction (Object objSource)
    {
	if (objSource instanceof Button)
	{
	    if (objSource == buttonNew)
	    {
		drawFlag = true;
	    }

	}

	repaint ();
    }


    /////// mouse
    public void mouseClicked (MouseEvent e)
    {


	repaint ();
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {

	int pileNum = -1;

	if ((e.getY () > playingPileHeight - (playingArray [0].getHeight () / 2)))
	{

	    for (int i = 0 ; i < 8 ; i++)
	    {
		int x = playingArray [i].getCentreX ();
		int y = playingArray [i].getCentreY ();
		int w = playingArray [i].getWidth ();
		int h = playingArray [i].getPileHeight ();
		int h1 = playingArray [i].getHeight ();

		if ((e.getX () >= x - w / 2) && (e.getX () <= x + w / 2)
			&& (e.getY () >= y - h / 2) && (e.getY () <= y + h))
		{
		    pileNum = i;
		    i = 9;

		}
	    }
	    if (pileNum != -1)
	    {
		insideNum = playingArray [pileNum].isInside (e.getX (), e.getY ());
	    }
	}

    }


    public void mouseReleased (MouseEvent e)
    {


    }


    public void mouseDragged (MouseEvent e)
    {


	repaint ();


    }


    public void mouseMoved (MouseEvent e)
    {
    }
} // FinalGame class


