package org.karensystem.core.databaseSwitch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSource implements Serializable {

    @Value("${databaseList}")
    private String[] databaseList;
    private HashMap<String, DataSource> dataSources = new HashMap<>();

//    @Autowired
//    private DataSourceConfigRepository configRepo;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    //@PostConstruct
    public Map<String, DataSource> getAll() {

        List<String> databaseNameList = List.of(databaseList);
        Map<String, DataSource> result = new HashMap<>();
        for (String dbName : databaseNameList) {
            DataSource dataSource = getDataSource(dbName);
            result.put(dbName, dataSource);
        }
        return result;
    }

    private DataSource createDataSource(String name) {
       /*
        DataSourceConfig config = configRepo.findByName(name);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverClassName())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            DataSource ds = factory.build();
            return ds;
        }

        */

        DataSourceBuilder factory = DataSourceBuilder
                .create()
                //.driverClassName(config.getDriverClassName())
                .username("root")
                .password("root1234")
                .url("jdbc:mysql://localhost:3306/"+name+"?createDatabaseIfNotExist=true&useSSL=false");
        DataSource ds = factory.build();
        return ds;
    }

}
