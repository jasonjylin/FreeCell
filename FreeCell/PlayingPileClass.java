import java.awt.*;
import java.awt.image.*;
//class for piles being played
public class PlayingPileClass extends DeckClass
{

    private int pileHeight;
    public PlayingPileClass ()
    {
	super ();
    }


    public void startAdd (CardClass c)
    {
	super.add (c);
    }


    public int isInside (int x, int y)
    {
	int v;
	CardClass c = new CardClass ();
	c = getCard (getDeckSize () - 1);
	if ((x >= c.getCentreX () - c.getWidth () / 2)
		&& (x <= c.getCentreX () + c.getWidth () / 2)
		&& (y >= c.getCentreY () - c.getHeight () / 2) && (y <= c.getCentreY () + c.getHeight () / 2))

	    {
		return getDeckSize () - 1;
	    }
	else
	{
	    for (int i = 0 ; i < getDeckSize () ; i++)
	    {
		if ((x >= getCentreX () - getWidth () / 2)
			&& (x <= getCentreX () + getWidth () / 2)
			&& (y >= getCentreY () - getHeight () / 2 + 30 * i)
			&& (y <= getCentreY () - getHeight () / 2 + 30 * (i + 1)))
		{
		    return i;
		}
	    }
	}
	return -1;
    }


    public boolean okToAdd (CardClass c)
    {
	int deckSize = getDeckSize () - 1;
	if ((c.getSuit () == 1)
		|| (c.getSuit () == 3))
	{
	    if ((getCard (deckSize).getSuit () == 1)
		    || (getCard (deckSize).getSuit () == 3))
	    {
		return false;
	    }
	    else
	    {
		if (c.getValue () == getCard (deckSize).getValue () - 1)
		{
		    return true;
		}
		else
		{
		    return false;
		}
	    }
	}
	else if ((c.getSuit () == 2)
		|| (c.getSuit () == 4))
	{

	    if ((getCard (deckSize).getSuit () == 2)
		    || (getCard (deckSize).getSuit () == 4))
	    {

		return false;
	    }
	    else
	    {
		if (c.getValue () == getCard (deckSize).getValue () - 1)
		{
		    return true;
		}
		else
		{
		    return false;
		}
	    }
	}
	else
	{
	    return false;
	}
    }


    public int getPileHeight ()
    {
	return pileHeight;
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	if (getDeckSize () == 0)
	{
	}
	else
	{
	    int newY = getCentreY ();
	    CardClass c1 = new CardClass ();
	    for (int i = 0 ; i < getDeckSize () ; i++)
	    {

		c1 = super.getCard (i);
		c1.setCentreX (this.getCentreX ());
		c1.setCentreY (getCentreY () + 30 * i);
		//  g.fillBox(getCentreX,getCentreY
		c1.draw (g, images, imgObs);

	    }
	    pileHeight = (getDeckSize () - 1) * 30 + c1.getHeight () / 2;
	    setWidth (c1.getWidth ());
	    setHeight (c1.getHeight ());
	    //setCentreX (c1.getCentreX ());
	    //setCentreY (c1.getCentreY ());
	}
    }
}


