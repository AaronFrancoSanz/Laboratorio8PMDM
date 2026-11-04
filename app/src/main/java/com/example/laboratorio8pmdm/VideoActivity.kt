package com.example.laboratorio8pmdm
import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        webView = findViewById(R.id.webViewPalantir)

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true // Permite almacenar configuraciones locales del reproductor
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        // ID del vídeo de YouTube con permisos de inserción pública activos
        val videoId = "YE7VzlLtp-4"

        val iframeHtml = """
            <html>
            <head>
                <style>
                    body { margin: 0; padding: 0; background-color: #000000; }
                    .video-container { position: relative; width: 100%; height: 100%; }
                    iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
                </style>
            </head>
            <body>
                <div class="video-container">
                    <iframe src="https://www.youtube.com/embed/$videoId" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                </div>
            </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL("https://www.youtube.com", iframeHtml, "text/html", "utf-8", null)
    }

    override fun onDestroy() {
        webView.stopLoading()
        webView.destroy()
        super.onDestroy()
    }
}