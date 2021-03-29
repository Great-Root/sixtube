package video.service;

import java.util.ArrayList;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import model.VideoDTO;
import video.VideoStage;

public class VideoServiceImpl implements VideoService{
	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	VideoStage vs = new VideoStage(); 
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void getVideo(String vpath) {
		vs.showVideoView(vpath);
	}
	@Override
	public ArrayList<VideoDTO> getVideoList() {
		return dao.getVideoList();
	}
}
