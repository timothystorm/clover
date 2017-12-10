#!/usr/bin/env groovy

@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser


import java.nio.file.Paths

import static org.apache.commons.csv.CSVFormat.*

Paths.get(args[0]).withReader { reader ->
    CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())

    new File(args[1]).withWriter { writer ->
        csv.collect { row ->
            writer.writeLine sprintf(
                    "INSERT INTO clover.address(code,country,locality,admin_level_1,latitude,longitude) VALUES('%s','%s','%s','%s',%s,%s);",
                    [row.get('PostalCode'),
                     'US',
                     row.get('PlaceName').replaceAll('\'', '\'\''),
                     row.get('StateAbbreviation'),
                     row.get('Latitude'),
                     row.get('Longitude')]
            )
        }
    }
}
