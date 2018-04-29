package com.example.ucoppp.androidappchallenge.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.ucoppp.androidappchallenge.R
import com.example.ucoppp.androidappchallenge.database.user.User
import com.example.ucoppp.androidappchallenge.ui.base.BaseActivity
import com.example.ucoppp.androidappchallenge.util.APPLICATION
import com.example.ucoppp.androidappchallenge.util.APP_SIGN_IN
import com.example.ucoppp.androidappchallenge.util.runOnUi
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeView {

    private lateinit var homePresenter: HomePresenter

    private val userId by lazy {
        intent.getStringExtra(EXTRA_USER_ID)
    }

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

        homePresenter = HomePresenter(this, this)

        homePresenter.loadData(userDatabase, intent.getStringExtra(HomeActivity.EXTRA_USER_ID))

        handleAllClickEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.sign_in_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.buttonSignOut -> homePresenter.onClickSignOut(sharedPreferences)

        }
        return true
    }

    override fun onUpdateMobileSuccess() {
        runOnUi {
            Toast.makeText(this, getString(R.string.text_change_mobile_success), Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpdateMobileFail(text: String?) {
        runOnUi {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSignOut() {
        val intent = baseContext
                .packageManager
                .getLaunchIntentForPackage(baseContext.packageName)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun updateDataToi(user: User?) {
        runOnUi {
            textFirstName.text = user?.firstName
            textLastName.text = user?.lastName
            textMobileNumber.text = user?.mobileNumber
        }
    }

    private fun handleAllClickEvents() {
        buttonOpenImages.setOnClickListener { homePresenter.onClickImages() }
        buttonEditMobile.setOnClickListener { homePresenter.onClickEditMobile(userDatabase, userDao, userId) }
    }
}