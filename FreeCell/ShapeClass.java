import java.awt.*;

public abstract class ShapeClass
{
    // public abstract void draw (Graphics g);

    int iHeight, iWidth, iCentreX, iCentreY;
    Color iColor;


    public ShapeClass ()
    {
	iHeight = 100;
	iWidth = 100;
	iCentreX = 100;
	iCentreY = 100;
	iColor = Color.black;
    }


    public void setColor (Color c)
    {
	iColor = c;
    }


    public void setHeight (int h)
    {
	iHeight = h;
    }


    public void setWidth (int w)
    {
	iWidth = w;
    }


    public void setCentre (int x, int y)
    {
	iCentreX = x;
	iCentreY = y;
    }


    public void setCentreY (int y)
    {
	iCentreY = y;
    }


    public void setCentreX (int x)
    {
	iCentreX = x;
    }


    public int getCentreX ()
    {
	return iCentreX;
    }


    public int getCentreY ()
    {
	return iCentreY;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public Color getColor ()
    {
	return iColor;
    }


    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }


    // public void erase (Graphics g)
    // {
    //     Color cOldColor = getColor ();
    //     setColor (Color.white);
    //     draw (g, ImageArray, this);
    //     setColor (cOldColor);
    // }
}
