package com.example.gamelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGame: RecyclerView
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGame = findViewById(R.id.rv_game)
        rvGame.setHasFixedSize(true)

        list.addAll(getListGame())
        showRecyclerList()
    }



    private fun getListGame(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataRelease = resources.getStringArray(R.array.data_release)
        val listGame = ArrayList<Game>()
        for (i in dataName.indices) {
            val game = Game(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataPrice[i],
                dataRelease[i]
            )
            listGame.add(game)
        }
        return listGame
    }
    private fun showRecyclerList() {
        rvGame.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        rvGame.adapter = listGameAdapter
        listGameAdapter.setOnItemClickCallback(object : ListGameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA_GAME, data)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}