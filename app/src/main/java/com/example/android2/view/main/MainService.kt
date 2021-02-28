package com.example.android2.view.main

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android2.R
import com.example.android2.model.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

class MainService(name: String? = "MainService") : IntentService(name) {

    private val mainBroadcastIntent = Intent(MAIN_INTENT_FILTER)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        loadFilms()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFilms() {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY&language=en-US")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_TIMEOUT
                }

                val filmDTO: FilmDTO =
                    Gson().fromJson(
                        getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                        FilmDTO::class.java
                    )
                onResponse(filmDTO)
            } catch (e: Exception) {
                onErrorRequest(e.message ?: getString(R.string.err_request_msg))
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun onResponse(filmDTO: FilmDTO) {
        val filmList = filmDTO.results
        filmList?.let {
            onSuccessResponse(it)
        } ?: run { onEmptyResponse() }
    }

    private fun onSuccessResponse(filmList: ArrayList<Film>) {
        putLoadResult(MAIN_RESPONSE_SUCCESS_EXTRA)
        mainBroadcastIntent.putParcelableArrayListExtra(MAIN_FILM_LIST_EXTRA, filmList)
        LocalBroadcastManager.getInstance(this).sendBroadcast(mainBroadcastIntent)
    }

    private fun onMalformedURL() {
        putLoadResult(MAIN_URL_MALFORMED_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(mainBroadcastIntent)
    }

    private fun onErrorRequest(error: String) {
        putLoadResult(MAIN_REQUEST_ERROR_EXTRA)
        mainBroadcastIntent.putExtra(MAIN_REQUEST_ERROR_MESSAGE_EXTRA, error)
        LocalBroadcastManager.getInstance(this).sendBroadcast(mainBroadcastIntent)
    }

    private fun onEmptyResponse() {
        putLoadResult(MAIN_DATA_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(mainBroadcastIntent)
    }

    private fun putLoadResult(result: String) {
        mainBroadcastIntent.putExtra(MAIN_LOAD_RESULT_EXTRA, result)
    }
}