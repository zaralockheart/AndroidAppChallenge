package com.example.ucoppp.androidappchallenge.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.imagedisplayer.ImagesActivity
import com.example.ucoppp.androidappchallenge.util.APPLICATION
import com.example.ucoppp.androidappchallenge.util.APP_SIGN_IN
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    private lateinit var homePresenter: HomePresenter

    companion object {

        const val EXTRA_USER_ID = "EXTRA_USER_ID"

        fun newIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            context.getSharedPreferences(APPLICATION, Context.MODE_PRIVATE).edit().putString(APP_SIGN_IN, userId).apply()
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadDatabase()

        homePresenter = HomePresenter(this)

        handleAllClicks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.sign_in_menu, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {

            R.id.buttonSignOut -> signUserOut()

        }
        return true
    }

    override fun onClickEditMobile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickImages() {
        startActivity(ImagesActivity.newIntent(this@HomeActivity))
    }

    private fun signUserOut() {
        sharedPreferences.edit().clear().apply()
        restartApp()
    }

    private fun restartApp() {
        val intent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun handleAllClicks() {
        buttonOpenImages.setOnClickListener { homePresenter.onClickImages() }
        buttonEditMobile.setOnClickListener { homePresenter.onClickEditMobile() }
    }

    private fun loadDatabase() {
        Observable.just(userDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db: UsersDatabase? ->
                    val user = db?.userDao()?.getUserByUuid(intent.getStringExtra(EXTRA_USER_ID))

                    setDataToUi(user)
                }
    }

    private fun setDataToUi(user: User?) {
        textFirstName.text = user?.firstName
        textLastName.text = user?.lastName
        textMobileNumber.text = user?.mobileNumber
    }
}