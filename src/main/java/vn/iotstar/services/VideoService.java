package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Video;

public class VideoService implements IVideoService {
	IVideoDao videoDao = new VideoDao();

	@Override
	public void insert(Video video) {
		videoDao.insert(video);

	}

	@Override
	public void update(Video video) {
		try {
			videoDao.update(video);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while updating video");
		}

	}

	@Override
	public void delete(String videoId) throws Exception {
		try {
			videoDao.delete(videoId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting video");
		}

	}

	@Override
	public Video findById(String videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public List<Video> findByVideoTitle(String title) {
		return videoDao.findByVideoTitle(title);
	}

	@Override
	public List<Video> findAll(int page, int pageSize) {
		return videoDao.findAll(page, pageSize);
	}

	@Override
	public int count() {
		return videoDao.count();
	}

}
