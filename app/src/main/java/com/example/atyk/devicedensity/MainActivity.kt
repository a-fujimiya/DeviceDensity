package com.example.atyk.devicedensity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()
    val display = windowManager.defaultDisplay
    val realMetrics = DisplayMetrics()
    display.getRealMetrics(realMetrics)
    val metrics = DisplayMetrics()
    display.getMetrics(metrics)

    val displayPx = findViewById(R.id.display_px) as TextView
    displayPx.text =
        getString(R.string.width_height, realMetrics.widthPixels, realMetrics.heightPixels)

    val applicationPx = findViewById(R.id.application_px) as TextView
    applicationPx.text =
        getString(R.string.width_height, metrics.widthPixels, metrics.heightPixels)

    val densityText = findViewById(R.id.density_view) as TextView
    densityText.text = getString(R.string.density_text, metrics.density, metrics.densityDpi)

    val dpiText = findViewById(R.id.dpi_text_view) as TextView
    dpiText.text = getDpiText(metrics.density)
  }

  override fun onPause() {
    super.onPause()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  private fun getDpiText(density: Float): String {
    val densityArray = floatArrayOf(0.75f, 1.0f, 1.5f, 2.0f, 3.0f, 4.0f)
    val densityNames = resources.getStringArray(R.array.density_names)
    var i = 0
    for (scale in densityArray) {
      if (java.lang.Float.compare(density, scale) == 0) {
        return densityNames[i]
      }
      i += 1
    }
    return getString(R.string.other_density)
  }
}
