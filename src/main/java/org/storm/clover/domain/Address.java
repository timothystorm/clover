package org.storm.clover.domain;

import java.util.Objects;

public class Address implements Comparable<Address>, Identifiable {
    private final String _code, _country, _locality, _adminLevel1;
    private final double _latitude, _longitude;
    private long id;

    public Address(String country, String locality, String code, String adminLevel1, double latitude, double longitude) {
        _code = Objects.requireNonNull(code);
        _country = Objects.requireNonNull(country);
        _locality = Objects.requireNonNull(locality);
        _adminLevel1 = Objects.requireNonNull(adminLevel1);
        _latitude = latitude;
        _longitude = longitude;
    }

    public static Builder builder() {
        return new Address.Builder();
    }

    public String getCode() {
        return _code;
    }

    public String getCountry() {
        return _country;
    }

    public String getLocality() {
        return _locality;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    @Override
    public int compareTo(Address other) {
        if (other == null) return 1;
        if (other == this) return 0;

        int compare = _country.compareTo(other._country);
        if (compare != 0) return compare;

        compare = _locality.compareTo(other._locality);
        if (compare != 0) return compare;

        compare = _code.compareTo(other._code);
        if (compare != 0) return compare;

        compare = _adminLevel1.compareTo(other._adminLevel1);
        if (compare != 0) return compare;

        if (_latitude < other._latitude) return -1;
        if (_latitude > other._latitude) return 1;

        if (_longitude < other._longitude) return -1;
        if (_longitude > other._longitude) return 1;

        // verify comparison is consistent with equality
        assert this.equals(other);

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Address)) return false;
        if (obj == this) return true;

        Address other = (Address) obj;
        return (Objects.equals(_country, other._country) &&
                Objects.equals(_locality, other._locality) &&
                Objects.equals(_code, other._code) &&
                Objects.equals(_adminLevel1, other._adminLevel1) &&
                Objects.equals(_latitude, other._latitude) &&
                Objects.equals(_longitude, other._longitude));
    }

    @Override
    public int hashCode() {
        return Objects.hash(_country, _locality, _code, _adminLevel1, _latitude, _longitude);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(Address.class.getName()).append("@").append(System.identityHashCode(this));

        str.append("[");
        str.append(",id:").append(this.id);
        str.append(",country:").append(_country);
        str.append(",locality:").append(_locality);
        str.append(",code:").append(_code);
        str.append(",adminLevel1:").append(_adminLevel1);
        str.append(",latitude:").append(_latitude);
        str.append(",longitude:").append(_longitude);
        str.append("]");
        return str.toString();
    }

    public static class Builder {
        private String _code, _country, _locality, _adminLevel1;
        private double _latitude, _longitude;

        public Builder code(String code) {
            _code = Objects.requireNonNull(code);
            return this;
        }

        public Builder postalCode(String postalCode) {
            return code(postalCode);
        }

        public Builder zipCode(String zipCode) {
            return code(zipCode);
        }

        public Builder country(String country) {
            _country = Objects.requireNonNull(country);
            return this;
        }

        public Builder locality(String locality) {
            _locality = Objects.requireNonNull(locality);
            return this;
        }

        public Builder city(String city) {
            return locality(city);
        }

        public Builder town(String town) {
            return locality(town);
        }

        public Builder adminLevel1(String adminLevel1) {
            _adminLevel1 = Objects.requireNonNull(adminLevel1);
            return this;
        }

        public Builder state(String state) {
            return adminLevel1(state);
        }

        public Builder province(String province) {
            return adminLevel1(province);
        }

        public Builder latitude(double latitude) {
            _latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            _longitude = longitude;
            return this;
        }

        public Builder latLon(double latitude, double longitude) {
            latitude(latitude);
            longitude(longitude);
            return this;
        }

        public Address build() {
            return new Address(_country, _locality, _code, _adminLevel1, _latitude, _longitude);
        }
    }
}
