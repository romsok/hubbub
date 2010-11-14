package com.hubbub

class User {
	
	String userId
	String password
	String homepage
	Date dateCreated
	
    static constraints = {
		userId(size: 3..20, unique: true)
		password(size: 8..12,
			validator: {pwd, usr ->
				return pwd != usr.userId
			}
		)
		homepage(url: true, nullable: true)
    }
}
