package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource;


import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import io.reactivex.Maybe;

/**
 * Provides an interface used by {@link com.guidovezzoni.gutils.architecture.pattern.repositorypattern.repository.Repository}
 * to source data. It can be used for network or cache
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings("unused")
public interface DataSource<M, P> {
    Maybe<TimeStampedData<M>> get(P params);

    /**
     * this is supposed to be called when we need to updated a quicker cache
     *
     * @param params      parameters required for obtaining the appropriate data
     * @param cacheSource a {@link DataSource} representing the cache that needs updating
     * @return the requested data model
     */
    Maybe<TimeStampedData<M>> getAndUpdate(P params, DataSource<M, P> cacheSource);

    void set(TimeStampedData<M> model);
}
