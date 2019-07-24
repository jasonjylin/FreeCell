import java.awt.*;
//class for free cells in the top left
public class FreeCellsClass extends DeckClass
{

    public FreeCellsClass ()
    {
	super ();
    }


    public boolean okToAdd (CardClass c)
    {
	if (getDeckSize () == 0)
	{
	    return true;
	}
	else
	{
	    return false;
	}
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


    public void add (CardClass c)
    {
	super.add (c);
    }
}


