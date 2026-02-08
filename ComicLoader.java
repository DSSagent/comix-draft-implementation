import java.util.List;  // For returning lists later
// Add imports for I/O: java.io.BufferedReader, java.io.FileReader, etc.

public class ComicLoader {
    public static Comic loadComicFromCSVLine(String csvLine) {  // Parse one line (for simplicity)
        // Split CSV line: e.g., "A-Force, Vol. 1","1A","Secret Wars","Marvel Comics","May 20, 2015"
        String[] parts = csvLine.split(",");  // Basic split; use a CSV library later for quotes/escapes
        String rawSeries = parts[0].trim();
        
        // Parse series for title and volume (requirement 2.b.i)
        String seriesTitle = rawSeries;
        int volume = 1;  // Default
        if (rawSeries.contains(", Vol. ")) {
            String[] split = rawSeries.split(", Vol. ");
            seriesTitle = split[0];
            volume = Integer.parseInt(split[1]);  // Convert string to int
        }
        
        String issue = parts[1].trim();
        String storyTitle = parts[2].trim();
        String publisher = parts[3].trim();
        String publicationDate = parts[4].trim();
        double baseValue = 5.0;  // Placeholder; parse from CSV if available
        
        return new ComicBook(seriesTitle, volume, issue, storyTitle, publisher, publicationDate, baseValue);
    }
}