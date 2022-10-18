import com.codecool.roguelike.Board;
import com.codecool.roguelike.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Gate {
    private static char defaultHorizontalIcon = '=';
    private static char defaultVerticalIcon = '"';
    private static List<Gate> gates = new ArrayList<>();
    private char gateIcon;
    private Coordinates coordinates;

    public Gate(Coordinates coordinates, int heightOfBoard) {
        this.coordinates = coordinates;
        int y = coordinates.getVerticalCoordinate();
        if (y == 0 || y == heightOfBoard - 1) {
            gateIcon = defaultHorizontalIcon;
        } else{
            gateIcon = defaultVerticalIcon;
        }
        gates.add(this);
    }

    public static List<Gate> getGates() {
        return gates;
    }

    public static char getDefaultHorizontalIcon() {
        return defaultHorizontalIcon;
    }

    public static void setDefaultHorizontalIcon(char defaultHorizontalIcon) {
        Gate.defaultHorizontalIcon = defaultHorizontalIcon;
    }

    public static char getDefaultVerticalIcon() {
        return defaultVerticalIcon;
    }

    public static void setDefaultVerticalIcon(char defaultVerticalIcon) {
        Gate.defaultVerticalIcon = defaultVerticalIcon;
    }

    public char getGateIcon() {
        return gateIcon;
    }

    public void setGateIcon(char gateIcon) {
        this.gateIcon = gateIcon;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
