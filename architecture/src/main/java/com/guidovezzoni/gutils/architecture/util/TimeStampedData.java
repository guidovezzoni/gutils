package com.guidovezzoni.gutils.architecture.util;

@SuppressWarnings({"WeakerAccess", "unused"})
public class TimeStampedData<M> {

    private final M model;
    private final Long timestamp;

    public TimeStampedData(M model, Long timestamp) {
        this.model = model;
        this.timestamp = timestamp;
    }

    public TimeStampedData(M model) {
        this.model = model;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> TimeStampedData<T> of(T t) {
        return new TimeStampedData<>(t);
    }

    public static <T> TimeStampedData<T> of(T t, Long timestamp) {
        return new TimeStampedData<>(t, timestamp);
    }

    public M getModel() {
        return model;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
