// The "DeckClass" class.
import java.awt.*;
import java.util.Vector;
import java.awt.image.*;


public class DeckClass extends ShapeClass
{
    private Vector v = new Vector ();

    private boolean flipped;
    private String cardSize = "Extra Large";

    public DeckClass ()
    {
	cardSize = "Medium";
	flipped = false;
    }


    public DeckClass (int s)
    {

	for (int i = 1 ; i < 14 ; i++)
	{

	    for (int b = 1 ; b < 5 ; b++)
	    {
		CardClass c1 = new CardClass (cardSize, i, b);
		add (c1);

	    }
	}

    }


    public void setFlipped (boolean bool)
    {
	flipped = bool;
    }


    public void add (CardClass card)

    {
	v.addElement (card);
    }


    public void add (CardClass card, int Pos)
    {
	if (Pos == 0 || v.size () == 0)
	{
	    v.addElement (card);
	}
	else if (Pos > v.size ())
	{
	    v.insertElementAt (card, v.size ());
	}
	else
	{
	    v.insertElementAt (card, Pos);
	}
    }


    public void delete (int pos)
    {
	v.removeElementAt (pos);
    }


    public CardClass peekTop ()
    {
	return (CardClass) v.get (0);
    }


    public int getDeckSize ()
    {
	return v.size ();
    }


    public void setDeckSize (int s)
    {
	v.setSize (s);
    }


    public void shuffle (int num)
    {
	for (int i = 1 ; i < num ; i++)
	{
	    CardClass c = new CardClass ();
	    CardClass d = new CardClass ();
	    int randInt = (int) (Math.random () * 51);
	    int randIntt = (int) (Math.random () * 51);
	    c = (CardClass) v.get (randInt);
	    d = (CardClass) v.get (randIntt);

	    v.setElementAt (c, randIntt);
	    v.setElementAt (d, randInt);

	}
    }


    public CardClass dealCard (int Pos)
    {

	return (CardClass) v.remove (Pos);
    }


    public CardClass getCard (int Pos)
    {
	return (CardClass) v.get (Pos);
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	if (flipped)
	{
	    // draw back of a card
	    g.setColor (Color.black);
	    g.drawRect (getCentreX () - (getWidth () / 2), getCentreY () - (getWidth () / 2), getWidth (), getHeight ());
	}

	else
	{
	    CardClass c = new CardClass ();
	    c = getCard (getDeckSize () - 1);
	    c.setCentreX (getCentreX ());
	    c.setCentreY (getCentreY () + 20);
	    c.draw (g, images, imgObs);
	}
    }
}
