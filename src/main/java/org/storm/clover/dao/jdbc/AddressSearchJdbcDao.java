package org.storm.clover.dao.jdbc;

import org.storm.clover.dao.AddressSearchDao;
import org.storm.clover.domain.Address;
import org.storm.clover.domain.Identifiable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class AddressSearchJdbcDao implements AddressSearchDao {
    private static final String ALL_QUERY = "SELECT * FROM clover.address";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS 'count' FROM clover.address";

    private final DataSource _dataSource;
    private final Function<ResultSet, Address> ADDRESS_EXTRACTOR = (rs) -> {
        try {
            Address address = Address.builder()
                    .country(rs.getString("country"))
                    .locality(rs.getString("locality"))
                    .code(rs.getString("code"))
                    .adminLevel1(rs.getString("admin_level_1"))
                    .latitude(rs.getLong("latitude"))
                    .longitude(rs.getLong("longitude"))
                    .build();
            Identifiable.Utils.setId(address, rs.getLong("id"));
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("failed to extract address");
        }
    };

    public AddressSearchJdbcDao(DataSource dataSource) {
        _dataSource = Objects.requireNonNull(dataSource);
    }

    @Override
    public List<Address> findAll() {
        try (Connection conn = _dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ALL_QUERY)) {
            stmt.setFetchSize(1000);

            List<Address> addresses = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    addresses.add(ADDRESS_EXTRACTOR.apply(rs));
                }
            }
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to search next Address", e);
        }
    }

    @Override
    public long count() {
        try (Connection conn = _dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(COUNT_QUERY)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Long.MIN_VALUE;
    }

    @Override
    public List<Address> findAfter(Address from, int maxSize) {
        if (maxSize <= 0) throw new IllegalArgumentException("maxSize must be greater than 0!");
        long id = Identifiable.Utils.getId(from, -1);

        try (Connection conn = _dataSource.getConnection();
             PreparedStatement stmt = prepareFindAfterQuery(conn, id, maxSize)) {

            List<Address> addresses = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    addresses.add(ADDRESS_EXTRACTOR.apply(rs));
                }
            }

            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement prepareFindAfterQuery(Connection conn, long id, int maxSize) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM clover.address WHERE id > ? ORDER BY id LIMIT ?");
        ps.setLong(1, id);
        ps.setInt(2, maxSize);
        return ps;
    }
}
