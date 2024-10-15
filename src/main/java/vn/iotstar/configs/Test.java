package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		User user = new User();
		user.setEmail("quynhthu@gmail.com");
		user.setUsername("quynhthu");
		user.setFullname("Phạm Quỳnh Thư");
		user.setPassword("hi");
		user.setAvatar("1728760984118.png");
		user.setPhone("0585281758");
		
		Role role = new Role();
		role.setRolename("USER");
		
		user.setRole(role);

		try {
			trans.begin();
			enma.persist(user);
			enma.persist(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}