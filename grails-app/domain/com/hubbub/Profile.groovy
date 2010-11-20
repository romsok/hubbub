package com.hubbub

class Profile {
	
	static belongsTo = User
	
	byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress

    static constraints = {
		fullName(nullable: true)
		bio(nullable: true, maxSize: 1000)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		photo(nullable: true)
		timezone(nullable: true)
		country(nullable: true)
		jabberAddress(email: true, nullable: true)
    }
	
	String toString() {
		return "Profile for ${fullName} (${id})"
	}
}
