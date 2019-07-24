// The "CardClass" class.
import java.awt.*;
import java.awt.image.*;


public class CardClass extends ShapeClass
{
    private int iSize = 80;
    private int iValue = 1;
    private int iSuit = 1;
    private boolean isShowing = true;
    private boolean bool = false;

    public CardClass ()
    {
	setSize ("medium");
    }


    public CardClass (String size, int value, int suit)
    {
	setSize (size);
	setValue (value);
	setSuit (suit);
    }


    public void setSize (String s)
    {
	bool = false;
	if (s == "Small" || s == "small")
	{
	    iSize = 60;
	    bool = true;
	}
	else if (s == "Medium" || s == "medium")
	{
	    iSize = 80;
	    bool = true;
	}
	else if (s == "Large" || s == "large")
	{
	    iSize = 100;
	    bool = true;
	}
	else if (s == "Extra Large" || s == "extra large" || s == "Extra large")
	{
	    iSize = 120;
	    bool = true;
	}

	if (bool == true)
	{
	    iHeight = iSize;
	    iWidth = (iSize * 7) / 10;
	}

    }


    public int getSize ()
    {
	return iSize;
    }


    public void setValue (int v)
    {
	if (v > 0 && v < 14)
	{
	    iValue = v;
	}
    }


    public int getValue ()
    {
	return iValue;
    }


    public void setSuit (int s)
    {
	if (s > 0 && s < 5)
	{
	    iSuit = s;
	}
    }


    public int getSuit ()
    {
	return iSuit;
    }


    public void setShowing (boolean boo)
    {
	isShowing = bool;
    }


    public boolean getShowing ()
    {
	return bool;
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	g.drawImage (images [iValue - 1] [iSuit - 1], getCentreX () - getWidth () / 2, getCentreY () - getHeight () / 2, getWidth (), getHeight (), imgObs);
    }


    // public void draw (Graphics g)
    // {
    //     if (isShowing = false)
    //     {
    //         g.fillRect (iCentreX - (iWidth / 2), iCentreY - (iHeight / 2), iWidth, iHeight);
    //
    //     }
    //
    //     else if (isShowing = true)
    //     {
    //
    //         g.drawRect (getCentreX () - (getWidth () / 2), getCentreY () - (getHeight () / 2), getWidth (), getHeight ());
    //
    //         if (iSuit == 1)
    //         {
    //             DiamondClass d1 = new DiamondClass ();
    //             d1.setCentre (getCentreX (), getCentreY ());
    //             d1.setHeight (getHeight () / 4);
    //             d1.draw (g);
    //         }
    //         else if (iSuit == 2)
    //         {
    //             ClubClass c1 = new ClubClass ();
    //             c1.setCentre (getCentreX (), getCentreY ());
    //             c1.setHeight (getHeight () / 4);
    //             c1.draw (g);
    //         }
    //         else if (iSuit == 3)
    //         {
    //             HeartClass h1 = new HeartClass ();
    //             h1.setCentre (getCentreX (), getCentreY ());
    //             h1.setHeight (getHeight () / 4);
    //             h1.draw (g);
    //         }
    //         else if (iSuit == 4)
    //         {
    //             SpadeClass s1 = new SpadeClass ();
    //             s1.setCentre (getCentreX (), getCentreY ());
    //             s1.setHeight (getHeight () / 4);
    //             s1.draw (g);
    //         }
    //
    //         Font f1 = new Font ("SanSerif", Font.PLAIN, 11);
    //         g.setFont (f1);
    //
    //         if (iValue > 1 && iValue < 11)
    //         {
    //             String num = "" + iValue;
    //             g.drawString (num, iCentreX - (iWidth / 3), iCentreY + (iHeight / 4));
    //         }
    //         else if (iValue == 1)
    //         {
    //             g.drawString ("A", iCentreX - (iWidth / 3), iCentreY + (iHeight / 4));
    //         }
    //         else if (iValue == 11)
    //         {
    //             g.drawString ("J", iCentreX - (iWidth / 3), iCentreY + (iHeight / 4));
    //         }
    //         else if (iValue == 12)
    //         {
    //             g.drawString ("Q", iCentreX - (iWidth / 3), iCentreY + (iHeight / 4));
    //         }
    //         else if (iValue == 13)
    //         {
    //             g.drawString ("K", iCentreX - (iWidth / 3), iCentreY + (iHeight / 4));
    //         }
    //     }
    // }
} // CardClass class
