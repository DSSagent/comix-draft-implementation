import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ComixTester {
    public static void main(String[] args) {
        List<Comic> database = loadDatabase("comics.csv");  // Load all ~3000 comics
        if (database.isEmpty()) {
            System.out.println("No comics loaded!");
            return;
        }

        // Test basic loading (requirement 2)
        System.out.println("First comic: " + database.get(0).getDescription());
        System.out.println("Value: $" + database.get(0).getValue());

        // Test Decorator (my subsystem, requirement 3.e/f)
        Comic firstComic = database.get(0);
        Comic graded = new GradedDecorator(firstComic, 8);  // Grade 8: log10(8)*baseValue
        Comic slabbed = new SlabbedDecorator(graded, 8);     // Double the graded value
        System.out.println("Graded & Slabbed: " + slabbed.getDescription());
        System.out.println("Enhanced Value: $" + slabbed.getValue());

        // Test search (partial, for requirement 2.c - expand later)
        String searchTerm = "Hulk";  // Case-insensitive partial match
        List<Comic> results = searchDatabase(database, searchTerm);
        System.out.println("Found " + results.size() + " comics with 'Hulk':");
        for (int i = 0; i < Math.min(5, results.size()); i++) {  // Print first 5
            System.out.println(results.get(i).getDescription());
        }
    }

    // Helper: Load from CSV (like ComicLoader, but full file)
    private static List<Comic> loadDatabase(String filePath) {
        List<Comic> comics = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();  // Skip the 3 headers 
            br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null) {
                Comic comic = parseCsvLine(line);  // Parsing logic
                if (comic != null) comics.add(comic);
            }
        } catch (IOException e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
        return List.copyOf(comics);  // Immutable as per requirement 2.a
    }

    // Parsing method
    private static Comic parseCsvLine(String csvLine) {
        // Better split: Use regex to split on commas not inside quotes
        // This pattern: Split on , surrounded by even quotes (basic quote handling)
        String[] parts = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length < 5) return null;  // Now check for at least 5, but there can be up to 9

        // Trim quotes if present (e.g., "\"A-Force, Vol. 1\"" → "A-Force, Vol. 1")
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim().replaceAll("^\"|\"$", "");  // Remove leading/trailing quotes
        }

        String rawSeries = parts[0];  // "A-Force, Vol. 1"
        String seriesTitle = rawSeries;
        int volume = 1;
        if (rawSeries.contains(", Vol. ")) {
            String[] split = rawSeries.split(", Vol. ");
            seriesTitle = split[0];
            if (split.length > 1) {
                volume = Integer.parseInt(split[1].replaceAll("[^0-9]", ""));  // Clean non-digits
            }
        }
        String issue = parts[1];             // "1A"
        String storyTitle = parts[2];        // "Secret Wars"
        // parts[3] = Variant Description (ignore for now, or add to description if needed)
        String publisher = parts[4];         // "Marvel Comics" (shifted index due to extra columns)
        String publicationDate = parts[5];   // "May 20, 2015"
        // parts[6] = Format (e.g., "Comic")
        // parts[7] = Added Date (ignore)
        // parts[8] = Creators (add to ComicBook later per req 1.f/2.b.vi)
        String creators = parts.length > 8 ? parts[8] : "";  // Last column from SS
        String principleCharacters = "";                     // Not in CSV, default empty
        String description = "";                             // default empty
        double baseValue = 5.0;  // Still placeholder (req 1.i optional—later compute or edit)

        return new ComicBook(seriesTitle, volume, issue, storyTitle, publisher, publicationDate, 
                            creators, principleCharacters, description, baseValue);
    }

    // Simple search helper (requirement 2.c: partial, case-insensitive)
    private static List<Comic> searchDatabase(List<Comic> database, String term) {
        List<Comic> results = new ArrayList<>();
        String lowerTerm = term.toLowerCase();
        for (Comic comic : database) {
            if (comic.getDescription().toLowerCase().contains(lowerTerm)) {
                results.add(comic);
            }
        }
        return results;
    }
}
