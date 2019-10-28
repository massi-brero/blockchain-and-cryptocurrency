package de.massisoft.merkletree;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MerkleTreeTest {

	@Test
	void testTreeWithEvenNumberOfNodes() throws NoTransactionsException {
		String expected = "4d737644e62ac7f9cf1831be5a838965b03e31a0f8bcac2c3e976fdea6d1cbda";
		List<String> transactions = new ArrayList<>();
		transactions.add("aa");
		transactions.add("bb");
		transactions.add("cc");
		transactions.add("dd");
		
		MerkleTree mTree = new MerkleTree(transactions);
		assertEquals(expected, mTree.getRoot());
	}

	@Test
	void testTreeWithUnevenNumberOfNodes() throws NoTransactionsException {
		String expected = "0cbe39bd48cba9a69ee1e0cf4c002a3a80810e37b63b0b8b30f948cc3d660641";
		List<String> transactions = new ArrayList<>();
		transactions.add("aa");
		transactions.add("bb");
		transactions.add("cc");
		
		
		MerkleTree mTree = new MerkleTree(transactions);
		assertEquals(expected, mTree.getRoot());
	}
	
	@Test
	void testTreeWithNoNodes() {
		String expected = "";
		List<String> transactions = new ArrayList<>();
		
		MerkleTree mTree = new MerkleTree(transactions);
		assertThrows(NoTransactionsException.class, () -> mTree.getRoot());
	}
	
	@Test
	void testTreeWithOneNode() throws NoTransactionsException {
		String expected = "aa";
		List<String> transactions = new ArrayList<>();
		transactions.add("aa");
		
		MerkleTree mTree = new MerkleTree(transactions);
		assertEquals(expected, mTree.getRoot());
	}
	
	@Test
	void testTreeWithOneEmptyNode() throws NoTransactionsException {
		String expected = "961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506";
		List<String> transactions = new ArrayList<>();
		transactions.add("aa");
		transactions.add("");
		
		MerkleTree mTree = new MerkleTree(transactions);
		assertEquals(expected, mTree.getRoot());
	}

}
