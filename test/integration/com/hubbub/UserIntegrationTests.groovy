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
		def user = new User(userId:'joejoejoe', password: 'secretsecret')
		
		assertNotNull user.save()
		assertNotNull user.id
		
		def foundUser = User.get(user.id)
		
		assertEquals 'joejoejoe', foundUser.userId
    }

	void testSaveAndUpdate() {
		def user = new User(userId:'joejoejoe', password: 'secretsecret')
		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.password = "sesamesesame"
		
		foundUser.save()
		
		def editedUser = User.get(user.id)
		assertEquals "sesamesesame", editedUser.password				
	}

	

	void testSaveThenDelete() {
		def user = new User(userId:'joejoejoe', password: 'secretsecret')		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.delete()
				
		assertFalse User.exists(foundUser.id)
	}

	
	void testEvilSave() {
		def user = new User(userId: "myself", password: "small")
		
		assertFalse user.validate()
		assertTrue user.hasErrors()
		
		def errors = user.errors
		
		assertEquals "size.toosmall", errors.getFieldError("password").code
		assertEquals "small", errors.getFieldError("password").rejectedValue
		
		assertNull errors.getFieldError("userId")
	}
	
	void testEvilSaveCorrected() {
		def user = new User(userId: "myself", password: "small")
		
		assertFalse user.validate()
		assertTrue user.hasErrors()
		assertNull user.save()
		
		user.password = "correctleng"
		
		assertTrue user.validate()
		assertFalse user.hasErrors()
		assertNotNull user.save()
	}
}
