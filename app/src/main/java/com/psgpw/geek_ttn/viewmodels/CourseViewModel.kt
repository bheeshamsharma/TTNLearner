package com.psgpw.geek_ttn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.data.dummymodel.Course
import com.psgpw.geek_ttn.data.dummymodel.SubTopic
import com.psgpw.geek_ttn.data.dummymodel.Topic
import com.psgpw.pickapp.data.repos.NetworkRepo
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    private val networkRepo = NetworkRepo()

    lateinit var courses: LiveData<ResultState<List<Course>?>>
    lateinit var topics: LiveData<ResultState<List<Topic>?>>
    lateinit var subTopics: LiveData<ResultState<List<SubTopic>?>>
    lateinit var subTopicDetail: LiveData<ResultState<SubTopic?>>

    fun getCourseList() {
        viewModelScope.launch {
            courses = networkRepo.getCourses().asLiveData()

        }
    }

    fun getTopicList(courseId: String) {
        viewModelScope.launch {
            topics = networkRepo.getTopics(courseId).asLiveData()

        }
    }

    fun getSubTopicList(topicId: String) {
        viewModelScope.launch {
            subTopics = networkRepo.getSubTopics(topicId).asLiveData()

        }
    }

    fun getSubTopicDetail(subTopicId: String) {
        viewModelScope.launch {
            subTopicDetail = networkRepo.getSubTopicDetail(subTopicId).asLiveData()
        }
    }
}