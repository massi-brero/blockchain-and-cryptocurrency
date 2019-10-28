package de.massisoft.blockchain.utils;

import de.massisoft.blockchain.utils.SHA256Helper;
import junit.framework.TestCase;

public class SHA256HelperTest extends TestCase {

	public void testGenerateHashForNotEmptyValue() {
		String expected = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
		String data = "1";
		
		String actual = new SHA256Helper().generateHash(data);
		
		assertEquals(expected, actual);
		
	}
	
	public void testGenerateHashForEmptyValue() {
		String expected = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
		String data = "";
		
		String actual = new SHA256Helper().generateHash(data);
		
		assertEquals(expected, actual);
		
	}

}
