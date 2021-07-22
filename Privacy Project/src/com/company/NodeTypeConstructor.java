package com.company;

public class NodeTypeConstructor {

    public int column1;
    private int column2;

    public NodeTypeConstructor(int column1, int column2) {
        this.column1 = column1;
        this.column2 = column2;
    }

    public int getColumn1() {
        return column1;
    }

    public int getColumn2() {
        return column2;
    }

    // Compare based on value
    public int compareTo(NodeTypeConstructor otherBar) {
        return Integer.compare(otherBar.column1, column2);
    }

    public String toString() {
        return String.format("(%d, %d)", column1, column2);
    }

}