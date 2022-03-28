package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.common.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class OwnersCacheTest {

    private lateinit var SUT: OwnersCache
    private lateinit var ownersDao: OwnersDaoMock

    @Before
    fun setUp() {
        ownersDao = CompositionRoot().getOwnersDaoMock()
        SUT = OwnersCache(ownersDao.mock)
    }

    @Test
    fun saveAll_owners_insertAllCalledWithOwners() = runTest {
        val owners = TestData.getOwners()
        val entities = EntityTestData.mapOwnersToOwnerEntities(owners)
        SUT.saveAll(owners)
        verify(ownersDao.mock).insertAll(entities)
    }
}