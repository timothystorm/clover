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

public class AddressSearchJdbcDao implements AddressSearchDao {
    private static final String NEXT_QUERY = "SELECT * FROM clover.address WHERE id > ? ORDER BY id LIMIT ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS 'count' FROM clover.address";

    private final DataSource _dataSource;


    public AddressSearchJdbcDao(DataSource dataSource) {
        _dataSource = Objects.requireNonNull(dataSource);
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
            throw new RuntimeException("failed to search next Address", e);
        }
        return Long.MIN_VALUE;
    }

    @Override
    public List<Address> nextAfter(Address from, int maxSize) {
        try (Connection conn = _dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(NEXT_QUERY)) {
            stmt.setLong(1, (from == null ? -1 : Identifiable.Utils.getId(from)));
            stmt.setInt(2, maxSize);

            List<Address> addresses = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Address address = Address.builder()
                            .country(rs.getString("country"))
                            .locality(rs.getString("locality"))
                            .code(rs.getString("code"))
                            .adminLevel1(rs.getString("admin_level_1"))
                            .latitude(rs.getLong("latitude"))
                            .longitude(rs.getLong("longitude"))
                            .build();
                    Identifiable.Utils.setId(address, rs.getLong("id"));
                    addresses.add(address);
                }
            }

            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to search next Address", e);
        }
    }
}
