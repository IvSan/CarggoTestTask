package xyz.hardliner.carggo;

import xyz.hardliner.carggo.domain.Cell;
import xyz.hardliner.carggo.domain.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Counter {

	private static final String LAND = "*";
	private static final String WATER = "~";

	public static void main(String[] args) throws IOException {
		World world;

		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			String[] sizes = in.readLine().split(" ");
			int xSize = Integer.parseInt(sizes[1]);
			int ySize = Integer.parseInt(sizes[0]);
			world = new World(xSize, ySize);

			for (int i = 1; i < ySize + 1; i++) {
				String worldLine = in.readLine();
				for (int j = 1; j < xSize + 1; j++) {
					String value = worldLine.substring(j - 1, j);
					if (value.equals(LAND)) {
						world.getCells().put(new Cell(j, i), true);
					} else if (!value.equals(WATER)) {
						throw new IllegalArgumentException("Wrong map symbol");
					}
				}
			}
		}

		System.out.println(countIslands(world));
	}

	private static int countIslands(World world) {
		int islandCounter = 0;
		Set<Cell> visitedIslands = new HashSet<>();
		Map<Cell, Boolean> cells = world.getCells();
		for (Cell cell : world.getCells().keySet()) {
			if (!cells.get(cell)) {
				continue;
			}
			if (visitedIslands.contains(cell)) {
				continue;
			}
			islandCounter++;
			Set<Cell> currentIsland = new HashSet<>();
			checkAllLandsOfCurrentIsland(world, currentIsland, cell);
			visitedIslands.addAll(currentIsland);
		}
		return islandCounter;
	}

	private static void checkAllLandsOfCurrentIsland(World world, Set<Cell> currentIsland, Cell cell) {
		currentIsland.add(cell);

		if (cell.getY() > 1) {
			Cell upperCell = new Cell(cell.getX(), cell.getY() - 1);
			if (!currentIsland.contains(upperCell) && world.getCells().get(upperCell)) {
				checkAllLandsOfCurrentIsland(world, currentIsland, upperCell);
			}
		}

		if (cell.getY() < world.getYSize()) {
			Cell lowerCell = new Cell(cell.getX(), cell.getY() + 1);
			if (!currentIsland.contains(lowerCell) && world.getCells().get(lowerCell)) {
				checkAllLandsOfCurrentIsland(world, currentIsland, lowerCell);
			}
		}

		if (cell.getX() > 1) {
			Cell leftCell = new Cell(cell.getX() - 1, cell.getY());
			if (!currentIsland.contains(leftCell) && world.getCells().get(leftCell)) {
				checkAllLandsOfCurrentIsland(world, currentIsland, leftCell);
			}
		}

		if (cell.getX() < world.getXSize()) {
			Cell rightCell = new Cell(cell.getX() + 1, cell.getY());
			if (!currentIsland.contains(rightCell) && world.getCells().get(rightCell)) {
				checkAllLandsOfCurrentIsland(world, currentIsland, rightCell);
			}
		}
	}

}
