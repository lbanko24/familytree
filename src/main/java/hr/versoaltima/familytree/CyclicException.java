package hr.versoaltima.familytree;

/**
 * Exception thrown when there is a cycle inside an acyclic graph
 * 
 * @author Leon Banko
 *
 */
public class CyclicException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new cyclic exception
	 */
	public CyclicException() {
		super("Tree contains a cycle");
	}
}
