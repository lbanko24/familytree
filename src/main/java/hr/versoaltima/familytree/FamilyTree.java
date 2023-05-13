package hr.versoaltima.familytree;

import java.util.Optional;

/**
 * Family tree containing hierarchically organized members
 * 
 * @author Leon Banko
 *
 */
public class FamilyTree {
	
	private FamilyNode root;
	
	/**
	 * Creates a new family tree
	 */
	public FamilyTree() {
		root = new FamilyNode();
	}
	
	/**
	 * Searches for a node with the specified name in this tree
	 * @param name target node name
	 * @return Optional containing the node with the specified name or empty Optional if not found
	 */
	public Optional<FamilyNode> find(String name) {
		return root.find(name);
	}
	
	/**
	 * Creates parent and child with specified names and adds them to this tree.
	 * If the specified child or parent already exist then they are not created.
	 * @param childName the name of the child node
	 * @param parentName the name of the parent node
	 */
	public void insertParentAndChild(String childName, String parentName) {
		Optional<FamilyNode> foundParent = find(parentName);
		Optional<FamilyNode> foundChild = find(childName);
		
		FamilyNode parent;
		FamilyNode child;
		
		// parent not found in tree
		if (foundParent.isEmpty()) {
			parent = new FamilyNode(parentName);
			root.addChild(parent);
		} else {
			parent = foundParent.get();
		}
		
		// child not found in tree
		if (foundChild.isEmpty()) {
			child = new FamilyNode(childName);
		} else {
			child = foundChild.get();
		}
		
		parent.addChild(child);
		
		// downgrade top-level node
		if (root.hasChild(child)) {
			root.removeChild(child);
		}
	}
	
	/**
	 * Determines if this tree contains a cycle
	 * @return true if this this tree contains a cycle, false otherwise
	 */
	public boolean isCyclic() {
		return root.isCyclic();
	}
	
	/**
	 * Displays nodes in this tree hierarchically, using tabs as indentations to show child-parent relationships
	 * @return A string containing a hierarchical representation of this tree
	 */
	public String display() {
		return root.displayChildren();
	}
}
