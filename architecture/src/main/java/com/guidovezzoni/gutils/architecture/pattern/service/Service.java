package com.guidovezzoni.gutils.architecture.pattern.service;

import io.reactivex.Single;

/**
 * Forwards data to the app from the repository, providing any logic if required.
 *
 * @param <M> data model - this should be different from the type used in the Repository Pattern
 * @param <P> parameters required for obtaining the appropriate data
 */
public interface Service<M, P> {
    Single<M> get(P params);
}
