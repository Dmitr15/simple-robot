package Model.units;

import Model.gamefield.Direction;
import Model.gamefield.Cell;
import Model.ownership.CanOwnUnit;
import Model.ownership.OwnershipProperty;
import Model.ownership.Unit;
import Model.updatableunit.UpdatableUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Робот, который может перемещаться по полю и взаимодействовать с другими юнитами
public class Robot extends UpdatableUnit implements CanOwnUnit {

    // ------------------------------- Заряд ---------------------------------
    private int _charge = 25;
    //private Unit _Key;


    private static final int REQUIRED_CHARGE_FOR_MOVE = 1;
    
    public int charge() {
        return _charge;
    }

    /*
    private Unit Key(){
        return _Key;
    }
     */


    public boolean isAvailableCharge(int chargeQuery) {
        return chargeQuery <= _charge;
    }
    
    protected int reduceCharge(int chargeQuery) {
        int retrievedCharge = Math.min(_charge, chargeQuery);
        _charge -= retrievedCharge;
        return retrievedCharge;
    }

    // --------------------------- Перемещение ------------------------------------
    public boolean canMoveTo(Cell to) {
        return to.isEmpty();
    }
    
    public void move(Direction direct) {
       
        Cell pos = typedOwner();
        
        if(!isAvailableCharge(REQUIRED_CHARGE_FOR_MOVE)) {
            return;
        }
        
        Cell newPos = pos.neighbor(direct);
        if(newPos == null) {
            return;
        }

        if(!canMoveTo(newPos)) {
            return;
        }
        
        pos.extractUnit();
        newPos.putUnit(this);
        reduceCharge(REQUIRED_CHARGE_FOR_MOVE);

        fireStateChanged();
    }


    public void openBox(){
        Cell pos = typedOwner();
        Unit neighboringUnit;

        Map<Direction, Cell> neighborDirect = new HashMap<>();
        neighborDirect = pos.getNeighbors();

        if(_ownership.getUnit() != null){
            for(Map.Entry<Direction, Cell> dir: neighborDirect.entrySet()){
                Cell cell = dir.getValue();
                neighboringUnit = cell.getUnit();
                if(neighboringUnit instanceof Box){
                    //_Key = cell.extractUnit();

                    if(!((Box) neighboringUnit).isOpen()){

                        ((Box) neighboringUnit).open((Key) this.getUnit());

                        this.extractUnit();
                        return;
                    }


                }
            }
        }
    }



    public void getKey(){
        Cell pos = typedOwner();
        Unit neighboringUnit;
        Unit _Key;
        Map<Direction, Cell> neighborDirect = new HashMap<>();
        neighborDirect = pos.getNeighbors();

        for(Map.Entry<Direction, Cell> dir: neighborDirect.entrySet()){
            Cell cell = dir.getValue();
            neighboringUnit = cell.getUnit();
            if(neighboringUnit instanceof Key){
                _Key = (Key) cell.extractUnit();
                this.putUnit(_Key);
                return;

            }

        }
    }



    // -----------------------------------------------------------------
    private final OwnershipProperty _ownership = new OwnershipProperty(this);

    @Override
    public String toString() {

        String msg;
        msg = "R(" + charge() + ")";

        return msg;
    }

    @Override
    public Unit getUnit() {
        return _ownership.getUnit();
        //return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean putUnit(Unit init) {
        return _ownership.putUnit(init);


    }

    @Override
    public Unit extractUnit() {
        return null;
    }
}
