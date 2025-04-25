package Model.ownership;

// Может владеть юнитом
public interface CanOwnUnit {

    Unit getUnit();
    boolean isEmpty();

    boolean putUnit(Unit init);
    Unit extractUnit();
}
