package com.othman.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

      private  var selDateBtn: Button?=null
      private var inMinuteText: TextView? = null
      private var inDaysText: TextView? = null
      private var inHoursText: TextView? = null
      private var inSecondsText: TextView? = null
      private var selDateText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selDateText=findViewById(R.id.TxtSelDate)
        inMinuteText=findViewById(R.id.TxtInMinute)
        inHoursText=findViewById(R.id.TxtInHours)
        inDaysText=findViewById(R.id.TxtInDays)
        inSecondsText=findViewById(R.id.TxtInSecond)
        selDateBtn=findViewById(R.id.BtnDateSel)

        selDateBtn?.setOnClickListener {
            selDateBtnClicked()
        }

    }
    private fun selDateBtnClicked (){

         val myCalendar =Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)

         val currentDate ="$day/${month+1}/$year"
         val sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
         val nCurrentDate = sdf.parse(currentDate)
         var fCurrentDate = 0L
         nCurrentDate?.let {  fCurrentDate = nCurrentDate.time }

       val dbd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, sYear, sMonth, sDay ->

             val selDate ="$sDay/${sMonth+1}/$sYear"
             selDateText?.text = selDate
             val nSelDate = sdf.parse(selDate)
             var fSelDate = 0L
             nSelDate?.let { fSelDate = nSelDate.time}

             val diffDate = fCurrentDate - fSelDate

             inDaysText?.text = (diffDate/(1000*3600*24)).toString()
             inHoursText?.text = (diffDate/(1000*3600)).toString()
             inMinuteText?.text = (diffDate/(1000*60)).toString()
             inSecondsText?.text = (diffDate/(1000)).toString()


         },
             year,
             month,
             day
         )

       dbd.datePicker.maxDate = fCurrentDate
       dbd.show()

     }
}


