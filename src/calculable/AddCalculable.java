package calculable;

/**
 * Interface merging Addable<C> and Calculable<C> interfaces.
 * @param <C>
 * @author Bartłomiej Kuśmirek
 */
public interface AddCalculable<C> extends Addable<C>, Calculable<C> {}
