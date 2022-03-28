package com.ocjadan.exhibitandroid.database.updates

import com.ocjadan.exhibitandroid.database.EntityTestData

class UpdatesCacheMock(private val updatesDaoMock: UpdatesDao) : UpdatesCache(updatesDaoMock) {
    override suspend fun getLastQuestionsUpdate(): Long? {
        val entity = updatesDaoMock.getLastQuestionsUpdate()
        return entity?.timestamp
    }

    override suspend fun setLastQuestionsUpdate(timestamp: Long) {
        val entity = EntityTestData.getLastQuestionsUpdateEntity(timestamp)
        updatesDaoMock.setLastQuestionsUpdate(entity)
    }
}