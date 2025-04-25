package Model.units;

import Model.ownership.Unit;

// Фрагмент стены
public abstract class Wall extends Unit {

    protected boolean _isOpen = false;

    public boolean isOpen() {
        return _isOpen;
    }

    public abstract void open(Key ... key);

    @Override
    public abstract String toString();


    /*
    @Override
    public String toString() {
        return "W";
    }

     */

}
