package com.guidovezzoni.gutils.architecture.pattern.repository.repository;


import com.guidovezzoni.gutils.architecture.util.TimeStampedData;
import com.guidovezzoni.gutils.architecture.pattern.repository.datasource.CacheDataSource;
import com.guidovezzoni.gutils.architecture.pattern.repository.datasource.DataSource;

import io.reactivex.Single;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseCachedRepository<M, P> implements Repository<M, P> {
    protected final DataSource<M, P> networkDataSource;
    protected final CacheDataSource<M, P> cacheDataSource;

    public BaseCachedRepository(DataSource<M, P> networkDataSource, CacheDataSource<M, P> cacheDataSource) {
        this.networkDataSource = networkDataSource;
        this.cacheDataSource = cacheDataSource;
    }

    @Override
    public Single<M> get(P params) {
        return cacheDataSource.get(params)
                .switchIfEmpty(networkDataSource.getAndUpdate(params, cacheDataSource))
                .map(TimeStampedData::getModel)
                .toSingle();
    }

    @Override
    public Single<M> getLatest(P params) {
        return networkDataSource.get(params)
                .doOnSuccess(cacheDataSource::set)
                .map(TimeStampedData::getModel)
                .toSingle();
    }

    public void setCacheValidity(Long newCacheValidity) {
        cacheDataSource.setCacheValidity(newCacheValidity);
    }

    public void invalidateCache() {
        cacheDataSource.invalidateCache();
    }
}
