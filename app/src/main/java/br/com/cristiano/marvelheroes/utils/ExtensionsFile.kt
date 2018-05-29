package br.com.cristiano.marvelheroes.utils

import android.app.Activity
import android.widget.Toast
import java.security.MessageDigest


fun Activity.toaster(text:String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

fun String.hashMD5():String{
    return HashUtil.md5(this)
}


