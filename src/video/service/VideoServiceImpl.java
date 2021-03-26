package video.service;

import database.video.VideoDAO;
import database.video.VideoDAOImpl;
import javafx.scene.Parent;

public class VideoServiceImpl implements VideoService{
	VideoDAO dao = new VideoDAOImpl();
	Parent root;
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void imgView() {
		System.out.println("서비스에서 실행됩니다");
	}
}
