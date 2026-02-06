// Concrete Component: The "base" implementation
public class ComicBook implements Comic {
    // Fields based on CSV columns, need to add more like volume #
    private String series;
    private String issue;
    private String fullTitle;
    private String publisher;
    private double baseValue;


    // Constructor
    public ComicBook(String series, String issue, String fullTitle, String publisher, double baseValue) {
        this.series = series;
        this.issue = issue;
        this.fullTitle = fullTitle;
        this.publisher = publisher;
        this.baseValue = baseValue;
    }
    // Implementing the Comic interface methods

    @Override
    public double getValue() {
        return baseValue;
    }

    @Override
    public String getDescription() {
        // Format: [Publisher] Series #Issue: Full Title
        return "[" + publisher + "] " + series + " #" + issue + ": " + fullTitle;
    }
}
