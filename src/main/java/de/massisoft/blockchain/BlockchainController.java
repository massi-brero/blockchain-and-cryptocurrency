package de.massisoft.blockchain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

import de.massisoft.blockchain.Exceptions.NoMinersFoundException;
import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.entities.Blockchain;
import de.massisoft.blockchain.transactions.SimpleTransaction;
import de.massisoft.blockchain.transactions.Transaction;
import de.massisoft.blockchain.utils.IHasher;
import de.massisoft.blockchain.utils.SHA256Helper;
import de.massisoft.blockchain.utils.Utils;

public class BlockchainController {

	private Blockchain blockchain;
	private int id;
	private List<Miner> miners;
	private final static String CONFIG_PATH = "src/main/resources/config.properties";
	private static Properties prop;
	
	/*
	 * 
	 * @throws IOException
	 */
	public BlockchainController() throws IOException {
		this(CONFIG_PATH);
	}
	
	/**
	 * 
	 * @param configPath
	 * @throws IOException
	 */
	public BlockchainController(String configPath) throws IOException {
		prop = Utils.loadProperties(configPath);
		blockchain = new Blockchain();
		
		int numberOfMiners = Integer.parseInt(prop.getProperty("NUMBER_OF_MINERS"));
		miners = new ArrayList<Miner>();
		for (int i = 0; i < numberOfMiners; i++) {
			miners.add(new Miner());
		}
		
	}		
	
	/**
	 * Adds block to the chain using a free miner.
	 * @ToDo implement majority vote.
	 * 
	 * @param amount
	 * @param sender
	 * @param receiver
	 * @throws NoMinersFoundException 
	 */
	public void addBlock(int amount, String sender, String receiver) throws NoMinersFoundException {
		String prevHash = "";
		Transaction trans = populateTransaction(amount, sender, receiver);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(trans);
		
		if (blockchain.isEmpty()) {
			prevHash = prop.getProperty("GENESiS_PREV_HASH");
		} else {
			prevHash = blockchain.getLedger().get(blockchain.size() - 1).getHash();
		}
		
		
		IHasher hasher = new SHA256Helper();
		Block block = new Block(getNewId(), transactions, prevHash, hasher);		
		
		try {
			Optional<Miner> result = miners.stream().filter(m -> m.isFree()).findFirst();
			Miner miner = result.get();
			miner.mine(block, blockchain);
		} catch (NoSuchElementException e) {
			throw new NoMinersFoundException();
		}
		
		if (!hasCorrectHash(block)) {
			reject(block);
			addBlock(amount, sender, receiver);
		} else {
			success(block);
		}
		
		
	}
	
	public Blockchain getBlockchain() {
		return blockchain;
	}
	
	public List<Miner>getMiners() {
		return miners;
	}
	
	private int getNewId() {
		return blockchain.isEmpty() ? 1 : ++id;
	}

	private boolean hasCorrectHash(Block block) {
		String hash = block.getHash();
		int difficulty = Integer.valueOf(prop.getProperty("DIFFICULTY"));

		boolean isHex = hash.matches("^[a-fA-F0-9]{64}$");
		
		String leadingZeros = new String(new char[difficulty]).replace('\0', '0');
		boolean hasDifficulty =  hash.substring(0, difficulty).equals(leadingZeros);
		
		return isHex && hasDifficulty;
	}

	private void success(Block b) {
		System.out.println("Generated hash for block was ok.");
		System.out.println(b);
		
	}

	private void reject(Block b) {
		System.out.println("Generated hash for block was faulty.");
		System.out.println(b);
	}

	private Transaction populateTransaction(int amount, String sender, String receiver) {
		Transaction trans = new SimpleTransaction();
		trans.setAmount(amount);
		trans.setSender(sender);
		trans.setReceiver(receiver);
		return trans;
	}
	
}
