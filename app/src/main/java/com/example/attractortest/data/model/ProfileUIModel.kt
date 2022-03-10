package com.example.attractortest.data.model

import androidx.recyclerview.widget.DiffUtil
import com.example.attractortest.R
import com.google.gson.annotations.SerializedName

data class ProfileUIModel(
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("education")
    val education: Int,
    @SerializedName("company")
    val company: List<Company>
) {
    fun getAcademicDegree(): Int {
        return when (education) {
            AcademicDegree.HIGH_SCHOOL.value -> R.string.high_school
            AcademicDegree.BACHELOR.value -> R.string.bachelor
            AcademicDegree.MASTER.value -> R.string.master
            AcademicDegree.DOCTORAL.value -> R.string.doctoral
            else -> R.string.unknown_academic_degree
        }
    }
}

data class Company(
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String
) {
    companion object {
        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Company>() {
            override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean =
                oldItem == newItem
        }
    }
}

enum class AcademicDegree(val value: Int) {
    HIGH_SCHOOL(1),
    BACHELOR(2),
    MASTER(3),
    DOCTORAL(4)
}