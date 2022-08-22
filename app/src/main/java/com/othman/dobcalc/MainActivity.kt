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
    private var tvselecteddate: TextView? = null
    private var tvinminutes: TextView? = null
    private  var btndate: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //private  val btndate: Button = findViewById(R.id.btndate)
        btndate = findViewById(R.id.btndate)
        tvselecteddate = findViewById(R.id.selecteddate)
        tvinminutes= findViewById(R.id.inminutes)
        btndate?.setOnClickListener {

           // clicked()
            click()

        }

    }

  private  fun clicked() {

        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
       val dpdp = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                Toast.makeText(this, "cliked", Toast.LENGTH_LONG).show()
                val selecteddate = "$dayOfMonth/${month + 1}/$year"
                tvselecteddate?.text = selecteddate
                val sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val thedate=sdf.parse(selecteddate)
                thedate?.let {
                val selectedinmin = thedate.getTime()/3600000
                val current = sdf.parse(sdf.format(System.currentTimeMillis()))
                    current?.let {
                        val currentm = current.time / 3600000
                        tvinminutes?.text = (currentm - selectedinmin).toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpdp.datePicker.maxDate=System.currentTimeMillis()-(24*3600)
        dpdp.show()

    }


    private  fun click() {

        DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,year:Int,month,day->

            val selecteddate = "$day/${month + 1}/$year"
            tvselecteddate?.text = selecteddate

        },2022,5,23).show()


    }
}


