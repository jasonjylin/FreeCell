import java.awt.*;
//class for finishing foundation piles
public class FoundationPileClass extends DeckClass
{
    private boolean finished;

    public FoundationPileClass ()
    {
	super ();
	finished = false;
    }


    public boolean isInside (int x, int y)
    {
	if ((x >= getCentreX () - getWidth () / 2)
		&& (x <= getCentreX () + getWidth () / 2)
		&& (y >= getCentreY () - getHeight () / 2)
		&& (y <= getCentreY () + getHeight () / 2))
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean okToAdd (CardClass c)
    {
	if (getDeckSize () == 13)
	{
	    finished = true;
	}
	if (!finished)
	{
	    if (getDeckSize () == 0)
	    {
		if (c.getValue () == 1)
		{
		    return true;
		}
		else
		{
		    return false;
		}
	    }
	    else
	    {
		if ((c.getValue () == getCard (getDeckSize () - 1).getValue () + 1) && (c.getSuit () == getCard (getDeckSize () - 1).getSuit ()))
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


    public void add (CardClass c)
    {
	super.add (c);

    }



}






