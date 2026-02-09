// Concrete Component: The "base" implementation
public class ComicBook implements Comic {
    // Fields based on CSV columns
    // Final = immutable after constructor. (2.a: "The database is immutable - the user cannot change the content of the database")
    private final String seriesTitle; // e.g., "A-Force"
    private final int volume;         // e.g., 1
    private final String issue;       // e.g., "1A" (keep as String, since CSV shows alphanumeric like "1A")
    private final String storyTitle;  // Renamed from fullTitle for clarity
    private final String publisher;
    private final String publicationDate;
    private final double baseValue;

    // Constructor
    public ComicBook(String seriesTitle, int volume, String issue, String storyTitle, String publisher, String publicationDate, double baseValue) {
        this.seriesTitle = seriesTitle;
        this.volume = volume;
        this.issue = issue;
        this.storyTitle = storyTitle;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.baseValue = baseValue;
    }

    // Implementing the Comic interface methods
    @Override
    public String getDescription() {
        // Update ComicBook's getDescription() to use StringBuilder to prepare for decorators appending their parts (e.g., "[Grade: 5]"
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(publisher).append("] ")
      .append(seriesTitle).append(", Vol. ").append(volume)
      .append(" #").append(issue).append(": ").append(storyTitle)
      .append(" (").append(publicationDate).append(")");
        return sb.toString();
        // Example output: [Marvel Comics] A-Force, Vol. 1 #1A: Secret Wars (May 20, 2015)
    }


    @Override
    public double getValue() {
        return baseValue;
    }

    
}
