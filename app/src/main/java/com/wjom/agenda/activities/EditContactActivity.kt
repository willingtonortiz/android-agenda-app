package com.wjom.agenda.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wjom.agenda.R
import com.wjom.agenda.models.Contact
import kotlinx.android.synthetic.main.activity_edit_contact.*

class EditContactActivity : AppCompatActivity() {

    var currentContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        // Llenar los datos al iniciar actividad
        val bundle = intent.extras
        bundle?.let {
            val contact = bundle.getSerializable("contact") as Contact
            tiName.editText?.setText(contact.name)
            tiPhone.editText?.setText(contact.phoneNumber)
            currentContact = contact
        }
    }

    private fun isNameValid(): Boolean {
        val name = tiName.editText?.text.toString().trim()

        if (name.isEmpty()) {
            tiName.error = "Este campo no puede estar vacio"
            return false
        } else {
            return true
        }
    }

    // Retornar los datos editados
    fun updateContact(v: View) {
        if (!isNameValid())
            return

        val name = tiName.editText?.text.toString().trim()
        val phone = tiPhone.editText?.text.toString().trim()
        val contact = Contact(name, phone)
        currentContact?.let {
            contact.id = it.id
        }

        val intent = Intent()
        intent.putExtra("contact", contact)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}