package View;

import Model.gamefield.Cell;
import Model.ownership.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Map;

// Виджет для отрисовки ячейки и всего, что на ней располагается
public class CellWidget extends JPanel {

    public static final int CELL_SIZE = 65;
    private static final Color BACKGROUND_COLOR = new Color(252, 252, 196);
    private static final Color RECT_COLOR = new Color(100, 100, 200);
    private static final Color FONT_COLOR = new Color(54, 190, 82);

    private final Cell _cell;

    public CellWidget(Cell cell) {
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(BACKGROUND_COLOR);

        _cell = cell;

        setToolTipText("");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setStroke( new BasicStroke(2) );
        gr2d.setPaint( RECT_COLOR );

        gr2d.drawRect(0, 0, CELL_SIZE, CELL_SIZE);

        Unit u = _cell.getUnit();

        if(u != null) {
            String msg = u.toString();

            gr2d.setColor( FONT_COLOR );
            gr2d.setFont( new Font( "Microsoft JhengHei Light", Font.BOLD, 20 ));

            FontMetrics fm = g.getFontMetrics();

            int msgWidth = fm.stringWidth( msg );
            int msgHeight = fm.getHeight();

            gr2d.drawString( msg, (CELL_SIZE - msgWidth)/2, CELL_SIZE/2 + msgHeight/4 );
        }
    }

    @Override
    public String getToolTipText(MouseEvent e)  {
        StringBuilder toolTip = new StringBuilder("<html><div align=\"left\">");

        for(Map.Entry entry: _cell.characteristics().entrySet()) {
            toolTip.append(entry.getKey()).append(" = ").append(entry.getValue());
            toolTip.append("<br>");
        }

        toolTip.append("</div></html>");
        return toolTip.toString();
    }
}
