package hr.versoaltima.familytree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * A node inside a family tree representing a family member
 * 
 * @author Leon Banko
 *
 */
public class FamilyNode implements Comparable<FamilyNode> {
	
	private String name;
	private Set<FamilyNode> children = new TreeSet<>();
	
	/**
	 * Creates a new node with the specified name
	 * @param name the name of this node
	 */
	public FamilyNode(String name) {
		this.name = name;
	}

	/**
	 * Creates a new blank node
	 */
	public FamilyNode() {
		name = "";
	}
	
	/**
	 * Searches for node with specified name below this node
	 * @param name target node name
	 * @return Optional containing the node with the specified name or empty Optional if not found
	 */
	public Optional<FamilyNode> find(String name) {
		if (name.equals(this.name)) return Optional.of(this);
		
		for (FamilyNode child : children) {
			Optional<FamilyNode> node = child.find(name);
			
			if (node.isPresent()) {
				return node;
			}
		}
		
		return Optional.empty();
	}
	
	/**
	 * Attaches the specified node to this node as child node
	 * @param child the node that needs to be attached
	 */
	public void addChild(FamilyNode child) {
		children.add(child);
		
		if (isCyclic()) {
			throw new CyclicException();
		}
	}
	
	/**
	 * Returns true if the specified node is a direct child of this node
	 * @param child child node
	 * @return true if the specified node is a direct child of this node, false otherwise
	 */
	public boolean hasChild(FamilyNode child) {
		return children.contains(child);
	}
	
	/**
	 * Detaches the specified child node from this node
	 * @param child the node that needs to be detached
	 */
	public void removeChild(FamilyNode child) {
		children.remove(child);
	}
	
	/**
	 * Displays all children of this node (not just direct children) hierarchically, using tabs as indentations to show child-parent relationships
	 * @return A string containing a hierarchical representation of this node's children
	 */
	public String displayChildren() {
		return displayRecursive(0);
	}
	
	private String displayRecursive(int depth) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < depth - 1; i++) {
			sb.append('\t');
		}
		
		if (depth > 0) {
			sb.append(name);
			sb.append('\n');
		}
		
		for (FamilyNode child : children) {
			sb.append(child.displayRecursive(depth + 1));
		}
		
		return sb.toString();
	}
	
	/**
	 * Determines if this tree contains a cycle
	 * @return true if this this tree contains a cycle, false otherwise
	 */
	public boolean isCyclic() {
		return detectCycle(new LinkedList<>(), new HashSet<>());
	}
	
	private boolean detectCycle(LinkedList<FamilyNode> path, Set<FamilyNode> visited) {
		if (path.contains(this)) {
			return true;
		}
		
		if (visited.contains(this)) {
			return false;
		}
		
		visited.add(this);
		path.add(this);

		for (FamilyNode child : children) {
			if (child.detectCycle(path, visited)) {
				return true;
			}
		}
		
		path.removeLast();
		return false;
	}
	
	/**
	 * Get name of node
	 * @return the name of this node
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FamilyNode) {
			return ((FamilyNode) obj).getName().equals(name);
		} else {
			return false;
		} 
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(FamilyNode n) {
		return name.compareTo(n.name);
	}
	
}
