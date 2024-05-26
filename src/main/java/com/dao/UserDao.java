package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;

	public void addUser(UserBean userBean) {
		stmt.update("insert into user (firstName , lastName , email , password) values (?,?,?,?)",
				userBean.getFirstName(), userBean.getLastName(), userBean.getEmail(), userBean.getPassword());
	}

	public UserBean loginUser(UserBean userBean) {
		try {
			String emailQuery = "select * from user where email like ?";
			String email = userBean.getEmail();
			UserBean user = stmt.queryForObject(emailQuery, new BeanPropertyRowMapper<UserBean>(UserBean.class),
					new Object[] { email });
			if (user != null) {
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void deleteUser(Integer userId) {
		try {
			String deleteQuery = "delete from user where userId = ?";
			stmt.update(deleteQuery, userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean updateUser(Integer userId, UserBean userBean) {
		try {
			String updateQuery = "update user set firstName = ? ,  lastName = ? , email=? , password =? where userId = ?";
			int record = stmt.update(updateQuery, userBean.getFirstName(), userBean.getLastName(), userBean.getEmail(),
					userBean.getPassword(), userId);

			if (record != 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
