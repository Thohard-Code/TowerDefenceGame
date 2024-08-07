import java.awt.*;
import java.util.List;

public class Projectile {
    private int x, y;
    private int speed;
    private int damage;
    private int targetX, targetY;
    private boolean active;

    public Projectile(int x, int y, int targetX, int targetY, int damage, int speed) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.damage = damage;
        this.speed = speed;
        this.active = true;
    }

    public Projectile(int x, int y, Enemy target, int damage, int damage1) {
        damage = 50;
        damage1 = 50;
    }

    public void update() {
        double angle = Math.atan2(targetY - y, targetX - x);
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        // Check if projectile reached target
        if (Math.abs(x - targetX) < speed && Math.abs(y - targetY) < speed) {
            active = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return false;
    }

    public void update(List<Enemy> enemies) {
    }
}
