package lucky.never.mtglife

import android.view.MenuItem
import java.util.*

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
        } else if (item?.itemId == R.id.action_roll) {
            // roll 0-1 to decide winner
            if (Random().nextInt(2) == 0) {
                // I win, 2-6
                val selfRoll = Random().nextInt(5) + 2
                // opponent rolls 1-{selfRoll-1}
                val opponentRoll = Random().nextInt(selfRoll - 1) + 1
                mViewDelegate.showDiceRollResults(selfRoll, opponentRoll)
            } else {
                // opponent win, 2-6
                val opponentRoll = Random().nextInt(5) + 2
                // self rolls 1-{opponentRoll-1}
                val selfRoll = Random().nextInt(opponentRoll - 1) + 1
                mViewDelegate.showDiceRollResults(selfRoll, opponentRoll)
            }
        }
        return true
    }

    private val mListener = object: LifeCounterViewDelegate.Listener {
        override fun onOpponentLifeTotalUp() {
            mOpponentLifeTotal++
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }

        override fun onOpponentLifeTotalDown() {
            if (mOpponentLifeTotal > 0) {
                mOpponentLifeTotal--
                mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
            }
        }

        override fun onSelfLifeTotalUp() {
            mSelfLifeTotal++
            mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
        }

        override fun onSelfLifeTotalDown() {
            if (mSelfLifeTotal > 0) {
                mSelfLifeTotal--
                mViewDelegate.setLifeTotals(mSelfLifeTotal, mOpponentLifeTotal)
            }
        }
    }
}