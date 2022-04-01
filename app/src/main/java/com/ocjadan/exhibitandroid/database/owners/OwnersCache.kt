package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.owners.Owner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class OwnersCache(private val ownersDao: OwnersDao, private val dispatcher: CoroutineDispatcher) {
    open suspend fun saveAll(owners: List<Owner>) = withContext(dispatcher) {
        val ownerEntities = mapOwnersToOwnerEntities(owners)
        ownersDao.upsertAll(ownerEntities)
    }

    private fun mapOwnersToOwnerEntities(owners: List<Owner>): List<OwnerEntity> {
        return owners.map { OwnerEntity(it.userId, it.name, it.profileImage, it.accountId) }
    }
}