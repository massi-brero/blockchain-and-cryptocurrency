package de.massisoft.blockchain.transactions;

public class SimpleTransaction extends Transaction {

	@Override
	public String getTransaction() {
		return "sender=" + getSender() + "; receiver=" + getReceiver() + "; amount="
				+ getAmount() + ", comment=" + getComment() + "; hashCode="
				+ hashCode();
	}

}
