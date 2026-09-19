package week7.assigment_problems;

class Character {
    private final int maxHealth;
    private int health;

    public Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public int getHealth() {
        return health;
    }
}

public class CharacterHealth {
    public static void main(String[] args) {
        Character c = new Character(100);
        c.takeDamage(30);
        System.out.println("health = " + c.getHealth());

        c.heal(50);
        System.out.println("health = " + c.getHealth() + " (capped)");

        c.takeDamage(150);
        System.out.println("health = " + c.getHealth() + " (floored)");
    }
}