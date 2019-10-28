package de.massisoft.blockchain.enities;

import java.util.ArrayList;
import java.util.List;

import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.transactions.SimpleTransaction;
import de.massisoft.blockchain.transactions.Transaction;
import de.massisoft.blockchain.utils.IHasher;
import de.massisoft.blockchain.utils.SHA256Helper;
import junit.framework.TestCase;

public class BlockTest extends TestCase {
	
	public void testIncrementNonce() {
		int expected = 0;
		Block b = new Block(0, getTransactions(), "", new SHA256Helper());
		
		int actual = b.getNonce();
		assertEquals(expected, actual);
		
		b.incrementNonce();
		expected = 1;
 		actual = b.getNonce();
	
		assertEquals(expected, actual);
	}

	private List<Transaction> getTransactions() {
		Transaction tr = new SimpleTransaction();
		tr.setReceiver("receiver");
		tr.setSender("sender");
		tr.setAmount(1);
		tr.setComment("comment");
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(tr);
		
		return transactions;
	}
	
	public void testgenerateHash() {
		final String expected = "1";
		
		IHasher h = new IHasher() {
			
			public String generateHash(String data) {
				return expected;
			}
		};
		
		Block b = new Block(0, getTransactions(), "", h);
		b.generateHash();
		
		assertEquals(expected, b.getHash());
	}
	
	public void testFeatureCorrectBlockHashing() {
		final String expected = "1";
		
		IHasher h = new IHasher() {
			
			public String generateHash(String data) {
				return expected;
			}
		};
		
		Block b = new Block(0, getTransactions(), "", h);
		
		assertEquals(expected, b.getHash());
	}
	
	public void testaddTwoTransactionsToBlock() {
		Block b = new Block(0, getTransactions(), "", new SHA256Helper());
		
		Transaction tr = new SimpleTransaction();
		tr.setReceiver("receiver");
		tr.setSender("sender");
		tr.setAmount(1);
		tr.setComment("comment");
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(tr);
		b.setTransactions(transactions);
		
		assertEquals(2, b.getTransactions().size());
	}
	
	
}
