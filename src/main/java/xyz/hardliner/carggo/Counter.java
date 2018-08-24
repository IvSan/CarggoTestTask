package xyz.hardliner.carggo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
						world.getCells().put(new Cell(j, i), 1);
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
		Map<Cell, Integer> cells = world.getCells();
		Set<Pair<Integer, Integer>> merges = new HashSet<>(); // Same islands with different zone numbers.

		//                 0
		// Scanning using 00 mask.

		for (int j = 1; j < world.getYSize() + 1; j++) {
			for (int i = 1; i < world.getXSize() + 1; i++) {
				Integer currentCell = cells.get(new Cell(i, j));
				Integer upperCell = j == 1 ? 0 : cells.get(new Cell(i, j - 1));
				Integer leftCell = i == 1 ? 0 : cells.get(new Cell(i - 1, j));

				//  ?     ?
				// ?0 -> ?0
				if (currentCell == 0) {
					continue;
				}

				//  0     0
				// 01 -> 0n
				if (upperCell == 0 && leftCell == 0) {
					islandCounter++;
					cells.put(new Cell(i, j), islandCounter);
					continue;
				}

				//  0     0
				// n1 -> nn
				if (upperCell == 0) {
					cells.put(new Cell(i, j), leftCell);
					continue;
				}

				//  n     n
				// 01 -> 0n
				if (leftCell == 0) {
					cells.put(new Cell(i, j), upperCell);
					continue;
				}

				//  m     m
				// n1 -> nm
				// Conflict case. Recording merging lands.
				cells.put(new Cell(i, j), upperCell);
				if (!leftCell.equals(upperCell)) {
					merges.add(new ImmutablePair<>(leftCell, upperCell));
				}

			}
		}

		return islandCounter - merges.size();
	}

}
