package lucky.never.mtglife

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mPresenter: LifeCounterPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mPresenter = LifeCounterPresenter.create(LifeCounterViewDelegate.create(this, findViewById(R.id.root)))
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.onActive()
    }

    override fun onPause() {
        super.onResume()
        mPresenter?.onInactive()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.action_refresh)?.icon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return mPresenter?.onOptionsItemSelected(item) ?: false
    }

}
