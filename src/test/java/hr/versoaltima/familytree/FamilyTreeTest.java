package hr.versoaltima.familytree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FamilyTreeTest {

	private FamilyTree tree;
	
	@BeforeEach
	public void initTree() {
		tree = new FamilyTree();
	}
	
	@Test
	public void testFindNode() {
		tree.insertParentAndChild("Adam", "Ivan");
		tree.insertParentAndChild("Marko", "Stjepan");
		tree.insertParentAndChild("Stjepan", "Adam");
		
		Optional<FamilyNode> found = tree.find("Marko");
		
		assertEquals("Marko", found.get().getName());
	}

	@Test
	public void testThrowsExceptionWhenCyclic() {
		tree.insertParentAndChild("Adam", "Ivan");
		tree.insertParentAndChild("Stjepan", "Adam");
		
		assertThrows(CyclicException.class, () -> tree.insertParentAndChild("Ivan", "Stjepan"));
	}
	
	@Test
	public void testIsCyclicWhenCyclic() {
		tree.insertParentAndChild("Adam", "Ivan");
		tree.insertParentAndChild("Stjepan", "Adam");
		
		try {
			tree.insertParentAndChild("Ivan", "Stjepan");
			fail();
		} catch (CyclicException ex) {
			assertTrue(tree.isCyclic());
		}
	}
	
	@Test
	public void testHasChildWhenMultipleParents() {
		tree.insertParentAndChild("C", "P1");
		tree.insertParentAndChild("C", "P2");
		
		FamilyNode child = tree.find("C").get();
		FamilyNode parent1 = tree.find("P1").get();
		FamilyNode parent2 = tree.find("P2").get();
		
		assertTrue(parent1.hasChild(child) && parent2.hasChild(child));
	}
	
	@Test
	public void testDisplayWhenMultipleParents() {
		tree.insertParentAndChild("C", "P1");
		tree.insertParentAndChild("C", "P2");
		
		StringBuilder sb = new StringBuilder();
		sb.append("P1\n");
		sb.append("\tC\n");
		sb.append("P2\n");
		sb.append("\tC\n");
		
		String expected = sb.toString();
		assertEquals(expected, tree.display());
	}
	
	@Test
	public void testAttachNewChildToExistingParent() {
		tree.insertParentAndChild("A", "P");
		tree.insertParentAndChild("B", "P");
		
		StringBuilder sb = new StringBuilder();
		sb.append("P\n");
		sb.append("\tA\n");
		sb.append("\tB\n");
		
		String expected = sb.toString();
		assertEquals(expected, tree.display());
	}
	
	@Test
	public void testAttachExistingChildToExistingParent() {
		tree.insertParentAndChild("B", "A");
		tree.insertParentAndChild("C", "A");
		tree.insertParentAndChild("B", "C");

		StringBuilder sb = new StringBuilder();
		sb.append("A\n");
		sb.append("\tB\n");
		sb.append("\tC\n");
		sb.append("\t\tB\n");
		
		String expected = sb.toString();
		assertEquals(expected, tree.display());
	}
	
	@Test
	public void testAttachExistingChildToNewParent() {
		tree.insertParentAndChild("B", "A");
		tree.insertParentAndChild("B", "C");

		StringBuilder sb = new StringBuilder();
		sb.append("A\n");
		sb.append("\tB\n");
		sb.append("C\n");
		sb.append("\tB\n");
		
		String expected = sb.toString();
		assertEquals(expected, tree.display());
	}
	
	@Test
	public void testDisplayTree() {
		tree.insertParentAndChild("Adam", "Ivan");
		tree.insertParentAndChild("Marko", "Stjepan");
		tree.insertParentAndChild("Stjepan", "Adam");
		tree.insertParentAndChild("Robert", "Stjepan");
		tree.insertParentAndChild("Fran", "Ivan");
		tree.insertParentAndChild("Leopold", "Luka");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Ivan\n");
		sb.append("\tAdam\n");
		sb.append("\t\tStjepan\n");
		sb.append("\t\t\tMarko\n");
		sb.append("\t\t\tRobert\n");
		sb.append("\tFran\n");
		
		sb.append("Luka\n");
		sb.append("\tLeopold\n");

		String expected = sb.toString();
		assertEquals(expected, tree.display());
	}
}
