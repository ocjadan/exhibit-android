package com.ocjadan.exhibitandroid.database.updates

import com.ocjadan.exhibitandroid.database.EntityTestData
import kotlinx.coroutines.CoroutineDispatcher

class UpdatesCacheMock(private val updatesDaoMock: UpdatesDao, dispatcher: CoroutineDispatcher) :
    UpdatesCache(updatesDaoMock, dispatcher) {
    override suspend fun getLastQuestionsUpdate(): Long? {
        val entity = updatesDaoMock.getLastQuestionsUpdate()
        return entity?.timestamp
    }

    override suspend fun setLastQuestionsUpdate(timestamp: Long) {
        val entity = EntityTestData.getLastQuestionsUpdateEntity(timestamp)
        updatesDaoMock.setLastQuestionsUpdate(entity)
    }
}