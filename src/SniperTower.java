import java.awt.*;
import java.util.List;

public class SniperTower extends Tower {
    private int x, y;
    private static final int RANGE = 200;
    private static final int DAMAGE = 50;
    private static final int FIRE_RATE = 100; // Lower means slower fire rate

    private int cooldown;

    public SniperTower(int x, int y) {
        super(x, y);
        this.cooldown = FIRE_RATE;
    }

    @Override
    public void update(List<Enemy> enemies) {
        if (cooldown > 0) {
            cooldown--;
        } else {
            Enemy target = findTarget(enemies);
            if (target != null) {
                // Shoot a bigger projectile
                projectiles.add(new Projectile(x, y, target, DAMAGE, 10));
                cooldown = FIRE_RATE;
            }
        }
        projectiles.forEach(p -> p.update(enemies));
        projectiles.removeIf(p -> !p.isAlive());
    }

    private Enemy findTarget(List<Enemy> enemies) {
        return null;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x - 10, y - 10, 20, 20); // Draw the tower
        projectiles.forEach(p -> p.draw(g));
    }

    @Override
    public int getRange() {
        return RANGE;
    }
}
