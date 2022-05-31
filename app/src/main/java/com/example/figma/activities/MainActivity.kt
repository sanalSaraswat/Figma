package com.example.figma.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.figma.R
import com.example.figma.fragments.FourthFragment
import com.example.figma.fragments.HomeFragment
import com.example.figma.fragments.SecondFragment
import com.example.figma.fragments.ThirdFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var bottomBar: AnimatedBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#030303")))


        val button = findViewById<Button>(R.id.button)
        bottomBar = findViewById(R.id.bottomBar)


        if(bottomBar.selectedIndex == 0) {
            val homeFragment = HomeFragment()
            setCurrentFragment(homeFragment)
        }


        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {


                when (newIndex) {

                    0 ->{
                        val homeFragment = HomeFragment()
                        setCurrentFragment(homeFragment)
                    }

                    1 ->{
                        val secondFragment = SecondFragment()
                        setCurrentFragment(secondFragment)
                    }

                    2 -> {
                        val thirdFragment = ThirdFragment()
                        setCurrentFragment(thirdFragment)
                    }

                    3 -> {
                        val fourthFragment = FourthFragment()
                        setCurrentFragment(fourthFragment)
                    }



                }
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {

                Toast.makeText(this@MainActivity, tab.title, Toast.LENGTH_SHORT).show()
            }
        })





    }

    fun setCurrentFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.field_1 ->
                Toast.makeText(this,"Settings selected", Toast.LENGTH_LONG).show()

            R.id.field_2 ->
                Toast.makeText(this,"GroupChat selected", Toast.LENGTH_LONG).show()

            R.id.logOut -> {
                Intent(this, SignUpActivity::class.java).also {
                    startActivity(it)
                }

            }

        }

        return super.onOptionsItemSelected(item)
    }


}