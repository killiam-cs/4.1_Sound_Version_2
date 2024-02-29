//Basic Game Application
//Version 2
//SOUNDS


//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Image astroPic;
    public Image saturnPic;
    public Image moonPic;
    public Image rockPic;
    public Image starPic;
    public Image satellitePic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Astronaut astro;
    public Rock asteroid1;
    public Rock asteroid2;
    public Rock asteroid3;
    public Planet saturn;
    public Satellite spudnik;


    public SoundFile themeMusic;
    public SoundFile efx1;


    // Main method definition
    // In this example I've put the main( ) method in the BasicGameApp
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp myApp = new BasicGameApp();   //creates a new instance of the game
        new Thread(myApp).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
        saturnPic = Toolkit.getDefaultToolkit().getImage("planet.jpg"); //load the picture
        moonPic = Toolkit.getDefaultToolkit().getImage("moon.jpg"); //load the picture
        rockPic = Toolkit.getDefaultToolkit().getImage("rock.png"); //load the picture
        starPic = Toolkit.getDefaultToolkit().getImage("starbackground.png");
        satellitePic = Toolkit.getDefaultToolkit().getImage("satellite.jpg");

        astro = new Astronaut(10, 100);
        asteroid1 = new Rock(300, 500, rockPic);
        asteroid2 = new Rock(20, 800, rockPic);
        asteroid3 = new Rock(800, 90, rockPic);
        saturn = new Planet(800, 50);
        spudnik = new Satellite(300, 120, satellitePic);
        spudnik.dx = -3;
        spudnik.dy = -4;

        //construct the soundFile objects.
        //specify the filename of the sound
        efx1 = new SoundFile("bang.wav");
        themeMusic = new SoundFile("theme.wav");


        //plays the soundFile themeMusic
        themeMusic.play();


    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            checkIntersections();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //calls the move( ) code in the objects
        astro.move();
        asteroid1.move();
        asteroid2.move();
        asteroid3.move();
        spudnik.move();

    }

    public void checkIntersections() {
        if (astro.rec.intersects(asteroid1.rec) && asteroid1.isAlive == true) {
            asteroid1.isAlive = false;
            efx1.play();
            themeMusic.pause();
            System.out.println("asteroid1 hit");
        }

        if (astro.rec.intersects(asteroid2.rec) && asteroid2.isAlive == true) {
            asteroid2.isAlive = false;
            efx1.play();
            themeMusic.resume();
            System.out.println("asteroid2 hit");
            //themeMusic.stop();
        }

        if (astro.rec.intersects(asteroid3.rec) && asteroid3.isAlive == true) {
            asteroid3.isAlive = false;
            efx1.play();
            System.out.println("asteroid3 hit");

        }

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw background
        g.drawImage(starPic, 0, 0, WIDTH, HEIGHT, null);

        //draw the image of the astronaut
        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);

        //draw the asteroids
        if (asteroid1.isAlive == true)
            g.drawImage(asteroid1.pic, asteroid1.xpos, asteroid1.ypos, asteroid1.width, asteroid1.height, null);
        if (asteroid2.isAlive == true)
            g.drawImage(asteroid2.pic, asteroid2.xpos, asteroid2.ypos, asteroid2.width, asteroid2.height, null);
        if (asteroid3.isAlive == true)
            g.drawImage(asteroid3.pic, asteroid3.xpos, asteroid3.ypos, asteroid3.width, asteroid3.height, null);
        g.drawImage(saturnPic, saturn.xpos, saturn.ypos, saturn.width, saturn.height, null);
        g.drawImage(spudnik.pic, spudnik.xpos, spudnik.ypos, spudnik.width, spudnik.height, null);


        g.dispose();
        bufferStrategy.show();
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


}