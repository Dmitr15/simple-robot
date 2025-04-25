package Model.gamefield;

// Абстрактный заполнитель поля
abstract public class Seeder {

    protected GameField _field;

    void setField(GameField field) {
        _field = field;
    }

    public void run() {
        seedRobot();
        seedCharacteristics();
        seedUnits();
    }

    abstract protected void seedRobot();

    abstract protected void seedCharacteristics();

    abstract protected void seedUnits();
}
