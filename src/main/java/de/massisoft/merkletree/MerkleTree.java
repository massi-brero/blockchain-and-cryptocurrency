package de.massisoft.merkletree;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a merkle tree... so simple that in fact it's actually a list.
 * @author massi.brero
 *
 */
public class MerkleTree {
	
	private List<String> transactions;

	/**
	 * 
	 * @param transactions
	 */
	public MerkleTree(List<String> transactions) {
		this.transactions = transactions;
		
	}
	
	/**
	 * 
	 * @return
	 * @throws NoTransactionsException 
	 */
	public String getRoot() throws NoTransactionsException {
		if(transactions.isEmpty()) {
			throw new NoTransactionsException();
		}
		return construct(transactions).stream().findFirst().get();
	}

	/**
	 * recursive method.
	 * 
	 * @param transactions2
	 * @return
	 */
	private List<String> construct(List<String> transactions) {
		if (transactions.size() == 1) {
			return transactions;
		}
		int size = transactions.size();
		List<String> updatedList = new ArrayList<>();
		
		for (int i = 0; i < size - 1; i += 2) {
			updatedList.add(mergeHash(transactions.get(i), transactions.get(i+1)));
		}
		
		if (size % 2 == 1 )  {
			updatedList.add(mergeHash(transactions.get(size - 1), transactions.get(size-1)));
		}
		
		return construct(updatedList);
	}

	private String mergeHash(String hash1, String hash2) {
		String mergedHash = hash1.concat(hash2);
		return new SHA256Helper().generateHash(mergedHash);
	}
	
}
