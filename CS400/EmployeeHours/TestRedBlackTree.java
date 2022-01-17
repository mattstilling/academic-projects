// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the various insert cases for the RBT implemented in RedBlackTree.java
 * 
 * @author Matt Stilling
 */
public class TestRedBlackTree {

    /**
     * Test 1:
     * 
     * Tests the case where the parent's sibling (S) is black and on the opposite side (Case 1).
     * Testing where the new node (C) is in grandparent's left subtree, so C is a left child and 
     * S is a right child.
     */
    @Test
    public void testSiblingBlackOppositeSide() {
        // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {3, 2};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(1); // 1 should be inserted such that Case 1 code is executed
        
        // Test node positions and colors after inserting 1
        assertTrue(tree.root.data == 2 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 1 && !tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 3 && !tree.root.rightChild.isBlack);
    }
    
    /**
     * Test 2:
     * 
     * Tests the case where the parent's sibling (S) is black and on the same side (Case 2).
     * Testing where the new node (C) is in grandparent's left subtree, so C is a right child and 
     * S is a right child.
     */
    @Test
    public void testSiblingBlackSameSide() {
     // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {10, 6, 4, 2, 12, 8};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(3); // 3 should be inserted such that Case 2 code is executed
        
        // Test node positions and colors after inserting 3
        assertTrue(tree.root.data == 6 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 3 && tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 10 && tree.root.rightChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.data == 2 
                && !tree.root.leftChild.leftChild.isBlack);
        assertTrue(tree.root.leftChild.rightChild.data == 4 
                && !tree.root.leftChild.rightChild.isBlack);
        assertTrue(tree.root.rightChild.leftChild.data == 8 
                && !tree.root.rightChild.leftChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.data == 12 
                && !tree.root.rightChild.rightChild.isBlack);
    }
    
    /**
     * Test 3:
     * 
     * Tests the case where the parent's sibling is red (Case 3). Testing where the new node is in 
     * grandparent's left subtree.
     */
    @Test
    public void testSiblingRed() {
        // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {4, 3, 2};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(1); // 1 should be inserted such that Case 3 code is executed
        
        // Test node positions and colors after inserting 1
        assertTrue(tree.root.data == 3 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 2 && tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 4 && tree.root.rightChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.data == 1 
                && !tree.root.leftChild.leftChild.isBlack);
    }
    
    /**
     * Test 4: (mirror of test 1)
     * 
     * Tests the case where the parent's sibling (S) is black and on the opposite side (Case 1).
     * Testing where the new node (C) is in grandparent's right subtree, so C is a right child and 
     * S is a left child.
     */
    @Test
    public void testSiblingBlackOppositeSideMirror() {
        // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {7, 14};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(18); // 18 should be inserted such that Case 1 code is executed
        
        // Test node positions and colors after inserting 18
        assertTrue(tree.root.data == 14 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 7 && !tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 18 && !tree.root.rightChild.isBlack);
    }
    
    /**
     * Test 5: (mirror of test 2)
     * 
     * Tests the case where the parent's sibling (S) is black and on the same side (Case 2).
     * Testing where the new node (C) is in grandparent's right subtree, so C is a left child and 
     * S is a left child.
     */
    @Test
    public void testSiblingBlackSameSideMirror() {
        // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {7, 14, 18, 23, 1, 11, 20, 29};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(25); // 25 should be inserted such that Case 2 code is executed
        
        // Test node positions and colors after inserting 25
        assertTrue(tree.root.data == 14 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 7 && tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 20 && !tree.root.rightChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.data == 1 
                && !tree.root.leftChild.leftChild.isBlack);
        assertTrue(tree.root.leftChild.rightChild.data == 11 
                && !tree.root.leftChild.rightChild.isBlack);
        assertTrue(tree.root.rightChild.leftChild.data == 18 
                && tree.root.rightChild.leftChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.data == 25 
                && tree.root.rightChild.rightChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.leftChild.data == 23 
                && !tree.root.rightChild.rightChild.leftChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.rightChild.data == 29 
                && !tree.root.rightChild.rightChild.rightChild.isBlack);
    }
    
    /**
     * Test 6: (mirror of test 3)
     * 
     * Tests the case where the parent's sibling is red (Case 3). Testing where the new node is in 
     * grandparent's right subtree. The specific case used in this test method also tests the 
     * recursive calls used when Case 3 causes red property violations further up the tree.
     */
    @Test
    public void testSiblingRedMirror() {
        // Create tree with desired structure
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Integer[] vals = {7, 14, 18, 23, 1, 11, 20, 29, 25};
        for (Integer val: vals) {
            tree.insert(val);
        }
        tree.insert(27); // 27 should be inserted such that Case 3 code is executed
        
        // Test node positions and colors after inserting 27
        assertTrue(tree.root.data == 20 && tree.root.isBlack);
        assertTrue(tree.root.leftChild.data == 14 && !tree.root.leftChild.isBlack);
        assertTrue(tree.root.rightChild.data == 25 && !tree.root.rightChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.data == 7 
                && tree.root.leftChild.leftChild.isBlack);
        assertTrue(tree.root.leftChild.rightChild.data == 18 
                && tree.root.leftChild.rightChild.isBlack);
        assertTrue(tree.root.rightChild.leftChild.data == 23 
                && tree.root.rightChild.leftChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.data == 29 
                && tree.root.rightChild.rightChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.leftChild.data == 1 
                && !tree.root.leftChild.leftChild.leftChild.isBlack);
        assertTrue(tree.root.leftChild.leftChild.rightChild.data == 11 
                && !tree.root.leftChild.leftChild.rightChild.isBlack);
        assertTrue(tree.root.rightChild.rightChild.leftChild.data == 27 
                && !tree.root.rightChild.rightChild.leftChild.isBlack);
    }
    
}
