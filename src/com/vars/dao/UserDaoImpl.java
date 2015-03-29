package com.vars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.vars.domain.Developer;
import com.vars.domain.Tester;
import com.vars.domain.TestingRating;
import com.vars.domain.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	private static final String GET_USER = "select id, password, first_name, last_name, istester, linkedin_id, linkedin_url from user where username = ?";
	
	private static final String GET_USER_FOR_ID = "select username, password, first_name, last_name, istester, linkedin_id, linkedin_url from user where id = ?";
	
	private static final String GET_IN_USER = "select id, username, password, first_name, last_name, istester, linkedin_id, linkedin_url from user where username = ?";
	
	private static final String GET_DEVELOPER = "select id from developer where user_id = ?";
	
	private static final String GET_TESTER = "select id from tester where user_id = ?";
	
	private static final String GET_TESTER_FOR_ID = "select user_id from tester where id = ?";
		
	private static final String GET_RATING = "select developer_id, rating, ratingdate from tester_rating where tester_id = ?";
	
	private static final String GET_DEVELOPER_FOR_ID = "select user_id from developer where id = ?";
	
	private static final String GET_PROJECT = "select * from project where id = ?";
	
	private static final String INSERT_USER = "INSERT into user (username, password, first_name, last_name, istester, linkedin_id, linkedin_url) values (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_DEVELOPER = "INSERT into developer (user_id) values (?)";
	
	private static final String INSERT_TESTER = "INSERT into tester (user_id) values (?)";
	
	private static final String INSERT_BID = "INSERT into bid (project_id, tester_id, description, amount) values (?, ?, ?, ?)";

	private static final String UPDATE_USER = "UPDATE user set username =? , password =? ,  first_name=?, last_name = ? where id = ?";
	
	//private static final String UPDATE_DEVELOPER = "UPDATE developer set first_name =?, last_name=? where user_id = ?";
	
	//private static final String UPDATE_TESTER = "UPDATE tester set first_name =?, last_name =? where user_id =?";
	
	private static final String CHECK_LINKED_IN_USER = "select distinct(username) from user where lower(username) = lower(?)";

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getJdbcTemplate().update(UPDATE_USER, new Object[]{user.getUserName(),
				user.getPassword(),user.getFirstName(), user.getLastName(), user.getId()});
		int userId = getJdbcTemplate().queryForInt("select last_insert_id()");
		System.out.println("userID: "+userId);
	}
	
	public void createUser(User user) {
		// TODO Auto-generated method stub
		//Make DB call to insert user here
		getJdbcTemplate().update(INSERT_USER, new Object[]{user.getUserName(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), user.getIsTester() ? 1 : 0, user.getLinkedInId()
						,user.getLinkedInUrl()});
		int userId = getJdbcTemplate().queryForInt("select last_insert_id()");
		
		user.setId(userId);
		if(user.getIsTester()) {
			user.getTester().setUserId(userId);
			getJdbcTemplate().update(INSERT_TESTER, new Object[]{user.getId()} );
		} else {
			user.getDeveloper().setUserId(userId);
			getJdbcTemplate().update(INSERT_DEVELOPER, new Object[]{user.getId()} );
		}
		System.out.println("created userID: "+userId);
	}

	public User getUser(final String username) {
		System.out.println("username fo get user is " + username);
		User user = getJdbcTemplate().queryForObject(GET_USER, new Object[]{username}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(username);
				user.setPassword(rs.getString("password"));
				user.setIsTester(rs.getInt("istester") == 1 ? true : false);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLinkedInUrl(rs.getString("linkedin_url"));
				user.setLinkedInId(rs.getString("linkedin_id"));
				return user;
			}
		});
		
		System.out.println("getting tester for user if " + user.getId());
		if(user.getIsTester()) {
			Tester tester = getJdbcTemplate().queryForObject(GET_TESTER, new Object[]{user.getId()}, new RowMapper<Tester>(){
				@Override
				public Tester mapRow(ResultSet rs, int rowNum) throws SQLException {
					Tester tester = new Tester();
					tester.setId(rs.getInt("id"));
					//tester.setFirstName(rs.getString("first_name"));;
					//tester.setLastName(rs.getString("last_name"));
					//tester.setLinkedInUrl(rs.getString("linkedin_url"));
					return tester;
				}
			}); 
			user.setTester(tester);
		} else {
			Developer developer = getJdbcTemplate().queryForObject(GET_DEVELOPER, new Object[]{user.getId()}, new RowMapper<Developer>(){
				@Override
				public Developer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Developer developer = new Developer();
					developer.setId(rs.getInt("id"));
					//developer.setFirstName(rs.getString("first_name"));;
					//developer.setLastName(rs.getString("last_name"));
					//developer.setLinkedInUrl(rs.getString("linkedin_url"));
					return developer;
				}
			});
			user.setDeveloper(developer);
		}
		return user;
	}

	@Override
	public boolean checkInUser(String email) {
		boolean exists = false;
        List existingEmail = getJdbcTemplate().queryForList(
        		CHECK_LINKED_IN_USER, new Object[] { email }, String.class);
        if (!existingEmail.isEmpty()) {
            exists = true;
        }
        return exists;
	}

	@Override
	public User getInUser(String inId) {
		User user = getJdbcTemplate().queryForObject(GET_IN_USER, new Object[]{inId}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIsTester(rs.getInt("istester") == 1 ? true : false);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLinkedInUrl(rs.getString("linkedin_url"));
				user.setLinkedInId(rs.getString("linkedin_id"));
				return user;
			}
		});
		
		if(user.getIsTester()) {
			Tester tester = getJdbcTemplate().queryForObject(GET_TESTER, new Object[]{user.getId()}, new RowMapper<Tester>(){
				@Override
				public Tester mapRow(ResultSet rs, int rowNum) throws SQLException {
					Tester tester = new Tester();
					tester.setId(rs.getInt("id"));
					//tester.setFirstName(rs.getString("first_name"));;
					//tester.setLastName(rs.getString("last_name"));
					//tester.setLinkedInUrl(rs.getString("linkedin_url"));
					return tester;
				}
			}); 
			user.setTester(tester);
		} else {
			Developer developer = getJdbcTemplate().queryForObject(GET_DEVELOPER, new Object[]{user.getId()}, new RowMapper<Developer>(){
				@Override
				public Developer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Developer developer = new Developer();
					developer.setId(rs.getInt("id"));
					//developer.setFirstName(rs.getString("first_name"));;
					//developer.setLastName(rs.getString("last_name"));
					//developer.setLinkedInUrl(rs.getString("linkedin_url"));
					return developer;
				}
			});
			user.setDeveloper(developer);
		}
		return user;
	}

	@Override
	public Tester getTester(Integer id) {
		Tester tester = getJdbcTemplate().queryForObject(GET_TESTER_FOR_ID, new Object[]{id}, new RowMapper<Tester>(){
			@Override
			public Tester mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tester tester = new Tester();
				tester.setUserId((rs.getInt("user_id")));
				return tester;
			}
		});
		tester.setId(id);
		return tester;
	}

	@Override
	public User getUserForId(Integer id) {
		// TODO Auto-generated method stub
		//GET_USER_FOR_ID
		System.out.println("getUserForId"+ id);
		User user = getJdbcTemplate().queryForObject(GET_USER_FOR_ID, new Object[]{id}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIsTester(rs.getInt("istester") == 1 ? true : false);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLinkedInUrl(rs.getString("linkedin_url"));
				user.setLinkedInId(rs.getString("linkedin_id"));
				return user;
			}
		});
		
		if(user.getIsTester()) {
			Tester tester = getJdbcTemplate().queryForObject(GET_TESTER, new Object[]{id}, new RowMapper<Tester>(){
				@Override
				public Tester mapRow(ResultSet rs, int rowNum) throws SQLException {
					Tester tester = new Tester();
					tester.setId(rs.getInt("id"));
					return tester;
				}
			}); 
			user.setTester(tester);
		} else {
			Developer developer = getJdbcTemplate().queryForObject(GET_DEVELOPER, new Object[]{id}, new RowMapper<Developer>(){
				@Override
				public Developer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Developer developer = new Developer();
					developer.setId(rs.getInt("id"));
					return developer;
				}
			});
			user.setDeveloper(developer);
		}
		user.setId(id);
		return user;
		
	}

	@Override

	public ArrayList<TestingRating> getRatingForTester(Integer id) {
		List<TestingRating> testingRatings = getJdbcTemplate().query(GET_RATING,
				new Object[] { id }, new RowMapper<TestingRating>() {
					@Override
					public TestingRating mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TestingRating testingRating = new TestingRating();
						testingRating.setDeveloperId(rs.getInt("developer_id"));
						testingRating.setRating(rs.getFloat("rating"));
						testingRating.setDate(rs.getString("ratingdate"));
						return testingRating;
					}
				});

		return (ArrayList<TestingRating>) testingRatings;
	}

	public Developer getDeveloperForId(Integer id) {
		System.out.println("Getting developer for id"+ id);
		Developer developer = getJdbcTemplate().queryForObject(GET_DEVELOPER_FOR_ID, new Object[]{id}, new RowMapper<Developer>(){
			@Override
			public Developer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Developer developer = new Developer();
				developer.setUserId((rs.getInt("user_id")));
				return developer;
			}
		});
		developer.setId(id);
		return developer;
	}
}