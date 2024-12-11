package de.hsaalen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int width_in_pixels = 300;
    private final int height_in_pixels = 300;
    private final int title_size_in_pixels = 10;
    private final int maximum_snake_length = 900;
    private final int initial_snake_size = 3;
    private final int RAND_POS = 29;
    private final int game_loop_duration_in_ms = 140;
    private final int MAX_OBSTACLES = 5; // Maximalzahl der Hindernisse


    private final int x[] = new int[maximum_snake_length];
    private final int y[] = new int[maximum_snake_length];

    private int current_snake_Size;
    private int apple_x;
    private int apple_y;
    private int superFruit_x;
    private int superFruit_y;
    private boolean superFruitExist = false;

    private int[] obstacle_x = new int[MAX_OBSTACLES];
    private int[] obstacle_y = new int[MAX_OBSTACLES];

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(width_in_pixels, height_in_pixels));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();
        if (ball == null) {
            System.out.println("Fehler: Bild konnte nicht geladen werden.");
        }

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();
        if (apple == null) {
            System.out.println("Fehler: Bild konnte nicht geladen werden.");
        }

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
        if (head == null) {
            System.out.println("Fehler: Bild konnte nicht geladen werden.");
        }
    }

    private void initGame() {

        current_snake_Size = 3;

        for (int z = 0; z < current_snake_Size; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();
        locateSuperFruit(); // Superfrucht initialisieren
        locateObstacles(); // Hindernisse initialisieren


        //timer = new Timer(game_loop_duration_in_ms, this);
        //timer.start();

        start_game_loop_timer();
    }

    public void start_game_loop_timer()
    {
        timer = new Timer(game_loop_duration_in_ms, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            // Zeichne die Hindernisse
            g.setColor(Color.red); // Farbe für Hindernisse
            for (int i = 0; i < MAX_OBSTACLES; i++) {
                g.fillOval(obstacle_x[i], obstacle_y[i], title_size_in_pixels, title_size_in_pixels);
            }

            if (superFruitExist) {
                g.setColor(Color.cyan); // Farbe f체r Superfrucht
                g.fillOval(superFruit_x, superFruit_y, title_size_in_pixels, title_size_in_pixels);
            }

            for (int z = 0; z < current_snake_Size; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (width_in_pixels - metr.stringWidth(msg)) / 2, height_in_pixels / 2);
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

         current_snake_Size++;
            locateApple();
        }
    }

    private void checkSuperFruit() {
        if (x[0] == superFruit_x && y[0] == superFruit_y) {
            current_snake_Size += 3; // Die Schlange w채chst um 3
            superFruitExist = false; // Superfrucht ist gegessen
            locateSuperFruit(); // Eine neue Superfrucht wird platziert
        }
    }

    private void checkObstacleCollision() {
        for (int i = 0; i < MAX_OBSTACLES; i++) {
            if (x[0] == obstacle_x[i] && y[0] == obstacle_y[i]) {
                inGame = false; // Wenn ein Hindernis getroffen wird, endet das Spiel
            }
        }
    }

    private void move() {

        System.arraycopy(x, 0, x, 1, current_snake_Size - 1);
        System.arraycopy(y, 0, y, 1, current_snake_Size - 1);
   

        if (leftDirection) {
            x[0] -= title_size_in_pixels;
        }

        if (rightDirection) {
            x[0] += title_size_in_pixels;
        }

        if (upDirection) {
            y[0] -= title_size_in_pixels;
        }

        if (downDirection) {
            y[0] += title_size_in_pixels;
        }
    }

    private void checkCollision() {

        for (int z = 4; z < current_snake_Size; z++) {
            if (x[0] == x[z] && y[0] == y[z]) {
                inGame = false;
                break; 
            }
        }

        if (x[0] < 0  x[0] >= width_in_pixels  y[0] < 0 || y[0] >= height_in_pixels) 
        {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * title_size_in_pixels));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * title_size_in_pixels));
    }

    private void locateSuperFruit() {
        int r = (int) (Math.random() * RAND_POS);
        superFruit_x = r * title_size_in_pixels;

        r = (int) (Math.random() * RAND_POS);
        superFruit_y = r * title_size_in_pixels;

        superFruitExist = true; // Superfrucht erscheint
    }

    private void locateObstacles() {
        for (int i = 0; i < MAX_OBSTACLES; i++) {
            int r = (int) (Math.random() * RAND_POS);
            obstacle_x[i] = r * title_size_in_pixels;

            r = (int) (Math.random() * RAND_POS);
            obstacle_y[i] = r * title_size_in_pixels;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkSuperFruit(); // Superfrucht überprüfen
            checkCollision();
            checkObstacleCollision(); 
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (!rightDirection) {
                        leftDirection = true;
                        upDirection = false;
                        downDirection = false;
                    }
                    break;
        
                case KeyEvent.VK_RIGHT:
                    if (!leftDirection) {
                        rightDirection = true;
                        upDirection = false;
                        downDirection = false;
                    }
                    break;
        
                case KeyEvent.VK_UP:
                    if (!downDirection) {
                        upDirection = true;
                        rightDirection = false;
                        leftDirection = false;
                    }
                    break;
        
                case KeyEvent.VK_DOWN:
                    if (!upDirection) {
                        downDirection = true;
                        rightDirection = false;
                        leftDirection = false;
                    }
                    break;
        
                default:
                    break;
            }
        }
    }
}