import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Satellite {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public int xpos;				//the x position
    public int ypos;				//the y position
    public boolean isAlive;			//a boolean to denote if the hero is alive or dead.
    public int dx;					//the speed of the hero in the x direction
    public int dy;					//the speed of the hero in the y direction
    public int width;
    public int height;
    public Image pic;
    public Rectangle rec;			//declare a rectangle variable

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.
    public Satellite(int xParameter, int yParameter)
    {
        xpos = xParameter;
        ypos = yParameter;
        dx=-1;
        dy=2;
        isAlive=true;
        width = 40;
        height=40;
        
        rec= new Rectangle (xpos,ypos,width,height);	//construct a rectangle


    } // constructor

    public Satellite(int xParameter, int yParameter, Image picParameter)
    {
        xpos = xParameter;
        ypos = yParameter;
        dx=-1;
        dy=2;
        isAlive=true;
        width = 40;
        height=40;
        pic = picParameter;
        
        rec= new Rectangle (xpos,ypos,width,height);	//construct a rectangle


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move()
    {
        xpos = xpos + dx;
        ypos = ypos + dy;
    
      if(xpos>1000)
      {
         dx= -dx;
      }
   
      if(xpos<0)
      {
         dx= -dx;
      }
   
      if(ypos>700)
      {
         dy=-dy;
      }
   
      if(ypos<0)
      {
         dy=-dy;
      }
             
        //always rebuild or update the rectangle after you've changed an object's position, height or width
        rec= new Rectangle (xpos,ypos,width,height);	//construct a rectangle


    }








}
