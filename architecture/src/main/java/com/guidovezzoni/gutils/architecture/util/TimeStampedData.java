package com.guidovezzoni.gutils.architecture.util;

@SuppressWarnings("unused")
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

    public M getModel() {
        return model;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
