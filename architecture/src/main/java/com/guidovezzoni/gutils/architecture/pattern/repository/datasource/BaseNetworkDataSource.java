package com.guidovezzoni.gutils.architecture.pattern.repository.datasource;


import com.guidovezzoni.gutils.architecture.util.TimeStampedData;
import com.guidovezzoni.gutils.architecture.util.RxUtils;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * This class fetches the info from the retrofit service and onSuccess returns the result
 * on stream - or returns an errors if that is the case
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class BaseNetworkDataSource<M, P> implements DataSource<M, P> {
    protected abstract Single<Response<M>> getFromEndPoint(P params);

    @Override
    public Maybe<TimeStampedData<M>> get(P params) {
        return getFromEndPoint(params)
                .compose(RxUtils.unWrapSingleResponseWithErrorOnStream())
                .map(TimeStampedData::new)
                .toMaybe();
    }

    @Override
    public Maybe<TimeStampedData<M>> getAndUpdate(P params, DataSource<M, P> cacheSource) {
        return get(params)
                .doAfterSuccess(cacheSource::set);
    }
}
