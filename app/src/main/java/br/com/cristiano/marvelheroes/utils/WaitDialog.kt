package br.com.cristiano.marvelheroes.utils

import android.app.ProgressDialog
import android.content.Context


object WaitDialog {

    private lateinit var dialog: ProgressDialog
    private var count: Array<Int>? = null

    @JvmOverloads
    fun showWaitDialog(context: Context, msg: String = message_waint(context)) {
        if (count == null) {
            count = arrayOf(1)
            count!![0] = 0
        }
        count!![0] += 1
        if (count!![0] == 1) {
            dialog = ProgressDialog(context)
            dialog!!.setMessage(msg)
            dialog!!.setCancelable(false)
            dialog!!.setInverseBackgroundForced(false)
            dialog!!.show()
        }

    }

    fun closeWaitDialog() {
        if (count == null) {
            count = arrayOf(1)
            count!![0] = 0
        }
        count!![0] -= 1

        if (count!![0] <= 0) {
            count!![0] = 0
            // dialog.cancel();
            if (dialog != null)
                dialog!!.dismiss()
        }
    }

    fun closeWaitDialog(listenerExecute: IListenerExecute<Boolean>) {
        if (count == null) {
            count = arrayOf(1)
            count!![0] = 0
        }
        count!![0] -= 1

        if (count!![0] <= 0) {
            count!![0] = 0
            // dialog.cancel();
            if (dialog != null) {
                dialog!!.dismiss()
            }
        }
        listenerExecute.onExecuted(count!![0] <= 0)
    }

    interface IListenerExecute<R> {
        fun onExecuted(result: R)
    }


}
