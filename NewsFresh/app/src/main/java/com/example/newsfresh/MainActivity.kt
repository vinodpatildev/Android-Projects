package com.example.newsfresh
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
class MainActivity : AppCompatActivity(),NewsItemClicked {
    private lateinit var mAdapter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rview = findViewById<RecyclerView>(R.id.myRecycleview)
        rview.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        rview.adapter = mAdapter
    }
        private fun fetchData(){
            val url = "https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                {response->
                    val newsJsonArray = response.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                    for(i in 0 until newsJsonArray.length()){
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = News(
                            newsJsonObject.getString("title"),
                            newsJsonObject.getString("author"),
                            newsJsonObject.getString("url"),
                            newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }
                    mAdapter.updateNews(newsArray)
                },
                {response->
                    Toast.makeText(this,"Failure : ${response.toString()}",Toast.LENGTH_LONG).show()
                }
            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }
        override fun onItemClicked(item: News){
            val url = item.url.toString()
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder();
            val customTabsIntent: CustomTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
}

