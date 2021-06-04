package com.google.samples.apps.hatch.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

    fun getProfiles() = profileDao.getProfiles()
}
