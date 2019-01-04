package calculable;

/**
 * Interface of classes that can calculate and return values of some type.
 * @param <C> type of returned values
 * @author Bartłomiej Kuśmirek
 */
public interface Calculable<C> {

    /**
     * Calculates and returns value of type <C>.
     * @return some value of type <C>
     */
    C calculate();

}
