package org.haozf.oauth2.core.domain;

import java.io.Serializable;
import java.util.Date;

import org.haozf.common.util.DateUtils;

/**
 * @author Shengzhao Li
 */
public abstract class AbstractDomain implements Serializable {


    private static final long serialVersionUID = 7787898374385773471L;
    /**
     * The domain create time.
     */
    protected Date createTime = DateUtils.now();

    protected String guid;

    public AbstractDomain() {
    }

    public String guid() {
        return guid;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractDomain> T guid(String guid) {
        this.guid = guid;
        return (T) this;
    }

    public Date createTime() {
        return createTime;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractDomain> T createTime(Date createTime) {
        this.createTime = createTime;
        return (T) this;
    }


    @Override
    public String toString() {
        return "{" +
                "createTime=" + createTime +
                ", guid='" + guid + '\'' +
                '}';
    }
}