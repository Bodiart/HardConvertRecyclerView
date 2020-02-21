package com.bodiart.library

import android.util.SparseArray
import androidx.core.util.contains
import androidx.core.util.forEach
import kotlinx.coroutines.Job

class ItemOperationsController {

    // item position to operations
    private val itemOperations = SparseArray<Job>()

    fun putOperation(itemPosition: Int, job: Job) {
        closeOperations(itemPosition)
        itemOperations.put(itemPosition, job)
    }

    fun closeOperations(itemPosition: Int) {
        if (itemOperations.contains(itemPosition))
            itemOperations[itemPosition].cancel()
    }

    fun closeOperations() {
        itemOperations.forEach { _, value ->
            value.cancel()
        }
    }

}