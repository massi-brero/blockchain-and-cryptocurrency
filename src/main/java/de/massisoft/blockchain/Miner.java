package de.massisoft.blockchain;

import java.io.IOException;
import java.util.Properties;

import de.massisoft.blockchain.entities.Block;
import de.massisoft.blockchain.entities.Blockchain;
import de.massisoft.blockchain.utils.Utils;

public class Miner {
	private double reward;
	private final static String CONFIG_PATH = "src/main/resources/config.properties";
	private boolean isFree;
	private Properties prop;
	/**
	 * 
	 * @throws IOException
	 */
	public Miner() throws IOException {
		this(CONFIG_PATH);
	}
	
	/**
	 * 
	 * @param configPath
	 * @throws IOException
	 */
	public Miner(String configPath) throws IOException {
		isFree = true;
		prop = Utils.loadProperties(configPath);
	}
	
	/**
	 * 
	 * @param block
	 * @param blockchain
	 */
	public Block mine(Block block, Blockchain blockchain) {
		isFree = false;
		
		while(notGoldenHash(block)) {
			block.generateHash();
			block.incrementNonce();
		}
		
		System.out.println(block + " has just been mined...");
		System.out.println("Hash is: " +  block.getHash());
		
		blockchain.addBlock(block);
		reward += Integer.valueOf(prop.getProperty("MINER_REWARD"));
		
		isFree = true;
		
		return block;
	}
	
	/**
	 * 
	 * @param block
	 * @param difficulty
	 * @return
	 */
	private boolean notGoldenHash(Block block) {
		int difficulty = Integer.valueOf(prop.getProperty("DIFFICULTY"));
		String leadingZeros = new String(new char[difficulty]).replace('\0', '0');
		return !block.getHash().substring(0, difficulty).equals(leadingZeros);
	}

	/**
	 * 
	 * @return
	 */
	public double getReward() {
		return reward;
	}


	public boolean isFree() {
		return isFree;
	}
	
	public void setIsFree(boolean val) {
		isFree = val;
	}
	
}
