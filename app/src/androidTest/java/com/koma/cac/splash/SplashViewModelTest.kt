package com.koma.cac.splash

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.koma.cac.data.source.Repository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@SmallTest
@RunWith(AndroidJUnit4::class)
class SplashViewModelTest {
    private val repository = mock(Repository::class.java)

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        viewModel = SplashViewModel(repository, ApplicationProvider.getApplicationContext())
    }

    @Test
    fun should_launch_auth_page_when_not_login() {
        `when`(repository.isLogin()).thenReturn(false)

        viewModel.start()

        assertThat(viewModel.intent)
    }
}