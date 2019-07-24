// The "SuitClass" class.
import java.awt.*;
import hsa.Console;

public abstract class SuitClass extends ShapeClass
{

    public SuitClass ()
    {
	super();
    }


    public void setSuitSize (int s)
    {
	super.setHeight (s);
	super.setWidth (s * 4 / 5);

    }


    public void setSuitSize1 (int s)
    {
	super.setWidth (s);
	super.setHeight (s * 5 / 4);

    }


    public void setWidth (int w)
    {
	setSuitSize1 (w);

    }


    public void setHeight (int h)
    {
	setSuitSize (h);
    }
} // SuitClass class


