package com.example.attractortest.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attractortest.data.model.Company
import com.example.attractortest.data.model.User
import com.example.attractortest.utils.extensions.readAssetsFile
import org.json.JSONObject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileViewModel : ViewModel(), KoinComponent {

    private val androidContext: Context by inject()

    private val _profileLiveData = MutableLiveData<User>()
    val profileLiveData: LiveData<User> get() = _profileLiveData

    init {
        getProfileData()
    }

    private fun getProfileData() {
        val result = androidContext.assets.readAssetsFile("file.json")

        val jObject = JSONObject(result)
        val user = jObject.getString(USER)
        val userjObject = JSONObject(user)

        val firstName = userjObject.getString(FIRST_NAME)
        val photo = userjObject.getString(PHOTO)
        val secondName = userjObject.getString(SECOND_NAME)
        val education = userjObject.getInt(EDUCATION)
        val companyList = getCompanyList(userjObject)

        _profileLiveData.value =
            User(
                firstName = firstName,
                photo = photo,
                secondName = secondName,
                education = education,
                company = companyList
            )
    }

    private fun getCompanyList(userjObject: JSONObject): ArrayList<Company> {
        val jsonArray = userjObject.getJSONArray(COMPANY)
        val arrayList = arrayListOf<Company>()

        for (i in 0 until jsonArray.length()) {
            val itemArray = jsonArray.getJSONObject(i)
            val name = itemArray.getString(NAME)
            val position = itemArray.getString(POSITION)

            arrayList.add(
                Company(
                    name = name,
                    position = position
                )
            )
        }

        return arrayList
    }

    companion object {
        private const val USER = "user"
        private const val NAME = "name"
        private const val FIRST_NAME = "first_name"
        private const val SECOND_NAME = "second_name"
        private const val PHOTO = "photo"
        private const val EDUCATION = "education"
        private const val COMPANY = "company"
        private const val POSITION = "position"
    }
}