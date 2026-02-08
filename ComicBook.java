// Concrete Component: The "base" implementation
public class ComicBook implements Comic {
    // Fields based on CSV columns
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
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(publisher).append("] ")
      .append(seriesTitle).append(", Vol. ").append(volume)
      .append(" #").append(issue).append(": ").append(storyTitle)
      .append(" (").append(publicationDate).append(")");
        return sb.toString();
    }


    @Override
    public double getValue() {
        return baseValue;
    }

    
}
