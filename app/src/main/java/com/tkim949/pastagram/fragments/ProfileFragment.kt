package com.tkim949.pastagram.fragments

import android.util.Log
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.tkim949.pastagram.Post

class ProfileFragment: FeedFragment() {

    override fun queryPosts() {

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        //Only return posts from currently signed in user
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())

        query.addDescendingOrder("createdAt")
        // Find all post object!
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                //TODO("Not yet implemented")
                if (e != null) {
                    // something has went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null ) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + " , username: " +
                                    post.getUser()?.username
                            )
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

    }
}