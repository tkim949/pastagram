package com.tkim949.pastagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.tkim949.pastagram.MainActivity
import com.tkim949.pastagram.Post
import com.tkim949.pastagram.PostAdapter
import com.tkim949.pastagram.R

open class FeedFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where we set up our views  and click listeners

        postsRecyclerView = view.findViewById(R.id.postRecyclerView)

//        Steps to populate RecyclerView
//        1. Create  layout for each row in list (item_post.xml)
//        2. Create data source for each row (this is the Post class)
//        3. Create adapter that will bridge data and row layout (PostAdapter)
//        4. Set adaptor on RecyclerView
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
//        5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }

    //Query for all posts in our server
    open fun queryPosts() {

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)

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

    companion object {
        const val TAG = "FeedFragment"
    }

}