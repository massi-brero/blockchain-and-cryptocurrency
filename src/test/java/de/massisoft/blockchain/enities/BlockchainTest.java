package de.massisoft.blockchain.enities;

import java.util.ArrayList;
import java.util.List;

import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.entities.Blockchain;
import de.massisoft.blockchain.transactions.SimpleTransaction;
import de.massisoft.blockchain.transactions.Transaction;
import de.massisoft.blockchain.utils.IHasher;
import junit.framework.TestCase;

public class BlockchainTest extends TestCase {

	public void testInitBlockChain() {
		Blockchain bc = new Blockchain();
		
		assertTrue(bc instanceof Blockchain);
	}
	
	public void testSizeOfBlockchain() {
		int expected = 0;
		Blockchain bc = new Blockchain();
		
		assertEquals(expected, bc.size());
		Transaction tr = new SimpleTransaction();
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(tr);
	
		tr.setReceiver("receiver");
		tr.setSender("sender");
		tr.setAmount(1);
		tr.setComment("comment");
		
		expected = 1;
		Block b = new Block(0, transactions, "", new IHasher() {
			
			public String generateHash(String data) {
				return null;
			}
		});
		bc.addBlock(b);
		
		assertEquals(expected, bc.size());
	}
}
