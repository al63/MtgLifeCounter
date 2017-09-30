package lucky.never.mtglife

import android.view.MenuItem

/**
 * Created by aleclee on 9/29/17.
 */

class LifeCounterPresenter(private val mViewDelegate: LifeCounterViewDelegate) {

    companion object {
        fun create(viewDelegate: LifeCounterViewDelegate): LifeCounterPresenter {
            return LifeCounterPresenter(viewDelegate)
        }
    }

    private var mSelfLifeTotal: Int = 20
    private var mOpponentLifeTotal: Int = 20

    fun onActive() {
        mViewDelegate.setListener(mListener)
    }

    fun onInactive() {
        mViewDelegate.setListener(null)
    }

    fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_refresh) {
            mSelfLifeTotal = 20
            mOpponentLifeTotal = 20
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
            return true
        }
        return false
    }

    private val mListener = object: LifeCounterViewDelegate.Listener {
        override fun onOpponentLifeTotalUp() {
            mOpponentLifeTotal++
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }

        override fun onOpponentLifeTotalDown() {
            mOpponentLifeTotal--
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }

        override fun onSelfLifeTotalUp() {
            mSelfLifeTotal++
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }

        override fun onSelfLifeTotalDown() {
            mSelfLifeTotal--
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }
    }
}