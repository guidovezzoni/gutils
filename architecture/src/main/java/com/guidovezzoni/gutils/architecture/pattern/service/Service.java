package com.guidovezzoni.gutils.architecture.pattern.service;

import io.reactivex.Single;

public interface Service<M, P> {
    Single<M> get(P params);
}
