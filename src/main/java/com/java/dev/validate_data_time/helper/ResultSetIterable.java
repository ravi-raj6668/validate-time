package com.java.dev.validate_data_time.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterable implements Iterable<ResultSet> {
    private final ResultSet resultSet;

    public ResultSetIterable(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Iterator<ResultSet> iterator() {
        return new Iterator<ResultSet>() {
            @Override
            public boolean hasNext() {
                try {
                    return resultSet.next();
                } catch (SQLException e) {
                    throw new RuntimeException("Error iterating over ResultSet", e);
                }
            }

            @Override
            public ResultSet next() {
                return resultSet;
            }
        };
    }
}
