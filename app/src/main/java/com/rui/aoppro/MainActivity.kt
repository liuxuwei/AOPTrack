package com.rui.aoppro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast

@PageEvent("home_page")
class MainActivity : BaseActivity() {
    private lateinit var fragment1: MyFragment
    private lateinit var fragment2: MyFragment
    private val countries = arrayOf("USA", "Canada", "UK", "Australia", "Germany")
    private val capitals = arrayOf("Washington", "Ottawa", "London", "Canberra", "Berlin")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//       activity_main startActivity(Intent(this, SecondActivity::class.java))


        initFragments()
        initView()

    }


    private fun initFragments() {
        fragment1 = MyFragment.newInstance("第一个")
        fragment2 = MyFragment.newInstance("你好的")
        supportFragmentManager.apply {
            beginTransaction().add(R.id.fragment_container, fragment1)
                .add(R.id.fragment_container, fragment2)
                .hide(fragment2)
                .show(fragment1)
                .commit()
        }
    }


    private fun initView() {
        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            supportFragmentManager.apply {
                beginTransaction()
                    .hide(fragment2)
                    .show(fragment1)
                    .commit()
            }
        }
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            supportFragmentManager.apply {
                beginTransaction()
                    .hide(fragment1)
                    .show(fragment2)
                    .commit()
            }
        }
    }
}


//        findViewById<TextView>(R.id.tv_click).setOnClickListener {
////                Toast.makeText(this@MainActivity, "点击了", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
//        }

//        val lv = findViewById<ListView>(R.id.lv)
//        val dataList = ArrayList<HashMap<String, String>>()
//        for (i in countries.indices) {
//            val data = HashMap<String, String>()
//            data["country"] = countries[i]
//            data["capital"] = capitals[i]
//            dataList.add(data)
//
////        findViewById<TextView>(R.id.tv_click).setOnClickListener(object : OnClickListener { //
////            override fun onClick(v: View?) {
////                Toast.makeText(this@MainActivity, "点击了", Toast.LENGTH_SHORT).show()
////            }
////        })
//        }
//
//
//        val adapter = SimpleAdapter(
//            this,
//            dataList,
//            android.R.layout.simple_list_item_2,
//            arrayOf("a"),
//            intArrayOf(android.R.id.text1)
//        )
//        lv.adapter = adapter
//
//        lv.setOnItemClickListener(object : OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                Toast.makeText(this@MainActivity, "点击了", Toast.LENGTH_SHORT).show()
//            }
//
//        })

//        initGrid()

//    private fun initGrid() {
//        val gridView: GridView = findViewById(R.id.gridview)
//
//// Create sample data
//        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")
//
//        // Create custom adapter
//        val adapter = CustomGridAdapter(this, items)
//
//        // 将适配器设置给 GridView
//        gridView.adapter = adapter
//
//        // 设置 GridView 的项点击事件监听器
//        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            // 在点击项时显示一个 Toast，显示点击的项的位置
//                Toast.makeText(this@MainActivity, "Item clicked at position $position", Toast.LENGTH_SHORT).show()
//        }
////        gridView.setOnItemClickListener(object : OnItemClickListener {
////            override fun onItemClick(
////                parent: AdapterView<*>?,
////                view: View?,
////                position: Int,
////                id: Long
////            ) {
////            }
//////
////        })
//    }
