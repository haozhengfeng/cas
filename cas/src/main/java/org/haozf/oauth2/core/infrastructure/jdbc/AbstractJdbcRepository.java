package org.haozf.oauth2.core.infrastructure.jdbc;

import org.haozf.oauth2.core.domain.share.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 15-6-13
 *
 * @author Shengzhao Li
 */
public abstract class AbstractJdbcRepository implements Repository {


    @Autowired
    protected JdbcTemplate jdbc;

}
