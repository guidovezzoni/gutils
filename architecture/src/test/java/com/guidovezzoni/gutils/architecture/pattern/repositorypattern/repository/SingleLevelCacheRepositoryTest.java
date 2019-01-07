package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.repository;

import com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource.CacheDataSource;
import com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource.DataSource;
import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SingleLevelCacheRepositoryTest {
    private static final String NETWORK_STRING = "Network";
    private static final String CACHE_STRING = "Cache";

    @Mock
    private DataSource<String, Void> networkDataSource;
    @Mock
    private CacheDataSource<String, Void> cacheDataSource;

    private SingleLevelCacheRepository<String, Void> sut;

    @Before
    public void setUp() {
        sut = new SingleLevelCacheRepository<>(networkDataSource, cacheDataSource);
    }

    @Test
    public void whenGetWithCacheAvailableThenReturnCache() {
        TestObserver<String> testObserver = TestObserver.create();
        when(cacheDataSource.get(null)).thenReturn(Maybe.just(TimeStampedData.of(CACHE_STRING)));
        when(networkDataSource.getAndUpdate(null, cacheDataSource)).thenReturn(Maybe.just(TimeStampedData.of(NETWORK_STRING)));

        sut.get(null).subscribe(testObserver);

        testObserver.assertResult(CACHE_STRING);  // includes .assertComplete().assertNoErrors()
        verify(cacheDataSource).get(null);
        verify(networkDataSource).getAndUpdate(null, cacheDataSource);
    }

    @Test
    public void whenGetWithCacheNotAvailableThenReturnFromNetwork() {
        TestObserver<String> testObserver = TestObserver.create();
        when(cacheDataSource.get(null)).thenReturn(Maybe.empty());
        when(networkDataSource.getAndUpdate(null, cacheDataSource)).thenReturn(Maybe.just(TimeStampedData.of(NETWORK_STRING)));

        sut.get(null)
                .subscribe(testObserver);

        testObserver.assertResult(NETWORK_STRING); // includes .assertComplete().assertNoErrors()
        verify(cacheDataSource).get(null);
        verify(cacheDataSource, never()).set(any());
        verify(networkDataSource).getAndUpdate(null, cacheDataSource);
    }

    @Test
    public void whenGetWithNetworkFailureThenReturnHandleIt() {
        TestObserver<String> testObserver = TestObserver.create();
        when(cacheDataSource.get(null)).thenReturn(Maybe.empty());
        when(networkDataSource.getAndUpdate(null, cacheDataSource)).thenReturn(Maybe.error(new Exception("generic network error")));

        sut.get(null)
                .subscribe(testObserver);

        testObserver.assertNotComplete()
                .assertError(throwable -> throwable instanceof Exception)
                .assertNoValues();
    }

    @Test
    public void whenGetLatestThenReturnNetwork() {
        TestObserver<String> testObserver = TestObserver.create();
        when(networkDataSource.get(null)).thenReturn(Maybe.just(TimeStampedData.of(NETWORK_STRING)));

        sut.getLatest(null)
                .subscribe(testObserver);

        testObserver.assertResult(NETWORK_STRING); // includes .assertComplete().assertNoErrors()

        verify(networkDataSource).get(null);
        verify(cacheDataSource, never()).get(null);
        verify(cacheDataSource).set(any());
    }
}
