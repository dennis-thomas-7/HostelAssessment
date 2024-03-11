//package com.network.hostelworld.presentation.propertylisting
//
//import com.network.hostelworld.data.PropertyRepository
//import com.network.hostelworld.presentation.PropertyContract
//import io.mockk.mockk
//import org.junit.Test
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.mockito.Mockito.verify
//
//
//class PropertyPresenterTest {
//
//    private lateinit var repository: PropertyRepository
//    private lateinit var view: PropertyContract.View
//    private lateinit var presenter: PropertyPresenter
//
//    @Before
//    fun setup() {
//        repository = mockk(relaxed = true)
//        view = mockk(relaxed = true)
//        presenter = PropertyPresenter(repository, view)
//    }
//
//    @Test
//    fun `test getPropertyList() method`() {
//        // Call the method to be tested
//        presenter.getPropertyList()
//
//        // Verify that the repository's getProperties() method is called
//        verify { repository.getProperties() }
//
//        // Verify that the view's showLoading() method is called
//        verify { view.showLoading() }
//    }
//}