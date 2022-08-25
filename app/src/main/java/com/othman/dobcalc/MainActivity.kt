package com.othman.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mainHandler: Handler
    private val updateTextTask = object : Runnable {
        override fun run() {
            display()
            mainHandler.postDelayed(this, 1000)
        }
    }

      private  var selDateBtn: Button?=null
      private  var dayBtn: Button?=null
      private  var minBtn: Button?=null
      private  var hourBtn: Button?=null
      private  var secondBtn: Button?=null
      private  var outText: TextView? = null
      private  var selDateText: TextView? = null
      private  var yourAgText: TextView? = null
      private  var sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
      private  val myCalendar =Calendar.getInstance()
      private  var year = myCalendar.get(Calendar.YEAR)
      private  var month = myCalendar.get(Calendar.MONTH)
      private  var day = myCalendar.get(Calendar.DAY_OF_MONTH)
      private  var diffDate = 0L
      private  var fSelDate = 0L
      private  var i = 0
      private  var j = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selDateText=findViewById(R.id.TxtSelDate)
        outText =findViewById(R.id.TxtOut)
        yourAgText=findViewById(R.id.TxtYourAge)
        hourBtn=findViewById(R.id.BtnHour)
        dayBtn=findViewById(R.id.BtnDay)
        minBtn=findViewById(R.id.BtnMin)
        secondBtn=findViewById(R.id.BtnSec)
        selDateBtn = findViewById(R.id.BtnDateSel)

        selDateBtn?.setOnClickListener {
            selDateBtnClicked ()
        }

        secondBtn?.setOnClickListener {
            if(j==5) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000)).toString()}\n Seconds"
                i=1
            }
        }

        minBtn?.setOnClickListener {
            if(j==5) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60)).toString()}\n Minutes"
                i=2
            }
        }

        hourBtn?.setOnClickListener {
            if(j==5) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60 * 60)).toString()}\n Hours"
                i=3
            }
        }

        dayBtn?.setOnClickListener {
            if(j==5) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60 * 60 * 24)).toString()}\n Days"
                i=4
            }
        }


        mainHandler = Handler(Looper.getMainLooper())

    }

    private fun selDateBtnClicked (){
        val dbd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, sYear, sMonth, sDay ->

            val selDate ="$sDay/${sMonth+1}/$sYear"
            selDateText?.text = "Your Birthday is: $selDate"
            val nSelDate = sdf.parse(selDate)
            nSelDate?.let { fSelDate = nSelDate.time}
            yourAgText?.text =" Select unit"
            outText?.text = ""
            j=5
            i=5
        },
            year,
            month,
            day
        )

        dbd.datePicker.maxDate = System.currentTimeMillis() - (1000*3600*24)
        dbd.show()

     }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

    fun display () {

        diffDate = System.currentTimeMillis() - fSelDate

        when(i){

            1 -> {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000)).toString()}\n Seconds"
            }
            2 -> {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60)).toString()}\n Minutes"
            }
            3 -> {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60 * 60)).toString()}\n Hours"
            }
            4 -> {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60 * 60 * 24)).toString()}\n Days"
            }
        }
    }
}


