package com.wjom.agenda.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjom.agenda.models.Contact
import com.wjom.agenda.R
import com.wjom.agenda.adapters.ContactAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val ADD_CONTACT_CODE = 1
    val EDIT_CONTACT_CODE = 2

    var contacts = ArrayList<Contact>()
    var contactAdapter = ContactAdapter(this, contacts)

    init {
        Contact.index = contacts.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadContacts()
        initView()
    }

    fun initView() {
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts.add(Contact(name = "Jorge", phoneNumber = "999999999"))
        contacts.add(Contact(name = "Luis", phoneNumber = "897645321"))
        contacts.add(Contact(name = "Guili", phoneNumber = "321456789"))
        contacts.add(Contact(name = "Jorge", phoneNumber = "999999999"))
        contacts.add(Contact(name = "Luis", phoneNumber = "897645321"))
        contacts.add(Contact(name = "Guili", phoneNumber = "321456789"))
        contacts.add(Contact(name = "Jorge", phoneNumber = "999999999"))
        contacts.add(Contact(name = "Luis", phoneNumber = "897645321"))
        contacts.add(Contact(name = "Guili", phoneNumber = "321456789"))
        contacts.add(Contact(name = "Jorge", phoneNumber = "999999999"))
        contacts.add(Contact(name = "Luis", phoneNumber = "897645321"))
        contacts.add(Contact(name = "Guili", phoneNumber = "321456789"))
        contacts.add(Contact(name = "Jorge", phoneNumber = "999999999"))
        contacts.add(Contact(name = "Luis", phoneNumber = "897645321"))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, ContactActivity::class.java)
        startActivityForResult(intent, ADD_CONTACT_CODE)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_CONTACT_CODE && resultCode == Activity.RESULT_OK) {
            var name = data?.getStringExtra("keyName");
            var phone = data?.getStringExtra("keyPhone");

            name = name ?: "Error";
            phone = phone ?: "Error";

            val contact = Contact(name, phone);
            contacts.add(contact)

        } else if (requestCode == EDIT_CONTACT_CODE && resultCode == Activity.RESULT_OK) {
            val bundle = data?.extras

            bundle?.let {
                val contact = bundle.getSerializable("contact") as Contact
                val index = contacts.indexOfFirst { it.id == contact.id }

                contacts[index] = contact
                rvContact.adapter?.notifyItemChanged(index)
            }
        }
    }
}
