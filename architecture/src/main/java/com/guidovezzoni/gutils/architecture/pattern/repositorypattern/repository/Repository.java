package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.repository;

import io.reactivex.Single;

/**
 * Interface for the repository pattern
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate
 */
@SuppressWarnings("unused")
public interface Repository<M, P> {
    Single<M> get(P params);

    /**
     * always get the latest network info, no cache involved
     *
     * @param params parameters required for obtaining the appropriate data
     * @return the data model from the network
     */
    Single<M> getLatest(P params);
}
