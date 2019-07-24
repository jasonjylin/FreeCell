// The DiamondClass Class
// Second in a series of demonstration programs for introducing Java

import java.awt.*;

class DiamondClass extends SuitClass
{


    public DiamondClass ()
    {
	super ();
    }


    public DiamondClass (int iWidth, int iHeight, int iCentreX, int iCentreY, int iTailLength, Color cColor)
    {
	setWidth (iWidth);
	setHeight (iHeight);
	setCentre (iCentreX, iCentreY);
    }


    public void draw (Graphics g)
    {
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX;
	iPointsY [1] = iCentreY - iHeight / 2;
	iPointsX [2] = iCentreX + iWidth / 2;
	iPointsY [2] = iCentreY;
	iPointsX [3] = iCentreX;
	iPointsY [3] = iCentreY + iHeight / 2;

	// draw the diamond using methods available from the Console object (c)
	g.setColor (Color.red);
	g.fillPolygon (iPointsX, iPointsY, 4);

    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }



}
