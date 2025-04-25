package Model.units;

import Model.ownership.Unit;
import Model.updatableunit.UpdatableUnit;

public class WallWithLock extends Wall{


    @Override
    public void open(Key... key) {
        if( key[0].belongTo(Robot.class) && key[0].isNeighbor(this) ) {
            _isOpen = true;
            //this.
        }

    }

    @Override
    public String toString() {
        return "W(Lock)";
    }
}
