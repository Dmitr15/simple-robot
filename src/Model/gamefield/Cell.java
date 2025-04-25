package Model.gamefield;

import Model.ownership.CanOwnUnit;
import Model.ownership.OwnershipProperty;
import Model.ownership.Unit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Ячейка поля
public class Cell implements CanOwnUnit {

    // ----------------------- Позиция --------------------------

    private final CellPosition _pos;

    public CellPosition position() {
        return _pos;
    }


    // ----------------------- Порождение --------------------------

    public Cell(CellPosition position) {
        _pos = position;
    }

    // ---------------------- Соседи -----------------------
    private final Map<Direction, Cell> _neighbors = new HashMap<>();
    
    public Cell neighbor(Direction direct) {

        if(_neighbors.containsKey(direct)) {
            return _neighbors.get(direct);
        }        
        
        return null;
    }

    public Map<Direction, Cell> getNeighbors(){
        return _neighbors;
    }

    public Map<Direction, Cell> neighbors() {
        return Collections.unmodifiableMap(_neighbors);
    }
    
    void setNeighbor(Direction direct, Cell neighbor) {
        if(neighbor != this && !isNeighbor(neighbor)) {
            _neighbors.put(direct, neighbor);
            neighbor.setNeighbor(direct.opposite(), this);
        }
    }
    
    public boolean isNeighbor(Cell other) {
        return _neighbors.containsValue(other);
    }


    // ------------------------- Характеристики -----------------------------------

    private final Map<String, Double> _characteristics = new HashMap<>();
    
    public double characteristic(String name) {
        return _characteristics.get(name);
    }
    
    public void setCharacteristic(String name, double value) {
        _characteristics.put(name, value);
    }

    public Map<String, Double> characteristics() {
        return Collections.unmodifiableMap(_characteristics);
    }
    
    public void incCharacteristic(String name, double delta, double max) {
        double newValue = _characteristics.get(name) + delta;
        if(newValue > max) {
            newValue = max;
        }
        _characteristics.put(name, newValue);
    }

    public void decCharacteristic(String name, double delta, double min) {
        double newValue = _characteristics.get(name) - delta;
        if(newValue < min) {
            newValue = min;
        }
        _characteristics.put(name, newValue);
    }

    // ------------------------------- Владение юнитом ---------------------------------
    private final OwnershipProperty _ownership = new OwnershipProperty(this);

    public Unit getUnit() {
        return _ownership.getUnit();
    }
    
    public boolean isEmpty() {
        return _ownership.isEmpty();
    }
    
    public boolean putUnit(Unit unit) {
        return _ownership.putUnit(unit);
    }
    
    public Unit extractUnit() {
        return _ownership.extractUnit();
    }
}
