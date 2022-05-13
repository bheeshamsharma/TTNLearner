package com.psgpw.geek_ttn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.psgpw.HireMe.data.ResultState
import com.psgpw.geek_ttn.data.dummymodel.*
import com.psgpw.pickapp.data.repos.NetworkRepo
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    private val networkRepo = NetworkRepo()

    lateinit var courses: LiveData<ResultState<List<Course>?>>
    lateinit var enrolledCourses: LiveData<ResultState<List<Course>?>>
    lateinit var assignments: LiveData<ResultState<List<Assignment>?>>
    lateinit var topics: LiveData<ResultState<List<Topic>?>>
    lateinit var subTopics: LiveData<ResultState<List<SubTopic>?>>
    lateinit var newCourse: LiveData<ResultState<Course?>>
    lateinit var enrollCourse: LiveData<ResultState<Course?>>
    lateinit var submitAssignment: LiveData<ResultState<Assignment?>>

    fun getEnrolledCourseList(userId: String) {
        viewModelScope.launch {
            enrolledCourses = networkRepo.getEnrolledCourse(userId).asLiveData()
        }
    }

    fun getCourseList() {
        viewModelScope.launch {
            courses = networkRepo.getCourses().asLiveData()

        }
    }

    fun getAssignmentList(userId: String) {
        viewModelScope.launch {
            assignments = networkRepo.getAssignmentList(userId).asLiveData()
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

    fun newCourseRequest(courseRequest: CourseRequest) {
        viewModelScope.launch {
            newCourse = networkRepo.newCourseRequest(courseRequest).asLiveData()
        }
    }

    fun submitAssignment(assignment: Assignment) {
        viewModelScope.launch {
            submitAssignment = networkRepo.submitAssignment(assignment).asLiveData()
        }
    }

    fun enrollCourseRequest(userId: String, CourseId: String) {
        viewModelScope.launch {
            enrollCourse = networkRepo.enrollCourseRequest(userId, CourseId).asLiveData()
        }
    }
}