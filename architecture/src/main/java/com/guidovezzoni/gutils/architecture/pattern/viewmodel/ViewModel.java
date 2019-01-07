package com.guidovezzoni.gutils.architecture.pattern.viewmodel;

import io.reactivex.Single;

/**
 * Basic ViewModel interface
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings("unused")
public interface ViewModel<M, P> {
    Single<M> get(P params);
}
