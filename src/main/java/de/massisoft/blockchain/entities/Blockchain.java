package de.massisoft.blockchain.entities;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private List<Block> blockchain;
	
	public Blockchain() {
		init();
	}

	private void init() {
		blockchain = new ArrayList<Block>();
	}
	
	public void addBlock(Block b) {
		blockchain.add(b);
	}

	public List<Block> getLedger() {
		return blockchain;
	}
	
	public int size() {
		return blockchain.size();
	}

	@Override
	public String toString() {
		return "Blockchain [blockchain=" + blockchain + "]";
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
}
