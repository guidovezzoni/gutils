package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource;


import com.guidovezzoni.gutils.architecture.util.RxUtils;
import com.guidovezzoni.gutils.architecture.util.TimeStampedData;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * This class fetches the info from the retrofit service and onSuccess returns the result
 * on stream - or returns an errors if that is the case
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class NetworkDataSource<M, P> implements DataSource<M, P> {

    /**
     * this need to be implemented for the specific network call
     *
     * @param params parameters required for obtaining the appropriate data
     * @return retrofit response
     */
    protected abstract Single<Response<M>> getFromEndPoint(P params);

    @Override
    public Maybe<TimeStampedData<M>> get(P params) {
        return getFromEndPoint(params)
                .compose(RxUtils.unWrapSingleResponseWithErrorOnStream())
                .map(TimeStampedData::of)
                .toMaybe();
    }

    @Override
    public Maybe<TimeStampedData<M>> getAndUpdate(P params, DataSource<M, P> cacheSource) {
        return get(params)
                .doAfterSuccess(cacheSource::set);
    }
}
