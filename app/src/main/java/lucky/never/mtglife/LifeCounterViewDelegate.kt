package lucky.never.mtglife

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by aleclee on 9/29/17.
 */

class LifeCounterViewDelegate(private val mContext: Context,
                              root: View) {

    companion object {
        fun create(context: Context, root: View): LifeCounterViewDelegate {
            return LifeCounterViewDelegate(context, root)
        }
    }

    interface Listener {
        fun onOpponentLifeTotalUp()
        fun onOpponentLifeTotalDown()
        fun onSelfLifeTotalUp()
        fun onSelfLifeTotalDown()
    }

    private var mOpponentText: TextView = root.findViewById(R.id.opponent_total) as TextView
    private var mSelfText: TextView = root.findViewById(R.id.self_total) as TextView
    private var mSelfUp: ImageView = root.findViewById(R.id.self_up) as ImageView
    private var mSelfDown: ImageView = root.findViewById(R.id.self_down) as ImageView
    private var mOpponentUp: ImageView = root.findViewById(R.id.opponent_up) as ImageView
    private var mOpponentDown: ImageView = root.findViewById(R.id.opponent_down) as ImageView
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
}
