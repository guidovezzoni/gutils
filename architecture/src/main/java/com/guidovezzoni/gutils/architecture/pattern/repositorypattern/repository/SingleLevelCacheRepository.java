package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.repository;


import com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource.CacheDataSource;
import com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource.DataSource;
import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import io.reactivex.Single;

/**
 * Repository Pattern, with one level of cache
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SingleLevelCacheRepository<M, P> implements Repository<M, P> {
    protected final DataSource<M, P> networkDataSource;
    protected final CacheDataSource<M, P> cacheDataSource;

    public SingleLevelCacheRepository(DataSource<M, P> networkDataSource, CacheDataSource<M, P> cacheDataSource) {
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
