import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private List<Enemy> enemies;
    private List<Tower> towers;
    private Timer timer;
    private boolean placingBasicTower;
    private boolean placingSniperTower;

    public GamePanel() {
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.timer = new Timer(16, this);
        this.placingBasicTower = false;
        this.placingSniperTower = false;
        this.addMouseListener(this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw selection bar
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), 50);
        g.setColor(Color.RED);
        g.fillRect(10, 10, 30, 30); // Basic Tower button
        g.setColor(Color.BLUE);
        g.fillRect(50, 10, 30, 30); // Sniper Tower button

        // Draw towers and enemies
        towers.forEach(t -> t.draw(g));
        enemies.forEach(e -> e.draw(g));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        towers.forEach(t -> t.update(enemies));
        enemies.forEach(Enemy::move);
        if (enemies.removeIf(e -> !e.isAlive()));
        else ;

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Check if clicked on selection bar
        if (y < 50) {
            if (x > 10 && x < 40) {
                placingBasicTower = true;
                placingSniperTower = false;
            } else if (x > 50 && x < 80) {
                placingBasicTower = false;
                placingSniperTower = true;
            }
        } else {
            // Place tower on the game field
            if (placingBasicTower) {
                towers.add(new Tower(x, y) {
                    @Override
                    public int getRange() {
                        return 0;
                    }
                });
                placingBasicTower = false;
            } else if (placingSniperTower) {
                towers.add(new SniperTower(x, y));
                placingSniperTower = false;
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}