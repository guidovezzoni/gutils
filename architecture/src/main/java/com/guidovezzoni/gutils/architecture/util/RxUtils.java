package com.guidovezzoni.gutils.architecture.util;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import retrofit2.Response;

public final class RxUtils {
    private static final String NETWORK_ERROR = "Network error";

    private RxUtils() {
    }

    public static <T> SingleTransformer<Response<T>, T> unWrapSingleResponseWithErrorOnStream() {
        return single -> single.flatMap(response -> {
            if (response.body() != null) {
                return Single.just(response.body());
            }

            try {
                return Single.error(
                        new Exception(
                                response.errorBody() != null ? response.errorBody().string() : NETWORK_ERROR));
            } catch (IOException e) {
                return Single.error(e);
            }
        });
    }
}
