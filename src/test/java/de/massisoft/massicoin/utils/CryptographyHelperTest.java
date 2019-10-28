package de.massisoft.massicoin.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptographyHelperTest {
	CryptographyHelper object;
	
	@BeforeEach
	public void SetUp(){		
		object = new CryptographyHelper();
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	


	@Test
	void testEllipticCurveCrypto() {

		KeyPair keypair = object.ellipticCurveCrypto();
		
		assertThat(keypair.getPublic(), instanceOf(PublicKey.class));
		assertThat(keypair.getPrivate(), instanceOf(PrivateKey.class));
		assertNotNull(keypair.getPublic());
		assertNotNull(keypair.getPrivate());
	}

}
