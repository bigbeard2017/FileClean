package FileClean.entry;

public enum SearchType {
    REGULAR(3),
    EXTENTION(1),
    NAME(2);

    private int index;

    SearchType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
