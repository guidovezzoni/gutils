package com.guidovezzoni.gutils.architecture.pattern.repository.repository;

import io.reactivex.Single;

@SuppressWarnings("unused")
public interface Repository<M, P> {
    Single<M> get(P params);

    Single<M> getLatest(P params);
}
