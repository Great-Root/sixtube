package video.service;

import java.util.ArrayList;

import model.VideoDTO;

public interface VideoService {

	public void getVideo(String vpath);

	ArrayList<VideoDTO> getVideoList();
	
	
}