package com.bodiart.library

import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class AdapterHelper<T>(private val notifyItemChangedAction: (Int) -> Unit) {

    private val itemOperationsController = ItemOperationsController()

    private val coroutineJob = Job()
    private val foregroundScope = CoroutineScope(Dispatchers.Main + coroutineJob)


    fun makeItemAsyncOperation(
        helper: BaseViewHolder,
        action: () -> Unit,
        dispatcher: CoroutineDispatcher
    ) {
        val position = helper.layoutPosition

        val job = foregroundScope.launch {

            withContext(dispatcher) {
                action.invoke()
            }
            notifyItemChangedAction.invoke(position)
        }
        itemOperationsController.putOperation(position, job)
    }

    fun viewRecycled(helper: BaseViewHolder) {
        itemOperationsController.closeOperations(helper.layoutPosition)
    }

    fun cancelOperations() {
        coroutineJob.cancel()
        itemOperationsController.closeOperations()
    }
}