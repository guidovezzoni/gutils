package com.guidovezzoni.gutils.architecture.pattern.repositorypattern.datasource;

/**
 * Adds some method to handle the cache
 *
 * @param <M> data model
 * @param <P> parameters required for obtaining the appropriate data
 */
@SuppressWarnings("unused")
public interface CacheDataSource<M, P> extends DataSource<M, P> {
    void invalidateCache();

    void setCacheValidity(Long newCacheValidity);
}
