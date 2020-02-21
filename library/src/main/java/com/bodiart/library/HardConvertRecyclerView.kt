package com.bodiart.library

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class HardConvertRecyclerView<T>(@LayoutRes layoutResId: Int) :
    BaseQuickAdapter<T, BaseViewHolder>(layoutResId) {

    private val adapterHelper = AdapterHelper<T> { position ->
        notifyItemChanged(position)
    }


    override fun convert(helper: BaseViewHolder, item: T) {
        hardConvert(helper, item)
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
        adapterHelper.viewRecycled(holder)
    }

    abstract fun hardConvert(helper: BaseViewHolder, item: T)

    protected fun makeItemAsyncOperation(
        helper: BaseViewHolder,
        action: () -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        adapterHelper.makeItemAsyncOperation(helper, action, dispatcher)
    }

    fun cancelOperations() {
        adapterHelper.cancelOperations()
    }
}