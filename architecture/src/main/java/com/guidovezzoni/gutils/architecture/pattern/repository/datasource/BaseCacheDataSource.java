package com.guidovezzoni.gutils.architecture.pattern.repository.datasource;


import com.fernandocejas.arrow.checks.Preconditions;
import com.guidovezzoni.gutils.architecture.pattern.repository.datasource.util.CacheHelper;
import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;

/**
 * This class fetches data from cache if present and valid, and returns the result on stream.
 *
 * @implNote parameters are ignored for now. It will require some sort of handling via HashMap.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class BaseCacheDataSource<M, P> implements CacheDataSource<M, P> {
    private static final long DEFAULT_CACHE_VALIDITY = TimeUnit.MINUTES.toMillis(5);

    private TimeStampedData<M> cachedValue;

    private final CacheHelper cacheHelper;
    private long cacheValidity;

    public BaseCacheDataSource(CacheHelper cacheHelper) {
        this.cacheHelper = cacheHelper;
        cacheValidity = DEFAULT_CACHE_VALIDITY;
        invalidateCache();
    }

    @Override
    public Maybe<TimeStampedData<M>> get(P params) {
        Preconditions.checkArgument(params == null, "Params must be NULL");
        return isCacheValid() ? Maybe.just(cachedValue) : Maybe.empty();
    }

    @Override
    public Maybe<TimeStampedData<M>> getAndUpdate(P params, DataSource<M, P> cacheSource) {
        Preconditions.checkArgument(params == null, "Params must be NULL");
        if (isCacheValid()) {
            cacheSource.set(cachedValue);
            return Maybe.just(cachedValue);
        } else {
            return Maybe.empty();
        }
    }

    @Override
    public void set(TimeStampedData<M> model) {
        updateCache(model);
    }

    @Override
    public void setCacheValidity(Long newCacheValidity) {
        cacheValidity = (newCacheValidity != null) ? newCacheValidity : DEFAULT_CACHE_VALIDITY;
    }

    protected void updateCache(TimeStampedData<M> newValue) {
        cachedValue = newValue;
    }

    public void invalidateCache() {
        updateCache(null);
    }

    private boolean isCacheValid() {
        return cachedValue != null && cacheHelper.isTimeStampValid(cachedValue.getTimestamp(), cacheValidity);
    }
}
