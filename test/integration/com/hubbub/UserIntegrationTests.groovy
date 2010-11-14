package com.hubbub

import grails.test.*

class UserIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	

    void testFirstSaveEver() {
		def user = new User(userId:'joejoejoe', password: 'secretsecret', homepage: 'http://www.amazon.com')
		
		assertNotNull user.save()
		assertNotNull user.id
		
		def foundUser = User.get(user.id)
		
		assertEquals 'joejoejoe', foundUser.userId
    }

	void testSaveAndUpdate() {
		def user = new User(userId:'joejoejoe', password: 'secretsecret', homepage: 'http://www.amazon.com')
		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.password = "sesamesesame"
		
		foundUser.save()
		
		def editedUser = User.get(user.id)
		assertEquals "sesamesesame", editedUser.password				
	}

	

	void testSaveThenDelete() {
		def user = new User(userId:'joejoejoe', password: 'secretsecret', homepage: 'http://www.amazon.com')		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.delete()
				
		assertFalse User.exists(foundUser.id)
	}

	
	void testEvilSave() {
		def user = new User(userId: "myself", password: "small", homepage: "notalink")
		
		assertFalse user.validate()
		assertTrue user.hasErrors()
		
		def errors = user.errors
		
		assertEquals "size.toosmall", errors.getFieldError("password").code
		assertEquals "small", errors.getFieldError("password").rejectedValue
		
		assertEquals "url.invalid", errors.getFieldError("homepage").code
		assertEquals "notalink", errors.getFieldError("homepage").rejectedValue
		
		assertNull errors.getFieldError("userId")
	}
	
	void testEvilSaveCorrected() {
		def user = new User(userId: "myself", password: "small", homepage: "notalink")
		
		assertFalse user.validate()
		assertTrue user.hasErrors()
		assertNull user.save()
		
		user.password = "correctleng"
		user.homepage = "http://www.ebay.com"
		
		assertTrue user.validate()
		assertFalse user.hasErrors()
		assertNotNull user.save()
	}
}
