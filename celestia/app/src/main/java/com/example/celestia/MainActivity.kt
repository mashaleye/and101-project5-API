package com.example.celestia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.*


var picOfDayImageURL = ""
var copyrightText = ""
var titleText = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.randomImageButton)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val captionTekstView = findViewById<TextView>(R.id.captionTextView)
        val titleView = findViewById<TextView>(R.id.titleTextView)



        button.setOnClickListener {
            getImageURL(imageView)
            getImageCaptionURL(captionTekstView)
            getImageTitleURL(titleView)

        }
    }

    private fun getImageURL(imageView: ImageView) {
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Rover", "response successful$json")
                picOfDayImageURL = json.jsonObject.getString("url")
                copyrightText = json.jsonObject.getString("copyright")

                Glide.with(this@MainActivity)
                    .load(picOfDayImageURL)
                    .fitCenter()
                    .into(imageView)
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Rover Error", errorResponse)
            }
        }]
    }

        private fun getImageCaptionURL(captionTekstView: TextView) {
            val client = AsyncHttpClient()
            client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object :
                JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    Log.d("Rover", "response successful$json")
                    copyrightText = json.jsonObject.getString("copyright")
                    captionTekstView.text = copyrightText

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Rover Error", errorResponse)
                }
            }]
        }
}

    private fun getImageTitleURL(titleView: TextView) {
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Pic", "response successful$json")
                titleText = json.jsonObject.getString("title")
                titleView.text = titleText

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Rover Error", errorResponse)
            }
        }]
    }




