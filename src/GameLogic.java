import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameLogic {
    private List<Tower> towers;
    private List<Enemy> enemies;
    private int wave;
    private int spawnCounter;
    private int enemyHealth;
    private int enemySpeed;

    public GameLogic() {
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.wave = 1;
        this.spawnCounter = 0;
        this.enemyHealth = 100; // Initial health of enemies
        this.enemySpeed = 2;    // Initial speed of enemies
    }

    public void update() {
        spawnEnemies();

        for (Tower tower : towers) {
            tower.update(enemies);
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();
            if (!enemy.isAlive()) {
                iterator.remove(); // Remove dead enemies
            }
        }
    }

    public void draw(Graphics g) {
        for (Tower tower : towers) {
            tower.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Wave: " + wave, 20, 20);
    }

    public void addTower(int x, int y) {
        towers.add(new Tower(x, y) {
            @Override
            public int getRange() {
                return 1;
            }
        });
    }

    private void spawnEnemies() {
        if (spawnCounter == 0) {
            enemies.add(new Enemy(0, 300, enemyHealth, enemySpeed));
            spawnCounter = 100; // Delay between spawns
        } else {
            spawnCounter--;
        }
    }
}
