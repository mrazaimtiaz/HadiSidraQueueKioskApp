package com.gicproject.salamkioskapp.domain.use_case


import com.gicproject.salamkioskapp.common.Resource
import com.gicproject.salamkioskapp.data.remote.dto.DepartmentDto
import com.gicproject.salamkioskapp.data.remote.dto.DoctorDto
import com.gicproject.salamkioskapp.domain.model.Department
import com.gicproject.salamkioskapp.domain.model.Doctor
import com.gicproject.salamkioskapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDoctors @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(): Flow<Resource<List<Doctor>>> = flow {
        try {
            emit(Resource.Loading())

         //  var locations = repository.getLocations()
            var doctors = listOf(DoctorDto("1","Dr Emad","ENT","30 KD","01:00 AM","03-02-2023"),DoctorDto("1","Dr Wasim","ENT","25 KD","02:00 AM","03-02-2023"),)
            if (!doctors.isNullOrEmpty()) {
                emit(Resource.Success(doctors.map {
                    it.toDoctor()
                }))
            } else {
                emit(Resource.Error("Empty Department List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}