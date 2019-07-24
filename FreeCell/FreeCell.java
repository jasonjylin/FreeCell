// The "FinalGame" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class FreeCell extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    private boolean OKtoMove = false;
    private boolean drawFlag = false;
    private Graphics g;
    private Image offscreen;
    private Button buttonNew = new Button ("New Game");
    private Button buttonRestart = new Button ("Restart");
    private TextField textFieldLegal = new TextField (7);
    private FoundationPileClass[] foundationArray = new FoundationPileClass [4];
    private FreeCellsClass[] freeArray = new FreeCellsClass [4];
    private PlayingPileClass[] playingArray = new PlayingPileClass [8];
    private Image[] [] ImageArray = new Image [13] [4];
    private Image King;
    private PlayingPileClass transferDeck;
    private int playingPileHeight;
    private int insideNum;
    private int pileNum;
    private int freePileNum;
    private int ogX;
    private int ogY;
    private boolean deckMoving = false;
    private boolean legalMoveBool = false;
    private int appletWidth;
    private int appletHeight;
    private int pileType = 0;
    BorderLayout lm = new BorderLayout ();

    public void init ()
    {
	setSize (1028, 1000);

	Panel buttonPanel = new Panel ();
	setLayout (lm);
	buttonPanel.add (buttonNew);
	buttonPanel.add (buttonRestart);
	add ("North", buttonPanel);

	g = getGraphics ();

	setBackground (Color.green);

	buttonNew.addActionListener (this);  // this is the button
	buttonRestart.addActionListener (this);

	addMouseListener (this);
	addMouseMotionListener (this);

	offscreen = createImage (1500, 1500);

	g = offscreen.getGraphics ();

	transferDeck = new PlayingPileClass ();

	for (int i = 0 ; i < 4 ; i++)
	{
	    foundationArray [i] = new FoundationPileClass ();
	    foundationArray [i].setFlipped (true);
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
	for (int i = 0 ; i < 52 ; i++)
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


    public void restartProc ()
    {
	transferDeck = new PlayingPileClass ();

	for (int i = 0 ; i < 4 ; i++)
	{
	    foundationArray [i] = new FoundationPileClass ();
	    foundationArray [i].setFlipped (true);
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
	for (int i = 0 ; i < 52 ; i++)
	{
	    playingArray [ctr].startAdd (d1.dealCard (0));
	    ctr += 1;
	    if (ctr > 7)
	    {
		ctr = 0;
	    }
	}
    }


    public void paint (Graphics a)
    {

	boolean wonGame = true;
	for (int i = 0 ; i < 4 ; i++)
	{
	    if (foundationArray [i].getDeckSize () != 13)
	    {
		wonGame = false;
	    }
	}

	if (wonGame)
	{
	    System.out.println ("You win!!!");
	}

	g.clearRect (0, 0, 1500, 1500);
	g.setColor (Color.green);

	if (drawFlag)
	{
	    Dimension appletSize = this.getSize ();
	    appletHeight = appletSize.height;
	    appletWidth = appletSize.width;

	    playingPileHeight = appletHeight / 2;


	    //g.drawImage (King, 50, 150, 100, 100, this);

	    for (int i = 0 ; i < 8 ; i++)
	    {
		playingArray [i].setCentreX (appletWidth / 9 * (i + 1));
		playingArray [i].setCentreY (playingPileHeight);
		if (playingArray [i].getDeckSize () == 0)
		{
		    g.setColor (Color.black);
		    g.drawRect (playingArray [i].getCentreX () - (playingArray [i].getWidth () / 2), playingArray [i].getCentreY () - (playingArray [i].getWidth () / 2) - 18, playingArray [i].getWidth (), playingArray [i].getHeight ());
		}
		else
		{
		    playingArray [i].draw (g, ImageArray, this);
		}
	    }

	    for (int i = 0 ; i < 4 ; i++)
	    {
		foundationArray [i].setCentreX (appletWidth / 9 * (i + 5) + 50);
		foundationArray [i].setCentreY (appletHeight / 5);
		foundationArray [i].setHeight (playingArray [0].getCard (0).getHeight ());
		foundationArray [i].setWidth (playingArray [0].getCard (0).getWidth ());
		foundationArray [i].setColor (Color.black);
		foundationArray [i].draw (g, ImageArray, this);
	    }
	    for (int i = 0 ; i < 4 ; i++)
	    {
		freeArray [i].setCentreX (appletWidth / 9 * (i + 1) - 50);
		freeArray [i].setCentreY (appletHeight / 5);
		freeArray [i].setHeight (playingArray [0].getCard (0).getHeight ());
		freeArray [i].setWidth (playingArray [0].getCard (0).getWidth ());
		freeArray [i].setColor (Color.black);
		if (freeArray [i].getDeckSize () == 0)
		{
		    freeArray [i].setFlipped (true);
		}
		freeArray [i].draw (g, ImageArray, this);
	    }


	}
	if (deckMoving)
	{
	    transferDeck.draw (g, ImageArray, this);
	}

	a.drawImage (offscreen, 0, 0, this);
	// Place the body of the drawing method here
    } // paint method


    public void update (Graphics a)
    {
	paint (a);
    }


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
	    if (objSource == buttonRestart)
	    {
		restartProc ();
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
	boolean m = false;
	pileNum = -1;
	freePileNum = -1;
	pileType = -1;

	for (int i = 0 ; i < 4 ; i++)
	{
	    if (freeArray [i].isInside (e.getX (), e.getY ()))
	    {
		m = true;
		freePileNum = i;
		break;
	    }
	}

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
		pileType = 1;
	    }
	}
	else if (m)
	{
	    if (freeArray [freePileNum].getDeckSize () == 1)
	    {
		pileType = 2;
	    }
	}

    }


    public void mouseReleased (MouseEvent e)
    {
	legalMoveBool = false;
	int ogPileNum = pileNum;
	int ogInsideNum = insideNum;
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
	    if ((pileNum != -1) && (playingArray [pileNum].getDeckSize () == 0) && (pileNum != 0))
	    {
		int ogDeckSize = transferDeck.getDeckSize ();
		for (int i = 0 ; i < ogDeckSize ; i++)
		{
		    playingArray [pileNum].startAdd (transferDeck.dealCard (0));
		    deckMoving = false;
		}
	    }
	    else
	    {
		if ((ogPileNum != -1) && (ogInsideNum == playingArray [ogPileNum].getDeckSize ()))
		{
		    if (pileNum != -1)
		    {
			legalMoveBool = playingArray [pileNum].okToAdd (transferDeck.getCard (0));
		    }

		    if ((legalMoveBool) && (pileNum != -1))
		    {
			if (transferDeck.getDeckSize () == 1)
			{
			    playingArray [pileNum].startAdd (transferDeck.dealCard (0));
			    deckMoving = false;
			}
			else
			{
			    int ogDeckSize = transferDeck.getDeckSize ();
			    for (int i = 0 ; i < ogDeckSize ; i++)
			    {
				playingArray [pileNum].startAdd (transferDeck.dealCard (0));
				deckMoving = false;
			    }
			}
		    }

		    if ((!legalMoveBool) && (transferDeck.getDeckSize () != 0)
			    && (pileNum != -1))
		    {
			if (transferDeck.getDeckSize () == 1)
			{
			    playingArray [ogPileNum].startAdd (transferDeck.dealCard (0));
			    deckMoving = false;
			}
			else
			{
			    int ogDeckSize = transferDeck.getDeckSize ();
			    for (int i = 0 ; i < ogDeckSize ; i++)
			    {
				playingArray [ogPileNum].startAdd (transferDeck.dealCard (0));
				deckMoving = false;
			    }
			}
		    }

		}
	    }
	    if ((pileNum != -1) && (playingArray [pileNum].getDeckSize () == 0))
	    {
		int ogDeckSize = transferDeck.getDeckSize ();
		for (int i = 0 ; i < ogDeckSize ; i++)
		{
		    playingArray [pileNum].startAdd (transferDeck.dealCard (0));
		    deckMoving = false;
		}
	    }
	    if ((pileNum != -1) && (pileType == 2) && (freePileNum != -1)
		    && (transferDeck.getDeckSize () > 0))

		{
		    legalMoveBool = playingArray [pileNum].okToAdd (transferDeck.getCard (0));
		    if (legalMoveBool)
		    {
			playingArray [pileNum].startAdd (transferDeck.dealCard (0));
			deckMoving = false;
		    }
		}
	}
	else
	{
	    if (transferDeck.getDeckSize () == 1)
	    {
		if (e.getX () > appletWidth / 2)
		{
		    boolean rBool = false;
		    for (int i = 0 ; i < 4 ; i++)
		    {
			if (foundationArray [i].isInside (e.getX (), e.getY ()))
			{
			    pileNum = i;
			    i = 4;
			    rBool = true;
			}
		    }

		    if ((rBool) && (transferDeck.getDeckSize () == 1))
		    {
			if (foundationArray [pileNum].okToAdd (transferDeck.getCard (0)))
			{
			    legalMoveBool = true;
			    foundationArray [pileNum].add (transferDeck.dealCard (0));
			    foundationArray [pileNum].setFlipped (false);
			    deckMoving = false;
			}
		    }
		}

		else
		{
		    boolean rBool = false;
		    for (int i = 0 ; i < 4 ; i++)
		    {
			if (freeArray [i].isInside (e.getX (), e.getY ()))
			{
			    pileNum = i;
			    i = 4;
			    rBool = true;
			}
		    }
		    if ((rBool) && (transferDeck.getDeckSize () == 1))
		    {
			if (freeArray [pileNum].okToAdd (transferDeck.getCard (0)))
			{
			    legalMoveBool = true;
			    freeArray [pileNum].add (transferDeck.dealCard (0));
			    freeArray [pileNum].setFlipped (false);
			    deckMoving = false;

			}
		    }
		}
	    }
	}


	if (pileType == 1)
	{
	    if ((!legalMoveBool) && (transferDeck.getDeckSize () != 0)
		    && (pileNum != -1))
	    {
		if (transferDeck.getDeckSize () == 1)
		{
		    playingArray [ogPileNum].startAdd (transferDeck.dealCard (0));
		    deckMoving = false;
		}
		else
		{
		    int ogDeckSize = transferDeck.getDeckSize ();
		    for (int i = 0 ; i < ogDeckSize ; i++)
		    {
			playingArray [ogPileNum].startAdd (transferDeck.dealCard (0));
			deckMoving = false;
		    }
		}
	    }
	}
	else
	{
	    if ((!legalMoveBool)
		    && (transferDeck.getDeckSize () != 0)
		    && (freePileNum != -1))
	    {
		freeArray [freePileNum].add (transferDeck.dealCard (0));
		deckMoving = false;
		freeArray [freePileNum].setFlipped (false);
	    }
	}
	repaint ();

    }


    public void mouseDragged (MouseEvent e)
    {
	if (!deckMoving)
	{
	    if (pileType == 1)
	    {
		if ((pileNum != -1) && (insideNum != -1))
		{
		    if (insideNum == playingArray [pileNum].getDeckSize () - 1)
		    {

			deckMoving = true;
			transferDeck.startAdd (playingArray [pileNum].dealCard (insideNum));
			ogX = transferDeck.getCentreX ();
			ogY = transferDeck.getCentreY ();
			transferDeck.setCentreX (playingArray [pileNum].getCentreX ());
			transferDeck.setCentreY (playingArray [pileNum].getCentreY () + 30 * insideNum);

		    }
		    else
		    {
			boolean BOOL = true;
			for (int i = insideNum ; i < playingArray [pileNum].getDeckSize () - 1 ; i++)
			{
			    int suit1;
			    int suit2;
			    int value1;
			    int value2;
			    if (i + 1 <= playingArray [pileNum].getDeckSize ())
			    {
				suit1 = playingArray [pileNum].getCard (i).getSuit ();
				suit2 = playingArray [pileNum].getCard (i + 1).getSuit ();
				value1 = playingArray [pileNum].getCard (i).getValue ();
				value2 = playingArray [pileNum].getCard (i + 1).getValue ();
				if ((suit1 == 1) || (suit1 == 3))
				{
				    if ((suit2 == 1) || (suit2 == 3))
				    {
					BOOL = false;
				    }
				}
				else if ((suit1 == 2) || (suit1 == 4))
				{
				    if ((suit2 == 2) || (suit2 == 4))
				    {
					BOOL = false;
				    }
				}
				if (value1 - value2 != 1)
				{
				    BOOL = false;
				}
			    }

			}
			if (BOOL)
			{
			    int v = insideNum;
			    int ogDeckSize = playingArray [pileNum].getDeckSize ();
			    for (int i = insideNum ; i < ogDeckSize ; i++)
			    {
				transferDeck.startAdd (playingArray [pileNum].dealCard (v));
			    }
			    deckMoving = true;
			    ogX = transferDeck.getCentreX ();
			    ogY = transferDeck.getCentreY ();
			    transferDeck.setCentreX (playingArray [pileNum].getCentreX ());
			    transferDeck.setCentreY (playingArray [pileNum].getCentreY () + 30 * insideNum);
			}
		    }
		}
	    }
	    else if (pileType == 2)
	    {
		if ((transferDeck.getDeckSize () == 0) && (freePileNum != -1))
		{
		    deckMoving = true;
		    transferDeck.startAdd (freeArray [freePileNum].dealCard (0));
		    ogX = transferDeck.getCentreX ();
		    ogY = transferDeck.getCentreY ();
		    transferDeck.setCentreX (freeArray [freePileNum].getCentreX ());
		    transferDeck.setCentreY (freeArray [freePileNum].getCentreY ());

		}
	    }
	}


	if (deckMoving)
	{
	    transferDeck.setCentreX (e.getX ());
	    transferDeck.setCentreY (e.getY ());
	}

	repaint ();


    }


    public void mouseMoved (MouseEvent e)
    {
    }
} // FinalGame class


