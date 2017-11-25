package com.example.leet.graduatedesign

import LoginAndRegister.LoginFragment
import LoginAndRegister.RegisterFragment
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList

import android.Manifest.permission.READ_CONTACTS
import android.app.Activity
import android.app.FragmentManager
import android.view.Window
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)
        var loginfrag=LoginFragment()
        var registerfrag=RegisterFragment()
        logintext.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.registerlayout,loginfrag)
            logintext.setTextColor(R.color.cpb_blue)
            registext.setTextColor(R.color.cpb_grey)
        }
        registext.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.loginlayout,registerfrag)
            logintext.setTextColor(R.color.cpb_grey)
            registext.setTextColor(R.color.cpb_blue)
        }


    }

}

