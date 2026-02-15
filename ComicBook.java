// Concrete Component: The "base" implementation
public class ComicBook implements Comic {
    // Final = immutable after constructor. (2.a: "The database is immutable - the user cannot change the content of the database")
    private final String seriesTitle; // e.g., "A-Force"
    private final int volume;         // e.g., 1
    private final String issue;       // e.g., "1A" (keep as String, since CSV shows alphanumeric like "1A")
    private final String storyTitle;  // Renamed from fullTitle for clarity
    private final String publisher;
    private final String publicationDate;
    private final String creators;    // New: e.g., "Bob Rozakis | Stephen DeStefano | Karl Kesel" (optional)
    private final String principleCharacters; // New: e.g., "Hulk, She-Hulk" (optional, not in CSV)
    private final String description; // New (optional)
    private final double baseValue;

    // Updated constructor with new params (defaults for optionals can be "" or null)
    public ComicBook(String seriesTitle, int volume, String issue, String storyTitle, String publisher, 
                     String publicationDate, String creators, String principleCharacters, String description, double baseValue) {
        this.seriesTitle = seriesTitle;
        this.volume = volume;
        this.issue = issue;
        this.storyTitle = storyTitle;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.creators = creators;
        this.principleCharacters = principleCharacters;
        this.description = description;
        this.baseValue = baseValue;
    }

    // Implementing the Comic interface methods
    @Override
    public String getDescription() {
        // Update to use StringBuilder to prepare for decorators appending their parts (e.g., "[Grade: 5]")
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(publisher).append("] ")
          .append(seriesTitle).append(", Vol. ").append(volume)
          .append(" #").append(issue);
        if (storyTitle != null && !storyTitle.isEmpty()) {  // Check for non-empty
            sb.append(": ").append(storyTitle);
        }
        if (creators != null && !creators.isEmpty()) {     // Append creators if present
            sb.append(" by ").append(creators);
        }
        sb.append(" (").append(publicationDate).append(")");
        return sb.toString();
        // Example output: [Marvel Comics] A-Force, Vol. 1 #1A: Secret Wars by G. Willow Wilson | Jorge Molina (May 20, 2015)
    }

    @Override
    public double getValue() {
        return baseValue;
    }

    // Add getters if needed for other code (e.g., search on principleCharacters/description per req 5.a)
    public String getPrincipleCharacters() {
        return principleCharacters;
    }

    public String getDescriptionField() {  // To distinguish from getDescription() method
        return description;
    }

    public String getCreators() {
        return creators;
    }
    
    // ... (add more getters as needed for req 5 searches/sorts)
}