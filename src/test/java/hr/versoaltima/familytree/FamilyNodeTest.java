package hr.versoaltima.familytree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FamilyNodeTest {

	private FamilyNode node;

	@BeforeEach
	public void initNode() {
		node = new FamilyNode("A");
	}
	
	@Test
	public void testHasChildAfterAddChild() {
		FamilyNode child = new FamilyNode("B");
		
		node.addChild(child);
		
		assertTrue(node.hasChild(child));
	}
	
	@Test
	public void testHasChildAfterRemoveChild() {
		FamilyNode child = new FamilyNode("B");
		
		node.addChild(child);
		node.removeChild(child);
		
		assertFalse(node.hasChild(child));
	}
	
	@Test
	public void testHasChildWhenMultipleParents() {
		FamilyNode parent1 = new FamilyNode("P1");
		FamilyNode parent2 = new FamilyNode("P2");
		FamilyNode child = new FamilyNode("C");
		
		parent1.addChild(child);
		parent2.addChild(child);
		
		assertTrue(parent1.hasChild(child) && parent2.hasChild(child));
	}
	
	@Test
	public void testThrowsExceptionWhenCyclic() {
		FamilyNode child1 = new FamilyNode("B");
		FamilyNode child2 = new FamilyNode("C");
		
		node.addChild(child1);
		child1.addChild(child2);
		
		assertThrows(CyclicException.class, () -> child2.addChild(node));
	}
	
	@Test
	public void testIsCyclicWhenCyclic() {
		FamilyNode child1 = new FamilyNode("B");
		FamilyNode child2 = new FamilyNode("C");
		
		node.addChild(child1);
		child1.addChild(child2);
		
		try {
			child2.addChild(node);
			fail();
		} catch (CyclicException ex) {
			assertTrue(node.isCyclic());
		}
	}
	
	@Test
	public void testFindCorrectChild() {
		FamilyNode child1 = new FamilyNode("B");
		FamilyNode child2 = new FamilyNode("C");
		FamilyNode child3 = new FamilyNode("D");
		FamilyNode child4 = new FamilyNode("E");
		
		node.addChild(child1);
		node.addChild(child2);
		child2.addChild(child4);
		node.addChild(child3);
		
		Optional<FamilyNode> found = node.find("E");
		
		assertEquals(child4, found.get());
	}
	
	@Test
	public void testEmptyOptionalWhenFindingNonExistentChild() {
		FamilyNode child1 = new FamilyNode("B");
		node.addChild(child1);

		Optional<FamilyNode> found = node.find("Z");
		
		assertTrue(found.isEmpty());
	}
	
	@Test
	public void testDisplayChildren() {
		FamilyNode child1 = new FamilyNode("B");
		FamilyNode child2 = new FamilyNode("C");
		FamilyNode child3 = new FamilyNode("D");
		FamilyNode child4 = new FamilyNode("E");
		
		node.addChild(child1);
		child1.addChild(child2);
		child1.addChild(child3);
		child2.addChild(child4);
		
		StringBuilder sb = new StringBuilder();
		sb.append("B\n");
		sb.append("\tC\n");
		sb.append("\t\tE\n");
		sb.append("\tD\n");
		
		String expected = sb.toString();
		assertEquals(expected, node.displayChildren());
	}
	
	@Test
	public void testDisplayChildrenWhenMultipleParents() {
		FamilyNode parent1 = new FamilyNode("P1");
		FamilyNode parent2 = new FamilyNode("P2");
		FamilyNode child = new FamilyNode("C");
		
		node.addChild(parent1);
		node.addChild(parent2);
		
		parent1.addChild(child);
		parent2.addChild(child);
		
		StringBuilder sb = new StringBuilder();
		sb.append("P1\n");
		sb.append("\tC\n");
		sb.append("P2\n");
		sb.append("\tC\n");
		
		String expected = sb.toString();
		assertEquals(expected, node.displayChildren());
	}
	
	@Test
	public void testToStringReturnsName() {
		assertEquals(node.getName(), node.toString());
	}
	
	@Test
	public void testEqualsWhenSameName() {
		FamilyNode clone = new FamilyNode("A");
		
		assertEquals(node, clone);
	}
	
	@Test
	public void testOrderByName() {
		FamilyNode other = new FamilyNode("B");
		
		assertTrue(node.compareTo(other) < 0);
	}
}
