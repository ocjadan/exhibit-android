package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.owners.Owner

open class OwnersCache(private val ownersDao: OwnersDao) {
    fun saveAll(owners: List<Owner>) {
        val ownerEntities = mapOwnersToOwnerEntities(owners)
        ownersDao.insertAll(ownerEntities)
    }

    private fun mapOwnersToOwnerEntities(owners: List<Owner>): List<OwnerEntity> {
        return owners.map { OwnerEntity(it.userId, it.name, it.profileImage, it.accountId ) }
    }
}