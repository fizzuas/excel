package com.oyx.excel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val models = Array(size = 24) {
        when (it){
            in 0.. 26 -> Model(('A'+it).toString())
            else  -> Model(it.toString())

        }
    }.toMutableList()

    private val modelAdapter: ModelAdapter = ModelAdapter(models)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recy.apply {
            adapter=modelAdapter
            layoutManager=GridLayoutManager(this@MainActivity,4)
            addItemDecoration(ExcelGridItemDecoration(this@MainActivity))
//            addItemDecoration(DividerGridItemDecoration(this@MainActivity))


        }
    }
}