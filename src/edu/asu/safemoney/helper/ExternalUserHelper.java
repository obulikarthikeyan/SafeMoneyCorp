package edu.asu.safemoney.helper;

import java.util.UUID;

public class ExternalUserHelper {

	public static long generateRandomNumber()
	{
		long random = UUID.randomUUID().getLeastSignificantBits();
		random = System.currentTimeMillis() + random;
		random = (random / System.currentTimeMillis());
		return Math.abs(random);
	}
	
	public static long generateAccountNumber(int memberId)
	{
		long random = UUID.randomUUID().getLeastSignificantBits();
		random = System.currentTimeMillis() + random;
		random = (random / (memberId * 100000) );
		return Math.abs(random);
	}
}
