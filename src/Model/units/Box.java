package Model.units;

import Model.ownership.Unit;
import Model.updatableunit.UpdatableUnit;

// Коробка, которая может открываться ключом
public class Box extends UpdatableUnit {

    // ------------------- Открывание коробки ---------------------------

    private boolean _isOpen = false;
    
    public boolean isOpen() {
        return _isOpen;
    }


    public void open(Key key) {
        if( key.belongTo(Robot.class) && key.isNeighbor(this) ) {
            _isOpen = true;

            fireStateChanged();
        }
    }



    public void open() {
        if(!_isOpen){
            _isOpen = true;
            //key = null;
            fireStateChanged();
        }
    }




    // -----------------------------------------------------------------

    @Override
    public String toString() {

        String msg = "B";

        msg += "(";
        if( isOpen() ) {
            msg += "o";
        } else {
            msg += "c";
        }
        msg += ")";

        return msg;
    }
}
