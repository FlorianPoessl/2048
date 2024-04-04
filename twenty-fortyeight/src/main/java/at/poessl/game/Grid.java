package at.poessl.game;

import at.poessl.strategy.Move;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Grid {
	
	private int[][] field;

	private Map<Integer, Integer> newValues;
	
	private static final int FIELD_SIZE = 4;

	public Grid() {
		newValues = new LinkedHashMap<>();
		newValues.put(2, 85);
		newValues.put(4, 15);
		
		this.field = new int[FIELD_SIZE][FIELD_SIZE];
		initializeBoard();
	}

	private void initializeBoard() {
		for (int row = 0; row < FIELD_SIZE; row++) {
			for (int col = 0; col < FIELD_SIZE; col++) {
				this.field[row][col] = 0;
			}
		}
	}
	
	public MoveResult executeMove(Move move) {
		switch (move) {
		case LEFT:
			return this.moveLeft();
		case RIGHT:
			return this.moveRight();
		case UP:
			return this.moveUp();
		case DOWN:
			return this.moveDown();
		}
		return new MoveResult(false, false,0);
	}
	
	private MoveResult moveLeft() {
		int points = 0;
		boolean compacted = false;
		boolean moved = false;
		for (int row = 0; row < FIELD_SIZE ; row++) {
			int[] currentRow = this.field[row];
			CompactResult result = this.compactEntries(currentRow);
			currentRow = result.getResult();
			points += result.getPoints();
			compacted = compacted || result.isCompacted();
			moved = moved || result.isMoved();
			this.field[row] = currentRow;
		}
		return new MoveResult(compacted, moved, points);
	}

	private MoveResult moveRight() {
		int points = 0;
		boolean compacted = false;
		boolean moved = false;
		for (int row = 0; row < FIELD_SIZE ; row++) {
			int[] currentRow = this.revertArray(this.field[row]);
			CompactResult result = this.compactEntries(currentRow);
			currentRow = result.getResult();
			points += result.getPoints();
			compacted = compacted || result.isCompacted();
			moved = moved || result.isMoved();
			this.field[row] = this.revertArray(currentRow);
		}
		return new MoveResult(compacted, moved, points);
	}

	private MoveResult moveUp() {
		int points = 0;
		boolean compacted = false;
		boolean moved = false;
		for (int col = 0; col < FIELD_SIZE; col++) {
			int[] currentCol = new int[FIELD_SIZE];
			for (int row = 0; row < FIELD_SIZE; row++) {
				currentCol[row] = this.field[row][col];
			}
			CompactResult result = this.compactEntries(currentCol);
			currentCol = result.getResult();
			points += result.getPoints();
			compacted = compacted || result.isCompacted();
			moved = moved || result.isMoved();
			for (int row = 0; row < FIELD_SIZE; row++) {
				this.field[row][col] = currentCol[row];
			}
		}
		return new MoveResult(compacted, moved, points);
	}

	private MoveResult moveDown() {
		int points = 0;
		boolean compacted = false;
		boolean moved = false;
		for (int col = 0; col < FIELD_SIZE; col++) {
			int[] currentCol = new int[FIELD_SIZE];
			for (int row = 0; row < FIELD_SIZE; row++) {
				currentCol[row] = this.field[row][col];
			}
			currentCol = this.revertArray(currentCol);
			CompactResult result = this.compactEntries(currentCol);
			currentCol = result.getResult();
			points += result.getPoints();
			compacted = compacted || result.isCompacted();
			moved = moved || result.isMoved();
			currentCol = this.revertArray(currentCol);
			for (int row = 0; row < FIELD_SIZE; row++) {
				this.field[row][col] = currentCol[row];
			}
		}
		return new MoveResult(compacted, moved, points);
	}
	
	public CompactResult compactEntries(int[] input) {
		int[] result = new int[input.length];
		boolean compacted = false;
		
		int points = 0;
		
		MoveZerosResult moveZerosResult = this.moveZeros(input);
		int[] trimZero = moveZerosResult.getResult();
		
		for (int i = 0; i < trimZero.length; i++) {
			int nextCell = i+1;
			if (nextCell < trimZero.length && trimZero[i] == trimZero[nextCell] && trimZero[i] != 0) {
				result[i] = trimZero[i] * 2;
				compacted = true;
				points += result[i];
				trimZero[i] = result[i];
				for (int rest = nextCell; rest < trimZero.length - 1; rest++) {
					trimZero[rest] = trimZero[rest+1];
				}
				trimZero[trimZero.length-1] = 0;
			} else {
				result[i] = trimZero[i];
			}
		}
		return new CompactResult(result, compacted, moveZerosResult.isMoved(), points);
	}

	public MoveZerosResult moveZeros(int[] nums) {
		int nonZeroPointer = 0;
		boolean moved = false;
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[nonZeroPointer] = nums[i];
				if (nonZeroPointer != i) {
					nums[i] = 0;
					moved = true;
				}
				nonZeroPointer++;
			}
		}
		return new MoveZerosResult(nums, moved);
	}
	
	public int fillGridCoordinate(GridCoordinate coordinate) {
		int maxChance = newValues.values().stream().reduce(0, Integer::sum);
		Random random = new Random();
		int randomInt = random.nextInt(maxChance+1);
		
		int fillValue = 2;
		
		int currentMax = 0;
		for(Map.Entry<Integer, Integer> entry : this.newValues.entrySet()) {
			currentMax += entry.getValue();
			if (randomInt<currentMax) {
				fillValue = entry.getKey();
			}
		}
		
		this.field[coordinate.getRow()][coordinate.getCol()] = fillValue;
		return fillValue;
	}
	
	public GridCoordinate getRandomEmptyCell() {
		List<GridCoordinate> emptyCoordinates = new ArrayList<>();
		for (int row = 0; row < FIELD_SIZE; row++) {
			for (int col = 0; col < FIELD_SIZE; col++) {
				if (this.gridFieldIsEmpty(this.field[row][col])) {
					emptyCoordinates.add(new GridCoordinate(row, col));
				}
			}
		}
		
		if (emptyCoordinates.isEmpty()) {
			return null;
		}
		
		Random random = new Random();

		int randomNumber = random.nextInt(emptyCoordinates.size());
		return emptyCoordinates.get(randomNumber);
	}

	public boolean gridFieldIsEmpty(int fieldEntry) {
		return fieldEntry == 0;
	}
	
	public int[][] getField() {
		return field;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	private int[] revertArray(int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		return arr;
	}
}
