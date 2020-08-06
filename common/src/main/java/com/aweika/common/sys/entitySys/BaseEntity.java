package com.aweika.common.sys.entitySys;


import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 2054813493011812469L;

	private T id;
	private Date createTime = new Date();
	private Date updateTime = new Date();

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaseEntity)) return false;

		BaseEntity<?> that = (BaseEntity<?>) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
