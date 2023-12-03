package luis.mvi.uzumbank.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/*
created by Xo'jaakbar on 24.10.2023 at 19:38
*/

@Singleton
class SharedPref @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val pref = context.getSharedPreferences("info",Context.MODE_PRIVATE)

    var isLogIn:Boolean
        get() = pref.getBoolean("isLogin",false)
        set(value) = pref.edit().putBoolean("isLogin",value).apply()

    var token:String
        get() = pref.getString("token","").toString()
        set(value) = pref.edit().putString("token",value).apply()

    var summa:String
        get() = pref.getString("summa","").toString()
        set(value) = pref.edit().putString("summa",value).apply()

    var accessToken:String
        get() = pref.getString("accessToken","").toString()
        set(value) = pref.edit().putString("accessToken",value).apply()

    var password:String
        get() = pref.getString("password","").toString()
        set(value) = pref.edit().putString("password",value).apply()

    var phone:String
        get() = pref.getString("phone","").toString()
        set(value) = pref.edit().putString("phone",value).apply()

    var code:String
        get() = pref.getString("code","").toString()
        set(value) = pref.edit().putString("code",value).apply()



}