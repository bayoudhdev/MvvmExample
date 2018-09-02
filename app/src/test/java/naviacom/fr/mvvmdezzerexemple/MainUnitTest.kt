package naviacom.fr.mvvmdezzerexemple

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.subscribers.TestSubscriber
import naviacom.fr.mvvmdezzerexemple.activities.main.MainViewModel
import naviacom.fr.mvvmdezzerexemple.data.PlayListRepository
import naviacom.fr.mvvmdezzerexemple.models.Playlist
import naviacom.fr.mvvmdezzerexemple.utils.schedulers.ImmediateSchedulerProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainUnitTest {
    private lateinit var mMainViewModel: MainViewModel
    @Mock
    private lateinit var mPlayListRepository: PlayListRepository
    private lateinit var mTestPlayListSubscriber: TestSubscriber<List<Playlist>>

    private val emptyPlayLists = arrayListOf<Playlist>()

    private val mockedPlayLists: List<Playlist> by lazy {
        val playLists = mutableListOf<Playlist>()
        playLists.add(mock { on { title } doReturn "Playlist 1" })
        playLists.add(mock { on { title } doReturn "Playlist 2" })
        playLists.add(mock { on { title } doReturn "Playlist 3" })
        playLists
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mMainViewModel= MainViewModel(mPlayListRepository,ImmediateSchedulerProvider())
        mTestPlayListSubscriber= TestSubscriber<List<Playlist>>()
    }

   // @Test
   // @Throws(Exception::class)
   // fun testFetchEmptyPlaylists() {
   //     val playlistViewModel = MainViewModel(playlistProvider)
   //     val observer = playlistViewModel.fetchPlayLists(0).test()
   //     observer.assertNoErrors()
   //     observer.assertValue(emptyPlayLists)
   // }

    @Test
    @Throws(Exception::class)
    fun testFetchMockPlayLists() {
        Mockito.`when`(mMainViewModel.fetchPlayLists(0)).thenReturn(Observable.just(mockedPlayLists))
      //  val observer = mMainViewModel.fetchPlayLists(0).test()
      //  observer.assertNoErrors()
      //  observer.assertValue(mockedPlayLists)
    }


}