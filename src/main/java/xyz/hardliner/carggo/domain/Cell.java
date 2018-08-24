package xyz.hardliner.carggo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Cell {
	private int x;
	private int y;
}
