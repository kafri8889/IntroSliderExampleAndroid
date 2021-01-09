package com.eunidev.belajarbuatintroslider

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var preferencesManager: PreferencesManager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var dotsContainer: LinearLayout
    private lateinit var buttonNext: Button
    private lateinit var buttonSkip: Button
    private lateinit var dots: Array<TextView?>
    private var layouts: IntArray = intArrayOf(R.layout.slider1, R.layout.slider2, R.layout.slider3, R.layout.slider4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        preferencesManager = PreferencesManager(applicationContext)
        if (!preferencesManager.isFirstLaunchApp()) {
            toMainActivity()
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_welcome_screen)

        init()

        // Add First Dots
        addBottomDots(0)

        viewPager.adapter = viewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        buttonSkip.setOnClickListener { toMainActivity() }
        buttonNext.setOnClickListener {
            val current = getItem(+1)
            if (current < layouts.size) {
                viewPager.setCurrentItem(current, true)
            } else {
                toMainActivity()
            }
        }

    }

    private fun init() {
        viewPager = findViewById(R.id.viewPager_WelcomeScreenActivity)
        dotsContainer = findViewById(R.id.dotsContainer_WelcomeScreenActiviy)
        buttonNext = findViewById(R.id.buttonNext_WelcomeScreenActivity)
        buttonSkip = findViewById(R.id.buttonSkip_WelcomeScreenActivity)
        viewPagerAdapter = ViewPagerAdapter(layouts)
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size)
        val colorActive = resources.getIntArray(R.array.array_dot_active)
        val colorInactive = resources.getIntArray(R.array.array_dot_inactive)

        dotsContainer.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.textSize = 35F
            dots[i]!!.setTextColor(colorInactive[currentPage])

            if (Build.VERSION.SDK_INT >= 24) dots[i]!!.text = Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY)
            else dots[i]!!.text = Html.fromHtml("&#226;")

            dotsContainer.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[currentPage]!!.setTextColor(colorActive[currentPage])
        }
    }

    private val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            if (position == layouts.size - 1) {
                buttonNext.setText(R.string.mulai)
                buttonSkip.visibility = View.GONE
            } else {
                buttonNext.setText(R.string.lanjut)
                buttonSkip.visibility = View.VISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    private fun getItem(i: Int): Int = viewPager.currentItem + i

    fun toMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}