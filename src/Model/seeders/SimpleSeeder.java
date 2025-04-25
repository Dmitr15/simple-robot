package Model.seeders;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Seeder;
import Model.units.Box;
import Model.units.Key;

public class SimpleSeeder extends Seeder {

    @Override
    protected void seedRobot() {
        _field.cell(0, 0).putUnit( _field.robot() );
    }

    @Override
    protected void seedCharacteristics() {
        for (Cell c: _field ) {
            c.setCharacteristic("temperature", -1);
            c.setCharacteristic("radiation", 1.2);
        }
    }

    @Override
    protected void seedUnits() {

        Cell underRobotCell = _field.robot().typedOwner();
        underRobotCell = underRobotCell.neighbor( Direction.south()) ;
        underRobotCell.putUnit( new Box() );


        _field.cell(2, 1).putUnit( new Key() );

    }
}
