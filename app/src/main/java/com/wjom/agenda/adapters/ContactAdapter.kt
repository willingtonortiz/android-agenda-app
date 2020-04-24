package com.wjom.agenda.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.wjom.agenda.R
import com.wjom.agenda.activities.EditContactActivity
import com.wjom.agenda.models.Contact
import kotlinx.android.synthetic.main.prototype_contact.view.*

class ContactAdapter(
    val context: FragmentActivity,
    val contacts: ArrayList<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactPrototype>() {

    val EDIT_CONTACT_CODE = 2

    inner class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentContact: Contact? = null
        val tvName = itemView.tvName
        val tvPhone = itemView.tvPhone

        init {
            itemView.setOnClickListener {
                currentContact?.let {
                    Toast.makeText(
                        context,
                        "Nombre: ${it.name}, Phone: ${it.phoneNumber}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            itemView.tvName.setOnClickListener {
                itemView.easter_egg.visibility = View.VISIBLE
            }

            itemView.btn_edit.setOnClickListener {
                val intent = Intent(context, EditContactActivity::class.java)
                intent.putExtra("contact", currentContact)
                context.startActivityForResult(intent, EDIT_CONTACT_CODE)
            }

            itemView.btn_delete.setOnClickListener {
                val currentIndex = contacts.indexOfFirst { it.id == currentContact?.id }
                if (currentIndex == -1) {
                    Toast.makeText(context, "No seas mongol", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                contacts.removeAt(currentIndex)
                notifyItemRemoved(currentIndex)
            }
        }

        fun bind(contact: Contact?, index: Int) {
            contact?.let {
                tvName.text = contact.name
                tvPhone.text = contact.phoneNumber
            }
            this.currentContact = contact
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_contact, parent, false)

        return ContactPrototype(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactPrototype, position: Int) {
        holder.bind(contacts[position], position)
    }

}