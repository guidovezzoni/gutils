package com.guidovezzoni.gutils.architecture.pattern.repository.repository;


import com.guidovezzoni.gutils.architecture.util.TimeStampedData;
import com.guidovezzoni.gutils.architecture.pattern.repository.datasource.DataSource;

import io.reactivex.Single;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseRepository<M, P> implements Repository<M, P> {
    protected final DataSource<M, P> networkDataSource;

    public BaseRepository(DataSource<M, P> networkDataSource) {
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
