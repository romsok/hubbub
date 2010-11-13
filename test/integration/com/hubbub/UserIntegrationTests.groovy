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
		def user = new User(userId:'joe', password: 'secret', homepage: 'www.amazon.com')
		
		assertNotNull user.save()
		assertNotNull user.id
		
		def foundUser = User.get(user.id)
		
		assertEquals 'joe', foundUser.userId
    }
	
	void testSaveAndUpdate() {
		def user = new User(userId:'joe', password: 'secret', homepage: 'www.amazon.com')
		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.password = "sesame"
		
		foundUser.save()
		
		def editedUser = User.get(user.id)
		assertEquals "sesame", editedUser.password				
	}
	
	void testSaveThenDelete() {
		def user = new User(userId:'joe', password: 'secret', homepage: 'www.amazon.com')		
		assertNotNull user.save()
		
		def foundUser = User.get(user.id)
		foundUser.delete()
				
		assertFalse User.exists(foundUser.id)
	}
}
