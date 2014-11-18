import java.util.EnumSet;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.core.Random;
import com.googlecode.blacken.extras.PerlinNoise;
import com.googlecode.blacken.grid.Grid;
import com.googlecode.blacken.grid.Point;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.CellWalls;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalStyle;



public class MazeViewerGUI
{

	private TerminalInterface term;
	private ColorPalette palette;
	private final static Point MAP_START = new Point(0, 0);
    private final static Point MAP_END = new Point(-1, 0);
    private final static int EMPTY_FLOOR = 0x2b;
    private Point _upperLeft = new Point(0, 0);
    private Grid<Integer> _grid;
    private float _noiseplane;
    private int _nextLocation;
    private Maze _maze;
	private Client _client;
	
	public MazeViewerGUI(Maze maze, Client client) {
		_maze = maze;
		_client = client;
		term = new SwingTerminal();
	    term.init("Blacken Example: Stumble", 25, 80);
	    setPalette();
	    initGrid();
	    makeMap();
	}
	
	public void showClientMove() {
		int x = this._client.getRoomCurrentId()/_maze.getNumberRoom();
		int y = _client.getRoomCurrentId()%_maze.getNumberRoom();
		_grid.clear();
		_grid.set(x, y, 0x40);
		System.out.println("x: " + x + "y: " + y + "roomId: " + this._client.getRoomCurrentId());
	}
	
	public void setPalette() {
		palette = new ColorPalette();
	    palette.addAll(ColorNames.XTERM_256_COLORS, false);
        palette.putMapping(ColorNames.SVG_COLORS);
        this.term.setPalette(palette);
	}
	
	public void initGrid() {
		_grid = new Grid<>(new Integer(EMPTY_FLOOR), _maze.getNumberRoom(), _maze.getNumberRoom());
	}
	
	public TerminalInterface getTerminal() {
		return term;
	}
	
	private void makeMap() {
        _grid.clear();
    }
	
	public void showMap() {
		showClientMove();
        int ey = MAP_END.getY();
        int ex = MAP_END.getX();
        if (ey <= 0) {
            ey += term.getHeight();
        }
        if (ey > _grid.getHeight()) {
            ey = _grid.getHeight();
        }
        if (ex <= 0) {
            ex += term.getWidth();
        }
        if (ex > _grid.getWidth()) {
            ex = _grid.getWidth();
        }
        for (int y = MAP_START.getY(); y < ey; y++) {
            for (int x = MAP_START.getX(); x < ex; x++) {
                int y1 = y + _upperLeft.getY() - MAP_START.getY();
                int x1 = x + _upperLeft.getX() - MAP_START.getX();
                int what = ' ';
                if (y1 >= 0 && x1 >= 0 && y1 < _grid.getHeight() && x1 < _grid.getWidth()) {
                    what = _grid.get(y1, x1);
                }
                int fclr = 7;
                int bclr = 0;
                EnumSet<CellWalls> walls = EnumSet.noneOf(CellWalls.class);
                if (what == '@'){
                    bclr = 0xe4;
                    fclr = 0;
                } else if (what == '.') {
                    fclr = (int)(Math.floor(PerlinNoise.noise(x1, y1, _noiseplane) * 10.0F)) + 0xee;
                } else if (what == '#') {
                    fclr = (int)(Math.floor(PerlinNoise.noise(x1, y1, _noiseplane) * 14.0F)) + 0x58;
                } else if (what == ' ') {
                    fclr = (int)(Math.floor(PerlinNoise.noise(x1, y1, _noiseplane) * 38.0F));
                    if (fclr < 0) {
                        fclr *= -1;
                    }
                    if (fclr < 28) {
                        if (fclr > 14) {
                            fclr -= 14;
                        }
                        fclr += 0x58;
                        what = '#';
                    } else {
                        fclr -= 28;
                        fclr += 0xee;
                        what = ':';
                    }
                } else if (what >= '0' || what <= '9') {
                    if (what > _nextLocation) {
                        walls = CellWalls.BOX;
                    }
                    bclr = 0x11;
                    fclr = (what - '0') + 0x4;
                }
                term.set(y, x, new String(Character.toChars(what)), 
                         fclr, bclr, EnumSet.noneOf(TerminalStyle.class), walls);
            }
        }
        
    }
	
		
}
