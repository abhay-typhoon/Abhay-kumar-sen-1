package com.UserManagment.verificationCode;

import java.util.UUID;

public class VerificationCodeGenerator {

	  public static String generateVerificationCode() {
	        return UUID.randomUUID().toString();
	    }

}
