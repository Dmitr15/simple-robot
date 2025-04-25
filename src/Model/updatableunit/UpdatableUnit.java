package Model.updatableunit;

import Model.ownership.Unit;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

// Игровой объект с изменяемым состоянием
abstract public class UpdatableUnit extends Unit {

    // ---------------------------- Cлушатели и события ------------------------
    private final List<StateChangeListener> _listeners = new ArrayList<>();

    public void addListener(StateChangeListener listener) {
        _listeners.add(listener);
    }

    protected void fireStateChanged() {
        for (StateChangeListener listener : _listeners) {
            listener.stateChanged( new EventObject(this) );
        }
    }
}
