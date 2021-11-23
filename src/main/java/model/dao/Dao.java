package model.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface Dao<T> {
	public void add(T object);

	public void delete(T object);

	public void deleteById(Object id);

	public T get(Object id);

	public ArrayList<T> listAll();

	public void update(T object, HashMap<String, Object> params);
}