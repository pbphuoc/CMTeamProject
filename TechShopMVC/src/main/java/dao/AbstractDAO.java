package dao;

import mapper.AbstractMapper;

public abstract class AbstractDAO<T> {
	public AbstractMapper<T> mapper;	
}
