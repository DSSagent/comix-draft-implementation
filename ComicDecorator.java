// Decorator: The abstract wrapper
public abstract class ComicDecorator implements Comic {
    protected Comic tempComic; // The object being wrapped
    public ComicDecorator(Comic newComic) {
        this.tempComic = newComic;
    }
}