public class SlabbedDecorator extends ComicDecorator {
    private int grade;

    public SlabbedDecorator(Comic innerComic, int grade) {
        super(innerComic); // Passes the comic up to the "protected" field in the parent;
        this.grade = grade;
    }

    @Override
    public double getValue() {
        double gradedValue = tempComic.getValue();  // Get the value from the inner graded comic
        return gradedValue * 2.0;  // Double it, as per requirements
    }

    @Override
    public String getDescription() {
        // Build on the inner description, adding a "Slabbed" indicator
        StringBuilder sb = new StringBuilder(tempComic.getDescription());
    sb.append(" [Slabbed, Grade: ").append(grade).append("]");
    return sb.toString();
        //return tempComic.getDescription() + " [Slabbed, Grade: " + grade + "]";
    }
}
