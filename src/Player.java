public class Player {
    private int id;
    private String name;

    private static int ID = 0;

    public Player(String name) {
        this.id = (++ID);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Player) || (((Player) o).id != this.id)) {
            return false;
        }
        return true;
    }
}
