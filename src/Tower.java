import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Tower {
    private int x, y;
    private int range;
    private int damage;
    private int cooldown;
    private int cooldownCounter;
    List<Projectile> projectiles;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        this.range = 100;
        this.damage = 50;
        this.cooldown = 60;
        this.cooldownCounter = 0;
        this.projectiles = new ArrayList<>();
    }

    public void update(List<Enemy> enemies) {
        if (cooldownCounter > 0) {
            cooldownCounter--;
        } else {
            for (Enemy enemy : enemies) {
                if (isInRange(enemy) && enemy.isAlive()) {
                    fireProjectile(enemy);
                    break; // Only attack one enemy per frame
                }
            }
        }

        // Update projectiles
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.update();
            if (!projectile.isActive()) {
                iterator.remove();
            } else {
                // Check collision with enemies
                for (Enemy enemy : enemies) {
                    if (isProjectileColliding(projectile, enemy)) {
                        enemy.takeDamage(projectile.getDamage());
                        iterator.remove();
                        break; // Projectile hits one enemy per frame
                    }
                }
            }
        }
    }

    private boolean isProjectileColliding(Projectile projectile, Enemy enemy) {
        // Simple collision check based on distance
        return Math.hypot(projectile.getX() - enemy.getX(), projectile.getY() - enemy.getY()) <= 5;
    }

    private void fireProjectile(Enemy enemy) {
        Projectile projectile = new Projectile(x, y, enemy.getX(), enemy.getY(), damage, 5);
        projectiles.add(projectile);
        cooldownCounter = cooldown;
    }

    public boolean isInRange(Enemy enemy) {
        return Math.hypot(enemy.getX() - x, enemy.getY() - y) <= range;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x - 10, y - 10, 20, 20);
        g.setColor(Color.RED);
        g.drawOval(x - range, y - range, range * 2, range * 2);

        // Draw projectiles
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

    public abstract int getRange();
}