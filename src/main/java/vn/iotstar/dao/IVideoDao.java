package vn.iotstar.dao;
import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public interface IVideoDao {
	int count();

    List<Video> findAll(int page, int pageSize);

    List<Video> findByVideoTitle(String title);

    List<Video> findAll();

    Video findById(String videoId);

    void delete(String videoId) throws Exception;

    void update(Video video);

    void insert(Video video);
}
