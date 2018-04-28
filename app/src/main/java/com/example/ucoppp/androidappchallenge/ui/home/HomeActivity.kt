package com.example.ucoppp.androidappchallenge.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.database.user.UsersDatabase
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.ui.imagedisplayer.ImagesActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeView {


    private lateinit var homePresenter: HomePresenter

    companion object {

        const val EXTRA_USER_ID = "EXTRA_USER_ID"

        fun newIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
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

    override fun onClickEditMobile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickImages() {
        startActivity(ImagesActivity.newIntent(this@HomeActivity))
    }
}