package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDaoImpl;
import vn.iotstar.entity.Video;
import vn.iotstar.service.IVideoService;

public class VideoServiceImpl implements IVideoService {
	IVideoDao videodao = new VideoDaoImpl();
	@Override
	public int count() {
		return videodao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videodao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByVideoTitle(String vidname) {
		return videodao.findByVideoTitle(vidname);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public Video findById(int videoid) {
		return videodao.findById(videoid);
	}

	@Override
	public void delete(int videoid) throws Exception {
		videodao.delete(videoid);
		
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
	}

}
