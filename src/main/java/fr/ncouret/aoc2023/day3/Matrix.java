package fr.ncouret.aoc2023.day3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {

    protected static final char PERIOD = '.';
    protected List<String> data;
    protected int rows;
    protected int cols;

    protected Matrix() {

    }

    public Matrix(List<String> input) {
        String emptyRow = new StringBuilder().repeat(PERIOD, input.get(0).length() + 2).toString();
        var lines = input.stream().map(line -> '.' + line + '.').collect(Collectors.toCollection(ArrayDeque::new));
        lines.addFirst(emptyRow);
        lines.addLast(emptyRow);

        rows = lines.size();
        cols = lines.getFirst().length();
        data = lines.stream().toList();
    }

    public boolean isDigit(int row, int col) {
        return Character.isDigit(charAt(row, col));
    }

    public boolean containsIndex(int row, int col) {
        return row >= 0 && row < this.rows && col >= 0 && col <= this.cols;
    }

    public boolean containsSymbol() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = charAt(i, j);
                if (!(Character.isDigit(c) || PERIOD == c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public char charAt(int row, int col) {
        return data.get(row).charAt(col);
    }

    public String numberAt(int row, int col) {
        StringBuilder builder = new StringBuilder();
        for (int i = col; i < cols - 1; i++) {
            char c = charAt(row, i);
            if (!Character.isDigit(c)) {
                break;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    public SubMatrix matrixAroundNumber(int row, int col) {
        String number = numberAt(row, col);
        return subMatrix(row - 1, col - 1, row + 2, (col + number.length() + 1));
    }

    public SubMatrix subMatrix(int rowStart, int colStart, int rowEnd, int colEnd) {
        List<String> lines = IntStream.range(rowStart, rowEnd)
            .mapToObj(data::get)
            .map(l -> l.substring(colStart, colEnd))
            .toList();
        return new SubMatrix(lines, rowStart, colStart);
    }

    public List<SubMatrix> numberMatrices() {
        List<SubMatrix> matrices = new ArrayList<>();
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (isDigit(i, j)) {
                    String number = numberAt(i, j);
                    matrices.add(matrixAroundNumber(i, j));
                    j += (number.length() - 1);
                }
            }
        }
        return matrices;
    }

    public List<SubMatrix> allForCharacter(char toFind) {
        List<SubMatrix> matrices = new ArrayList<>();
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                char c = charAt(i, j);
                if (c == toFind) {
                    matrices.add(subMatrix(i, j, i + 1, j + 1));
                }
            }
        }
        return matrices;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[\n");
        data.forEach(line -> builder.append(line).append("\n"));
        builder.append("]");
        return builder.toString();
    }
}
