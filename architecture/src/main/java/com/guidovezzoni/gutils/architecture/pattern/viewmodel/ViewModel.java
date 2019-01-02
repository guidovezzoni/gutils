package com.guidovezzoni.gutils.architecture.pattern.viewmodel;

import io.reactivex.Single;

@SuppressWarnings("unused")
public interface ViewModel<M, P> {
    Single<M> get(P params);
}
