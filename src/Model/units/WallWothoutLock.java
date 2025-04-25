package Model.units;

public class WallWothoutLock extends Wall{


    @Override
    public void open(Key ... key) {
        _isOpen = true;
    }

    @Override
    public String toString() {
        return "W";
    }
}
