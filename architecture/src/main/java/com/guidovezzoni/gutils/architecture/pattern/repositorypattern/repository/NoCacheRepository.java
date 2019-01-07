package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.repository;


import com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource.DataSource;
import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import io.reactivex.Single;

/**
 * Basic version of Repository Pattern, with no cache
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class NoCacheRepository<M, P> implements Repository<M, P> {
    protected final DataSource<M, P> networkDataSource;

    public NoCacheRepository(DataSource<M, P> networkDataSource) {
        this.networkDataSource = networkDataSource;
    }

    @Override
    public Single<M> get(P params) {
        return getLatest(params);
    }

    @Override
    public Single<M> getLatest(P params) {
        return networkDataSource.get(params)
                .map(TimeStampedData::getModel)
                .toSingle();
    }
}
