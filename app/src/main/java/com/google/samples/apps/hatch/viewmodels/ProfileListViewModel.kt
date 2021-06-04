package com.google.samples.apps.hatch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.samples.apps.hatch.data.ProfileListItem
import com.google.samples.apps.hatch.data.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The ViewModel for [ProfileListFragment].
 */
@HiltViewModel
class ProfileListViewModel @Inject internal constructor(
    profileRepository: ProfileRepository,
) : ViewModel() {

    val profiles: LiveData<List<ProfileListItem>> = profileRepository.getProfiles().asLiveData()
}
