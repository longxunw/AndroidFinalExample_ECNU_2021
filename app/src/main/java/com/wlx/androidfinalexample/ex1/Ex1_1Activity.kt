package com.wlx.androidfinalexample.ex1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R

class Ex1_1Activity : AppCompatActivity() {

    private val group = arrayListOf<Int>(1,2)
    private val mainTeam = hashMapOf<Int,MutableList<String>>(
        0 to arrayListOf("鼠队","狼队"),
        1 to arrayListOf("啦啦队","呼呼队")
    )
    private val guestTeam = hashMapOf<Int,MutableList<String>>(
        0 to arrayListOf("鼠队","兔队"),
        1 to arrayListOf("啦啦队","呜呜队")
    )
    private val TAG = "Ex1_1Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_ex1_1)
        val groupSpin : Spinner = findViewById(R.id.group_spinner)
        val mainTeamSpin: Spinner = findViewById(R.id.main_team_spinner)
        val guestTeamSpin: Spinner = findViewById(R.id.guest_team_spinner)
        val resultGroup: RadioGroup = findViewById(R.id.result_radio)

        val groupAdapter = ArrayAdapter<Int>(applicationContext,android.R.layout.simple_expandable_list_item_1,group)
        groupSpin.adapter = groupAdapter
        val mainTeamAdapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_expandable_list_item_1)
        mainTeamAdapter.addAll(mainTeam[0]!!)
        mainTeamSpin.adapter = mainTeamAdapter
        val guestTeamAdapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_expandable_list_item_1)
        guestTeamAdapter.addAll(guestTeam[0]!!)
        guestTeamSpin.adapter = guestTeamAdapter
        groupSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainTeamAdapter.clear()
                mainTeamAdapter.addAll(mainTeam[position]!!)
                mainTeamAdapter.notifyDataSetChanged()
                guestTeamAdapter.clear()
                guestTeamAdapter.addAll(guestTeam[position]!!)
                guestTeamAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        val confirm : Button = findViewById(R.id.confirm)
        confirm.setOnClickListener {
            val groupSelected = groupSpin.selectedItem as Int
            val mainTeamSelected = mainTeamSpin.selectedItem as String
            val guestTeamSelected = guestTeamSpin.selectedItem as String
            val resultButtonSelected: RadioButton = findViewById(resultGroup.checkedRadioButtonId)
            val resultSelected = resultButtonSelected.text
            if(mainTeamSelected == guestTeamSelected){
                Toast.makeText(applicationContext,"主队和客队不能相同",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,Ex1_2Activity::class.java).apply {
                    putExtra("group",groupSelected)
                    putExtra("mainTeam",mainTeamSelected)
                    putExtra("guestTeam",guestTeamSelected)
                    putExtra("result",resultSelected)
                }
                startActivity(intent)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }
}