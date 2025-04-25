package Model.gamefield;

//  Позиция ячейки
public class CellPosition {

    // ----------------------- Свойства --------------------------
    private final int _row;
    private final int _column;

    public int row() { return _row; }

    public int column() { return _column; }

    private void validate(int row, int col) {
        if(row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    // ----------------------- Порождение --------------------------
    public CellPosition(int row, int col) {

        validate(row, col);

        _row = row;
        _column = col;
    }

    // ------------------ "Соседние" позиции ----------------
    public CellPosition shift(int row_delta, int col_delta) {
        int new_row = row() + row_delta;
        int new_col = column() + col_delta;

        validate(new_row, new_col);

        return new CellPosition(new_row, new_col);
    }

    public CellPosition shift(Direction direct, int delta) {

        if(delta <= 0) {
            throw new IllegalArgumentException();
        }

        int row_delta = 0, col_delta = 0;

        if(direct.equals( Direction.east() )) {
            row_delta = 0; col_delta += delta;
        } else if(direct.equals( Direction.west() )) {
            row_delta = 0; col_delta -= delta;
        } else if(direct.equals( Direction.north() )) {
            row_delta -= delta; col_delta = 0;
        }else if(direct.equals( Direction.south() )) {
            row_delta += delta; col_delta = 0;
        } else {
            throw new RuntimeException();
        }

        return shift(row_delta, col_delta);
    }

    // ------------------ Сравнение позиций ----------------
    @Override
    public boolean equals(Object other){

        if(other instanceof CellPosition) {
            // Типы совместимы, можно провести преобразование
            CellPosition otherPosition = (CellPosition)other;
            // Возвращаем результат сравнения углов
            return row() == otherPosition.row() && column() == otherPosition.column();
        }

        return false;
    }

    public boolean isNeighbor(CellPosition other) {

        int row_delta = Math.abs( row() - other.row() );
        int col_delta = Math.abs( column() - other.column() );

        return  (row_delta == 0 && col_delta == 1) || (row_delta == 1 && col_delta == 0);
    }

    // --------------------------------------------------
    @Override
    public int hashCode() {
        // Одинаковые объекты должны возвращать одинаковые значения
        return row() * 1000 + column();
    }
}
