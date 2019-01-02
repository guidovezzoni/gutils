package com.guidovezzoni.gutils.architecture.pattern.repository.datasource;

@SuppressWarnings("unused")
public interface CacheDataSource<M, P> extends DataSource<M, P> {
    void invalidateCache();

    void setCacheValidity(Long newCacheValidity);
}
