package fr.ncouret.aoc2023.day3;

import java.util.ArrayList;
import java.util.List;

public class SubMatrix extends Matrix {

    private final int rowOffset;
    private final int colOffset;

    public SubMatrix(List<String> input, int row, int col) {
        super();

        rows = input.size();
        cols = input.getFirst().length();
        data = new ArrayList<>(input);

        rowOffset = row;
        colOffset = col;
    }

    @Override
    public boolean containsIndex(int row, int col) {
        return row >= rowOffset && row < rowOffset + rows && col >= colOffset && col < colOffset + cols;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
}
