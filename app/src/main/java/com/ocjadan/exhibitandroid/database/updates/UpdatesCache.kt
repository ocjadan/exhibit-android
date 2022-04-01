package com.ocjadan.exhibitandroid.database.updates

import com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate.LastQuestionsUpdateEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class UpdatesCache(
    private val updatesDao: UpdatesDao,
    private val dispatcher: CoroutineDispatcher
) {
    open suspend fun getLastQuestionsUpdate(): Long? = withContext(dispatcher) {
        val entity = updatesDao.getLastQuestionsUpdate()
        return@withContext entity?.timestamp
    }

    open suspend fun setLastQuestionsUpdate(timestamp: Long) = withContext(dispatcher) {
        val entity = LastQuestionsUpdateEntity(timestamp = timestamp)
        updatesDao.setLastQuestionsUpdate(entity)
    }
}