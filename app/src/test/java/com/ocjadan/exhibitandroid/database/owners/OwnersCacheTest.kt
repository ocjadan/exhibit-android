package com.ocjadan.exhibitandroid.database.owners

import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.common.TestData
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class OwnersCacheTest {

    private lateinit var SUT: OwnersCache
    private lateinit var ownersDao: OwnersDaoMock

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcher = compositionRoot.testDispatcher

        ownersDao = compositionRoot.getOwnersDaoMock()
        SUT = OwnersCache(ownersDao.mock, dispatcher)

        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun saveAll_owners_insertAllCalledWithOwners() = runTest {
        val owners = TestData.getOwners()
        val entities = EntityTestData.mapOwnersToOwnerEntities(owners)
        SUT.saveAll(owners)
        verify(ownersDao.mock).upsertAll(entities)
    }
}