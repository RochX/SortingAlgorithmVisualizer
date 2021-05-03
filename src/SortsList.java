public enum SortsList {
    //BOG("Bogo Sort"),
    BUB("Bubble Sort"),
    COC("Cocktail Sort"),
    INS("Insertion Sort"),
    MER("Merge Sort"),
    QUI("Quick Sort"),
    SEL("Selection Sort"),
    STO("Stooge Sort");

    private String sort;

    private SortsList(String str) {
        sort = str;
    }

    @Override
    public String toString() {
        return sort;
    }
}
