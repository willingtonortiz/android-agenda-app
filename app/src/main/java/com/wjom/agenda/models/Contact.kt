package com.wjom.agenda.models

import java.io.Serializable

data class Contact(
    val name: String,
    val phoneNumber: String
) : Serializable {
    companion object {
        var index = 0
    }

    var id: Int

    init {
        id = ++index
    }
}
//
//fun main(){
//
//    val c1 = Contact(name = "", phoneNumber = "")
//    println(c1.toString())
//}