package model.Bean;

import java.sql.SQLException;
import java.util.Collection;

public interface UserModel {
	public boolean doSave(UserBean product) throws SQLException;

	public boolean doDelete(String email) throws SQLException;

	public UserBean doRetrieveByKey(String email) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException;
}