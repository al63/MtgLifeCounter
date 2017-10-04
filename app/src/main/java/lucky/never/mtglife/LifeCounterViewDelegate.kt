package lucky.never.mtglife

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by aleclee on 9/29/17.
 */

class LifeCounterViewDelegate(private val mRoot: View) {

    companion object {
        fun create(root: View): LifeCounterViewDelegate {
            return LifeCounterViewDelegate(root)
        }
    }

    interface Listener {
        fun onOpponentLifeTotalUp()
        fun onOpponentLifeTotalDown()
        fun onSelfLifeTotalUp()
        fun onSelfLifeTotalDown()
    }

    private var mDiceRollWrapper: ViewGroup = mRoot.findViewById(R.id.dice_roll_wrapper) as ViewGroup
    private var mSelfDiceRoll: TextView = mRoot.findViewById(R.id.self_roll) as TextView
    private var mOpponentDiceRoll : TextView = mRoot.findViewById(R.id.opponent_roll) as TextView
    private var mAnimatingRoll = false

    private var mOpponentText: TextView = mRoot.findViewById(R.id.opponent_total) as TextView
    private var mSelfText: TextView = mRoot.findViewById(R.id.self_total) as TextView
    private var mSelfUp: View = mRoot.findViewById(R.id.self_up)
    private var mSelfDown: View = mRoot.findViewById(R.id.self_down)
    private var mOpponentUp: View = mRoot.findViewById(R.id.opponent_up)
    private var mOpponentDown: View = mRoot.findViewById(R.id.opponent_down)
    private var mListener: Listener? = null

    init {
        mSelfDown.setOnClickListener { mListener?.onSelfLifeTotalDown() }
        mSelfUp.setOnClickListener { mListener?.onSelfLifeTotalUp() }
        mOpponentDown.setOnClickListener { mListener?.onOpponentLifeTotalDown() }
        mOpponentUp.setOnClickListener { mListener?.onOpponentLifeTotalUp() }
    }

    fun setListener(listener: Listener?) {
        mListener = listener
    }

    fun setLifeTotals(selfLifeTotal: Int, opponentLifeTotal: Int) {
        mOpponentText.text = opponentLifeTotal.toString()
        mSelfText.text = selfLifeTotal.toString()
    }

    fun showDiceRollResults(selfDiceRoll: Int, opponentDiceRoll: Int) {
        if (mAnimatingRoll) {
            return
        }

        mAnimatingRoll = true
        TransitionUtil.beginTransition(mDiceRollWrapper)
        mDiceRollWrapper.visibility = View.VISIBLE

        var selfText: String? = null
        var opponentText: String? = null
        if (selfDiceRoll > opponentDiceRoll) {
            selfText = "Rolled $selfDiceRoll\nYou start"
            opponentText = "Rolled $opponentDiceRoll"
        } else if (opponentDiceRoll > selfDiceRoll) {
            selfText = "Rolled $selfDiceRoll"
            opponentText = "Rolled $opponentDiceRoll\nYou start"
        }
        mSelfDiceRoll.text = selfText
        mOpponentDiceRoll.text = opponentText

        mRoot.postDelayed({
            mAnimatingRoll = false
            TransitionUtil.beginTransition(mDiceRollWrapper)
            mDiceRollWrapper.visibility = View.GONE
            mSelfDiceRoll.text = null
            mOpponentDiceRoll.text = null
        }, 3000)
    }
}
