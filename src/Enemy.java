import java.awt.*;

public class Enemy {
    private int x, y;
    private int health;
    private int maxHealth;
    private int speed;

    public Enemy(int x, int y, int health, int speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
    }

    public void update() {
        x += speed; // Move right for simplicity
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x - 15, y - 25, 30, 5); // Health bar background
        g.setColor(Color.RED);
        int healthBarWidth = (int) (((double) health / maxHealth) * 30);
        g.fillRect(x - 15, y - 25, healthBarWidth, 5); // Health bar
        g.setColor(Color.GREEN);
        g.fillRect(x - 10, y - 10, 20, 20); // Enemy body
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Ensure health doesn't go below zero
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void move() {
    }
}
