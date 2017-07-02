package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import next.model.User;

public class UserDaoTest {
	private UserDao userDao;

	@Before
	public void setUp() {
		userDao = new UserDao();
	}

	@Test
	public void createTest() throws Exception {
		User expected = new User("userId2", "password", "name", "javajigi@email.com");
		userDao.insert(expected);

		User actual = userDao.findByUserId(expected.getUserId());
		assertEquals(expected, actual);
	}

	@Test
	public void findAllTest() throws Exception {
		List<User> userList = userDao.findAll();
		assertTrue(!CollectionUtils.isEmpty(userList));
	}

	@Test
	public void UpdateTest() throws Exception {
		User updateUser = new User("userId", "password2", "name2", "javajigi@email.com");
		userDao.update(updateUser);

		User user = userDao.findByUserId("userId");
		assertEquals(updateUser, user);
	}

	@Test
	public void test() {
		String abc = new String("abc").intern();
		String abc1 = "abc";
		
		assertTrue(abc == abc1);
	}
}
