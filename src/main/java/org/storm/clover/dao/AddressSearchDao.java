package org.storm.clover.dao;

import org.storm.clover.domain.Address;

import java.util.List;

public interface AddressSearchDao {
    /**
     * Finds addresses that are comparatively after (greater than) the provided address.
     * Address comparability is determined by implementations.
     *
     * @param from    - address, exclusive, to use as the starting point of cursor fetch.
     *                If null the fetch starts at the lowest {@link Address}
     * @param maxSize - max result to return in the collection.
     * @return {@link Address}es after the provided {@link Address} with a maximum size of results, can be empty but not null.
     */
    List<Address> findAfter(Address from, int maxSize);

    List<Address> findAll();

    /**
     * @return count of {@link Address}es in the data source
     */
    long count();
}
