package com.guidovezzoni.gutils.architecture.pattern.viewmodel;

import com.guidovezzoni.gutils.architecture.pattern.service.Service;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.BehaviorSubject;

@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseViewModel<M, P> extends androidx.lifecycle.ViewModel implements ViewModel<M, P> {
    protected final Service<M, P> service;

    private final BehaviorSubject<Boolean> loadingSubject;

    public BaseViewModel(Service<M, P> service) {
        this.service = service;

        loadingSubject = BehaviorSubject.create();
    }

    @Override
    public Single<M> get(P params) {
        return service.get(params)
                .doOnSubscribe(disposable -> loadingSubject.onNext(true))
                .doOnSuccess(tvShows -> loadingSubject.onNext(false));
    }

    @NonNull
    public Observable<Boolean> getLoadingIndicatorVisibility() {
        return loadingSubject.hide();
    }
}
