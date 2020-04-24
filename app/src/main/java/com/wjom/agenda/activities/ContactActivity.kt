package com.wjom.agenda.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.wjom.agenda.R
import com.wjom.agenda.models.Contact
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        saveContact()
        return super.onOptionsItemSelected(item)
    }

    private fun saveContact() {
        val name = etName.text.toString()
        val phone = etPhone.text.toString()
//        val contact = Contact(name, phone)

        val intent = Intent()
        intent.putExtra("keyName", name)
        intent.putExtra("keyPhone", phone)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}