package com.psgpw.geek_ttn.managers

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


object GoogleLoginManager {

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken("12530960803-tbs6t64n0l2f4g1d46gqg34369qej2sv.apps.googleusercontent.com")
        .build()

    fun getGoogleClient(activity: Activity): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, gso)
    }

    fun getLastLoginAccount(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }


}