package FileClean.entry;

public class CleanEntry {
    private SearchType searchType;
    private String searcheValue;


    public CleanEntry() {
    }

    public CleanEntry(SearchType searchType, String searcheValue) {
        this.searcheValue = searcheValue;
        this.searchType = searchType;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public String getSearcheValue() {
        return searcheValue;
    }

    public void setSearcheValue(String searcheValue) {
        this.searcheValue = searcheValue;
    }
}
