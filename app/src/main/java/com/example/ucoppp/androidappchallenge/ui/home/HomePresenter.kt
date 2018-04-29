package com.example.ucoppp.androidappchallenge.ui.home

import android.app.AlertDialog
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.UserDao
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.ui.base.BasePresenter
import com.example.ucoppp.androidappchallenge.ui.imagedisplayer.ImagesActivity
import com.example.ucoppp.androidappchallenge.util.isValidMobileNumber
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val homeView: HomeView, appCompatActivity: AppCompatActivity) : BasePresenter(appCompatActivity) {

    fun onClickEditMobile(userDatabase: UsersDatabase, userDao: UserDao, userId: String) {
        val dialog = AlertDialog.Builder(appCompatActivity)
        val viewRoot: ViewGroup? = null
        val view: View = appCompatActivity.layoutInflater.inflate(R.layout.dialog_edit_mobile, viewRoot)
        val mobileNumberEditText = view.findViewById<EditText>(R.id.editTextMobileNumber)
        dialog.let {

            setUiMobileDialog(it, appCompatActivity.getString(R.string.text_change_mobile), view)

            it.setPositiveButton(appCompatActivity.getString(R.string.text_change), { mDialog, _ ->
                if (mobileNumberEditText.text.toString().isValidMobileNumber()) {

                    updateMobileNumber(userDao, userId, mobileNumberEditText)

                } else {
                    homeView.onUpdateMobileFail(appCompatActivity.getString(R.string.text_invalid_mobile))
                }

                loadData(userDatabase = userDatabase, userId = userId)

                mDialog.dismiss()
            })

            it.show()
        }
    }

    fun onClickImages() {
        appCompatActivity.startActivity(ImagesActivity.newIntent(appCompatActivity))
    }

    fun onClickSignOut(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit().clear().apply()
        homeView.onSignOut()
    }

    fun loadData(userDatabase: UsersDatabase, userId: String) {
        Observable.just(userDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db: UsersDatabase? ->
                    val user = db?.userDao()?.getUserByUuid(userId)
                    homeView.updateDataToi(user)
                }
    }

    private fun updateMobileNumber(userDao: UserDao, userId: String, mobileNumberEditText: EditText) {
        Observable.fromCallable({
            userDao.updateMobileNumber(userId, mobileNumberEditText.text.toString())
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    homeView.onUpdateMobileSuccess()
                }, { _ ->
                    homeView.onUpdateMobileFail(appCompatActivity.getString(R.string.text_change_mobile_fail))
                })
    }

    private fun setUiMobileDialog(dialog: AlertDialog.Builder, title: String, view: View) {
        dialog.setTitle(title)
        dialog.setView(view)
    }
}