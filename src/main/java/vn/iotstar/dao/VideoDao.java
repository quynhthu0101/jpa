package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		EntityManager em = JpaConfig.getEntityManager();
		String jpql = "SELECT count(v) FROM Video v";
		Query query = em.createQuery(jpql);
		int count = ((Long) query.getSingleResult()).intValue();
		em.close();
		return count;
	}

	@Override
	public List<Video> findAll(int page, int pageSize) {
		EntityManager em = JpaConfig.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		List<Video> videos = query.getResultList();
		em.close();
		return videos;
	}

	@Override
	public List<Video> findByVideoTitle(String title) {
		EntityManager em = JpaConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		List<Video> videos = query.getResultList();
		em.close();
		return videos;
	}

	@Override
	public List<Video> findAll() {
		EntityManager em = JpaConfig.getEntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		List<Video> videos = query.getResultList();
		em.close();
		return videos;
	}

	@Override
	public Video findById(String videoId) {
		EntityManager em = JpaConfig.getEntityManager();
		Video video = em.find(Video.class, videoId);
		em.close();
		return video;
	}

	@Override
	public void delete(int videoId) throws Exception {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Video video = em.find(Video.class, videoId);
			if (video != null) {
				em.remove(video);
			} else {
				throw new Exception("Video not found");
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	@Override
	public void update(Video video) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(video);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	@Override
	public void insert(Video video) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(video);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

}
