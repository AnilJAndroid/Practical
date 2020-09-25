package com.example.mypractical

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypractical.adapter.DepartmentAdapter
import com.example.mypractical.model.Deparment
import com.example.mypractical.model.DepartmentModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_department.*


class DepartmentActivity : AppCompatActivity() {

    private val todoAdapter by lazy { DepartmentAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)

        setupRecyclerView()

    }

    override fun onResume() {
//        val myData = MyPrefrence(applicationContext).data
//        myData.let {
//            val list = Gson().fromJson(myData, object : TypeToken<List<Any>>() {}.type) as ArrayList<Any>
//            todoAdapter.addModels(list)
//        }



        super.onResume()
    }

    private fun setupRecyclerView() {
        rv_department_list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = todoAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
        }

        spinnerDeparmentType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                todoAdapter.addItem(p0?.getItemAtPosition(p2).toString())
            }
        }

        todoAdapter.setClickListner(object : OnClickedListner {
            override fun OnCLicked(pos: Int, callback: String) {
                when (callback) {
                    "add" -> {
                        val modelType = todoAdapter.getListModels()[pos] as String

                        val model = DepartmentModel().apply {
                            title = ""
                            priority = setPriority(modelType)
                        }
                        todoAdapter.addItemPos(pos + 1, model)
                    }

                    "remove" -> {
                        todoAdapter.removeItem(pos)
                    }

                    "status" -> {
                        todoAdapter.changeStatus(pos)
                    }
                }
            }
        })
    }


    fun setPriority(priority: String): Deparment {
        return when (priority) {
            "Android" -> Deparment.Android
            "Web" -> Deparment.WEB
            "IOS" -> Deparment.IOS
            else -> Deparment.Android
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_add -> {

              /*  val data = todoAdapter.getListModels();

                val str = Gson().toJson(data,
                    object : TypeToken<ArrayList<Any>>() {}.type)
                MyPrefrence(applicationContext).data = str

                Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show()*/
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}