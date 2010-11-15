package com.hubbub

class Tag {

	String name
	User user
	
	hasMany = [posts: Post]
	belongsTo = [User, Post]
	
    static constraints = {
		name(blank: false)
    }
}
