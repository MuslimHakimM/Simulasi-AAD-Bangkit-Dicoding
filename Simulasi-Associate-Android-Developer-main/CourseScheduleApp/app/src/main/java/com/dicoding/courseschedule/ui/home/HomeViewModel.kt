package com.dicoding.courseschedule.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType

class HomeViewModel(var repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun getToday() = repository.getNearestSchedule(_queryType.value!!)

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }
}
