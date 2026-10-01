import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    // Game window
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;

    // Snake
    final int[] x = new int[WIDTH * HEIGHT / UNIT_SIZE];
    final int[] y = new int[WIDTH * HEIGHT / UNIT_SIZE];

    int bodyParts = 6;
    int score = 0;

    // Food
    int foodX;
    int foodY;

    // Snake direction
    char direction = 'R';

    boolean running = false;

    Timer timer;
    Random random;

    public SnakeGame() {

        random = new Random();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(this);

        startGame();
    }

    public void startGame() {

        createFood();

        running = true;

        timer = new Timer(100, this);
        timer.start();
    }

    public void createFood() {

        foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            // Draw food
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // Draw snake
            for (int i = 0; i < bodyParts; i++) {

                if (i == 0) {
                    // Snake head
                    g.setColor(Color.GREEN);
                } else {
                    // Snake body
                    g.setColor(new Color(45, 180, 0));
                }

                g.fillRect(
                        x[i],
                        y[i],
                        UNIT_SIZE,
                        UNIT_SIZE
                );
            }

            // Score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 25));

            g.drawString(
                    "Score: " + score,
                    20,
                    30
            );

        } else {

            gameOver(g);
        }
    }

    public void move() {

        // Move body
        for (int i = bodyParts; i > 0; i--) {

            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Move head
        switch (direction) {

            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;

            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;

            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkFood() {

        if (x[0] == foodX && y[0] == foodY) {

            bodyParts++;

            score++;

            createFood();
        }
    }

    public void checkCollision() {

        // Snake hits itself
        for (int i = bodyParts; i > 0; i--) {

            if (x[0] == x[i] && y[0] == y[i]) {

                running = false;
            }
        }

        // Left wall
        if (x[0] < 0) {
            running = false;
        }

        // Right wall
        if (x[0] >= WIDTH) {
            running = false;
        }

        // Top wall
        if (y[0] < 0) {
            running = false;
        }

        // Bottom wall
        if (y[0] >= HEIGHT) {
            running = false;
        }

        if (!running) {

            timer.stop();
        }
    }

    public void gameOver(Graphics g) {

        // Game Over text
        g.setColor(Color.RED);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        60
                )
        );

        FontMetrics metrics = getFontMetrics(g.getFont());

        g.drawString(
                "GAME OVER",
                (WIDTH - metrics.stringWidth("GAME OVER")) / 2,
                HEIGHT / 2
        );

        // Final score
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        String scoreText = "Score: " + score;

        FontMetrics scoreMetrics =
                getFontMetrics(g.getFont());

        g.drawString(
                scoreText,
                (WIDTH - scoreMetrics.stringWidth(scoreText)) / 2,
                HEIGHT / 2 + 50
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {

            move();

            checkFood();

            checkCollision();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:

                if (direction != 'R') {
                    direction = 'L';
                }

                break;

            case KeyEvent.VK_RIGHT:

                if (direction != 'L') {
                    direction = 'R';
                }

                break;

            case KeyEvent.VK_UP:

                if (direction != 'D') {
                    direction = 'U';
                }

                break;

            case KeyEvent.VK_DOWN:

                if (direction != 'U') {
                    direction = 'D';
                }

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Main method
    public static void main(String[] args) {

        JFrame frame = new JFrame("Snake Game");

        SnakeGame game = new SnakeGame();

        frame.add(game);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setResizable(false);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
