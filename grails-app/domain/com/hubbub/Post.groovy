package com.hubbub

class Post {

	String content
	Date dateCreated

    static constraints = {
		content(blank: false)
    }
	
	static belongsTo = [user: User]
}
