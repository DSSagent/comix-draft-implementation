public class GradedDecorator extends ComicDecorator {
    private int grade;

    public GradedDecorator(Comic innerComic, int grade) {
        super(innerComic); // Passes the comic up to the "protected" field in the parent
        this.grade = grade;
    }

    @Override
    public double getValue() {
        double value = tempComic.getValue(); // Asking the inner object for its value
        // Math logic: 1 = 10% of value, 2-10 = log10(grade) * value
        if (grade == 1) {
            return value * 0.10;
        } else {
            return Math.log10(grade) * value;
        }
    }

    @Override
    public String getDescription() {
        return tempComic.getDescription() + " [Grade: " + grade + "]";
    }
}