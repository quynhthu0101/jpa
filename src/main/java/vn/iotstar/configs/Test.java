package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;

public class Test {
	public static void main(String[] args) {
		Category cate = new Category();

		try {
			//IVideoDao videoDao = new VideoDao();
			ICategoryDao cateDao = new CategoryDao();
			cateDao.delete(2);
			//videoDao.findByCategory(cate);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}