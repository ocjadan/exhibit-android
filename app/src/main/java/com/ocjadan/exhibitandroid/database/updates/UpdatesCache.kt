package com.ocjadan.exhibitandroid.database.updates

import com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate.LastQuestionsUpdateEntity

open class UpdatesCache(private val updatesDao: UpdatesDao) {
    fun getLastQuestionsUpdate(): Long? {
        val entity = updatesDao.getLastQuestionsUpdate()
        return entity?.timestamp
    }

    fun setLastQuestionsUpdate(timestamp: Long) {
        val entity = LastQuestionsUpdateEntity(timestamp = timestamp)
        updatesDao.setLastQuestionsUpdate(entity)
    }
}