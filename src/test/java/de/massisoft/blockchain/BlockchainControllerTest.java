package de.massisoft.blockchain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.Properties;

import de.massisoft.blockchain.Exceptions.NoMinersFoundException;
import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.utils.Utils;
import junit.framework.TestCase;

public class BlockchainControllerTest extends TestCase {
	private String configPath = "src/test/resources/config.properties";
	private BlockchainController bcController;
	private Properties prop;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bcController = new BlockchainController(configPath);
		prop = Utils.loadProperties(configPath);
	}
	
	/**
	 * 
	 * @throws NoMinersFoundException
	 */
	public void testIdsAreIncremented() throws NoMinersFoundException {
		String sender = "user1";
		String receiver = "user2";
		int amount = 1;
		
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(1, getLastBlock().getId());
		
		
		amount = 2;
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(1, getLastBlock().getId());
				
	}
	
	public void testMinersListIsCreated() {
		assertTrue(bcController.getMiners().size() > 0);
	}
	
	public void test2BlocksAreAdded() throws NoMinersFoundException {
		String sender = "user1";
		String receiver = "user2";
		int amount = 1;
		
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(1, bcController.getBlockchain().size());
		
		
		amount = 2;
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(2, bcController.getBlockchain().size());
				
	}
	
	public void testPreviousHashWhenPreviousIsGenesisBlock() throws NoMinersFoundException {
		String sender = "user1";
		String receiver = "user2";
		int amount = 1;
		String genHash = prop.getProperty("GENESiS_PREV_HASH");
		
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(genHash, getLastBlock().getPrevHash());
	}
	
	public void testPreviousHashWhenPreviousIsNotGenesisBlock() throws NoMinersFoundException {
		
		String sender = "user1";
		String receiver = "user2";
		int amount = 1;
		String genHash = prop.getProperty("GENESiS_PREV_HASH");
		
		bcController.addBlock(amount, sender, receiver);
		
		assertEquals(genHash, getLastBlock().getPrevHash());
		
		amount = 2;
		bcController.addBlock(amount, sender, receiver);
		
		Block firstBlock = bcController.getBlockchain().getLedger().get(0);
		
		assertEquals(firstBlock.getHash(), getLastBlock().getPrevHash());
		
	}
	
	
	
	public void testRejectWrongHashedBlockBecauseDifficultyÍsNotMet() {
		
	}
	
	public void testRejectWrongHashedBlockBecauseHashIsNotHex() {
		
	}
	
	public void testNoMinersException() {
		bcController.getMiners().stream().forEach(m -> m.setIsFree(false));
		String sender = "user1";
		String receiver = "user2";
		int amount = 1;
		
		assertThrows(NoMinersFoundException.class, () -> bcController.addBlock(amount, sender, receiver));
	}

	
	private Block getLastBlock() throws MissingResourceException {
		if (bcController.getBlockchain().isEmpty()) {
			throw new MissingResourceException("Blockchain is empty mate...", "", "");
		}
		
		ArrayList<Block> ledger = (ArrayList<Block>) bcController.getBlockchain().getLedger();
		return ledger.get(ledger.size() - 1);
	}
	
}
