package de.massisoft.blockchain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.massisoft.blockchain.Exceptions.MissingHasherError;
import de.massisoft.blockchain.transactions.Transaction;
import de.massisoft.blockchain.utils.IHasher;

public class Block {
	private int id;
	private int nonce;
	private long timeStamp;
	private String hash;
	private String prevHash;
	private List<Transaction> transactions;
	private IHasher hasher;
	
	public Block(int id, List<Transaction> trans, String prevHash, IHasher h) {
		this.transactions = new ArrayList<>();
		setId(id);
		setTransactions(trans);
		setPrevHash(prevHash);
		setTimeStamp(new Date().getTime());
		setHasher(h);
		
		generateHash();
	}
	
	public void generateHash() {
		
		if (getHasher() == null) {
			throw new MissingHasherError();
		}
		
		String dataToHash = Integer.toString(getId()) + transactions + prevHash + Long.toString(timeStamp) + Integer.toString(nonce);
		String hashValue = getHasher().generateHash(dataToHash);
		setHash(hashValue);
	}
	
	public void incrementNonce() {
		int n = getNonce();
		setNonce(++n);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNonce() {
		return nonce;
	}
	
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getPrevHash() {
		return prevHash;
	}
	
	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<Transaction> newTransactions) {
		this.transactions.addAll(newTransactions);
	}
	
	public void setHasher(IHasher h) {
		hasher = h;
	}
	
	public IHasher getHasher() {
		return hasher;
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", nonce=" + nonce + ", timeStamp=" + timeStamp + ", hash=" + hash + ", prevHash="
				+ prevHash + ", transactions=" + transactions + ", hasher=" + hasher + "]";
	}
	
}
