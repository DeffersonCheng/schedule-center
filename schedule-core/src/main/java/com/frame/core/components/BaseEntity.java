package com.frame.core.components;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author DeffersonCheng
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 主键ID自动生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    protected Integer version;

    /**
     * 创建时间
     */
    @Column(name = "create_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createDateTime;
    
    /**
     * 最后修改时间
     */
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date updateDateTime;
    
    /**
     * 用于鉴别初始化数据
     */
    @Column(name = "init_id")
    private Long initId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    @Override
    public boolean equals(Object obj) {
    	if (obj!=null&&obj instanceof BaseEntity){
    		if (id==null) return ((BaseEntity)obj).getId()==null;
    		return id.equals(((BaseEntity)obj).getId());
    	}else
    		return false;
    }
    
    @Override
    public int hashCode() {
        return id==null? -1: id.hashCode();
    }
}
