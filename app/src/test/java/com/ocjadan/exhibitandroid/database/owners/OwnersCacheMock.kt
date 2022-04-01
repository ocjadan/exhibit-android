package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.owners.Owner
import kotlinx.coroutines.CoroutineDispatcher

class OwnersCacheMock(private val ownersDaoMock: OwnersDao, dispatcher: CoroutineDispatcher) :
    OwnersCache(ownersDaoMock, dispatcher) {
    override suspend fun saveAll(owners: List<Owner>) {
        val entities = EntityTestData.mapOwnersToOwnerEntities(owners)
        ownersDaoMock.upsertAll(entities)
    }
}