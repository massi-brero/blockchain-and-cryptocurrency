package de.massisoft.blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.entities.Blockchain;
import de.massisoft.blockchain.transactions.SimpleTransaction;
import de.massisoft.blockchain.transactions.Transaction;
import de.massisoft.blockchain.utils.SHA256Helper;
import de.massisoft.blockchain.utils.Utils;
import junit.framework.TestCase;

public class MinerTest extends TestCase {
	private String configPath = "src/test/resources/config.properties";
	Properties prop;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		prop = Utils.loadProperties(configPath);
	}
	
	public void testFeatureNotGoldenHash() throws IOException {
		char expected = '0';
		Miner miner = new Miner(configPath);
		String prevHash = prop.getProperty("GENESiS_PREV_HASH");
		
		Transaction tr = new SimpleTransaction();
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(tr);
		
		tr.setReceiver("receiver");
		tr.setSender("sender");
		tr.setAmount(1);
		tr.setComment("comment");
		
		Block block = new Block(
				0, 
				transactions, 
				prevHash, 
				new SHA256Helper());
		
		Block hashedBlock = miner.mine(block, new Blockchain());
		String hash = hashedBlock.getHash();
		
		assertEquals(expected, hash.charAt(0));
	}
}
