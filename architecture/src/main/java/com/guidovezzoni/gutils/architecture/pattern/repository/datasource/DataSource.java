package com.guidovezzoni.gutils.architecture.pattern.repository.datasource;


import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import io.reactivex.Maybe;

@SuppressWarnings("unused")
public interface DataSource<M, P> {
    Maybe<TimeStampedData<M>> get(P params);

    Maybe<TimeStampedData<M>> getAndUpdate(P params, DataSource<M, P> cacheSource);

    void set(TimeStampedData<M> model);
}
