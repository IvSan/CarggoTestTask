package xyz.hardliner.carggo.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class World {
	private int xSize;
	private int ySize;
	private Map<Cell, Integer> cells;

	public World(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		cells = new HashMap<>();
		for (int i = 1; i < xSize + 1; i++) {
			for (int j = 1; j < ySize + 1; j++) {
				cells.put(new Cell(i, j), 0);
			}
		}
	}
}
