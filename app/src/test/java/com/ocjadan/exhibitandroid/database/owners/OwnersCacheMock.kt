package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.owners.Owner

class OwnersCacheMock(private val ownersDaoMock: OwnersDao) : OwnersCache(ownersDaoMock) {
    override suspend fun saveAll(owners: List<Owner>) {
        val entities = EntityTestData.mapOwnersToOwnerEntities(owners)
        ownersDaoMock.insertAll(entities)
    }
}