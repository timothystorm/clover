CREATE TABLE clover.address (
  -- identity
  id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  code          VARCHAR(12)     NOT NULL
  COMMENT 'zip/postal_code',

  -- components
  country       CHAR(2)         NOT NULL,
  locality      VARCHAR(64)     NOT NULL
  COMMENT 'city/town',
  admin_level_1 VARCHAR(64)     NOT NULL
  COMMENT 'state/province',
  latitude      DECIMAL(8, 4)   NOT NULL,
  longitude     DECIMAL(8, 4)   NOT NULL,

  PRIMARY KEY (id)
);

CREATE INDEX code_index
  ON clover.address (code);
CREATE INDEX country_index
  ON clover.address (country);
CREATE INDEX locality_index
  ON clover.address (locality);
CREATE INDEX admin_level_1_index
  ON clover.address (admin_level_1);
CREATE INDEX lat_lon_index
  ON clover.address (latitude, longitude);