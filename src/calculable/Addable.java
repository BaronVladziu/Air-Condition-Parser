package calculable;

/**
 * Interface of classes that can add values of some type to itself.
 * @param <A> type of added values
 * @author Bartłomiej Kuśmirek
 */
public interface Addable<A> {

    /**
     * Add value of type A to instance af Addable<A>.
     * @param value added value
     */
    void add(A value);

}
