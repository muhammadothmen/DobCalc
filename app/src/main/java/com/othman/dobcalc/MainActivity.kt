package com.othman.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

      private  var selDateBtn: Button?=null
      private  var dayBtn: Button?=null
      private  var minBtn: Button?=null
      private  var hourBtn: Button?=null
      private  var secondBtn: Button?=null
      private  var outText: TextView? = null
      private  var selDateText: TextView? = null
      private  var yourAgText: TextView? = null
      private  val myCalendar =Calendar.getInstance()
      private  var year = myCalendar.get(Calendar.YEAR)
      private  var month = myCalendar.get(Calendar.MONTH)
      private  var day = myCalendar.get(Calendar.DAY_OF_MONTH)
      private  var diffDate = 0L
      private  var i = 0

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
            if(i==1) {
                yourAgText?.text = "Your age is :"
                outText?.text =
                    "${(diffDate / (1000)).toString()}\n Seconds"
            }
        }

        minBtn?.setOnClickListener {
            if(i==1) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60)).toString()}\n Minutes"
            }
        }

        hourBtn?.setOnClickListener {
            if (i == 1){
                yourAgText?.text = "Your age is :"
            outText?.text = "${(diffDate / (1000 * 60 * 60)).toString()}\n Hours"
            }
        }

        dayBtn?.setOnClickListener {
            if(i==1) {
                yourAgText?.text = "Your age is :"
                outText?.text = "${(diffDate / (1000 * 60 * 60 * 24)).toString()}\n Days"
            }
        }

    }
    @SuppressLint("SetTextI18n")
    private fun selDateBtnClicked (){
        val dbd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, sYear, sMonth, sDay ->

            val selDate ="$sDay/${sMonth+1}/$sYear"
            val sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            selDateText?.text = "Your Birthday is $selDate"
            val nSelDate = sdf.parse(selDate)
            var fSelDate = 0L
            nSelDate?.let { fSelDate = nSelDate.time}
            year = myCalendar.get(Calendar.YEAR)
            month = myCalendar.get(Calendar.MONTH)
            day = myCalendar.get(Calendar.DAY_OF_MONTH)
            val currentDate ="$day/${month+1}/$year"
            val nCurrentDate = sdf.parse(currentDate)
            var fCurrentDate = 0L
            nCurrentDate?.let {  fCurrentDate = nCurrentDate.time }
            diffDate = fCurrentDate - fSelDate

            outText?.text =" Select by"
            i=1
            yourAgText?.text = ""

        },
            year,
            month,
            day
        )

        dbd.datePicker.maxDate = System.currentTimeMillis() - (1000*3600*24)
        dbd.show()




     }
}


