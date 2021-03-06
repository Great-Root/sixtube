package video;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import member.Controller;
import member.ModifyMain;
import member.service.MemberServiceImpl;
import model.CommentDTO;
import model.VideoDTO;
import video.service.VideoService;
import video.service.VideoServiceImpl;

public class VideoListController implements Initializable {

	static Parent root;
	Parent reviseRoot;
	VideoStage vs;
	VideoService service;
	ModifyMain mom;

	@FXML Button btnShow;
	@FXML Button btnHide;
	@FXML AnchorPane paneSlide;
	MemberServiceImpl ms;
	TextField tf;

	Button btnCompl;

	CommentDTO seletedDTO;

	String inputValue;

	public void setRoot(Parent root) {
		VideoListController.root = root;
		paneSlide.setTranslateX(-180);
		btnShow.setVisible(true);
		btnHide.setVisible(false);
	}


	public void setReviseRoot(Parent reviseRoot) {
		this.reviseRoot = reviseRoot;
		btnCompl = (Button)reviseRoot.lookup("#btnCompl");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vs = new VideoStage();
		service = new VideoServiceImpl();
		mom=new ModifyMain();
	}

	public void click(MouseEvent e) {
		TableView<CommentDTO> tw = (TableView<CommentDTO>) e.getSource();
		seletedDTO = tw.getSelectionModel().getSelectedItem();
		paneSlide.setTranslateX(-180);
		btnShow.setVisible(true);
		btnHide.setVisible(false);

		if(seletedDTO != null) {
			btnRevDisable();
			btnDelDisable();
			((Button)root.lookup("#btnRev"+seletedDTO.getVnum())).setDisable(false);
			((Button)root.lookup("#btnDel"+seletedDTO.getVnum())).setDisable(false);
		} 
	}

	public void btnRevDisable() {
		//?????? button ?????? ????????????
		for(int i = 0; i < 3; i++) {
			((Button)root.lookup("#btnRev"+i)).setDisable(true);
		}
	}

	public void btnDelDisable() {
		//?????? button ?????? ????????????
		for(int i = 0; i < 3; i++) {
			((Button)root.lookup("#btnDel"+i)).setDisable(true);
		}
	}

	public void CompleteProc() {
		seletedDTO.setContent(new SimpleStringProperty(tf.getText()));
		String n = seletedDTO.getContent();
		if(n.length() < 50) {
			service.commentsRevise(seletedDTO);
			Controller.cs.exit(reviseRoot);
			Controller.cs.alert("?????????????????????.");
			setListView();
		}else {
			Controller.cs.alert("?????? ????????? ?????? ?????? ?????? 50???????????????. \n?????? ????????? ?????? ?????? : " + n.length()+ "?????????");
		}
	}

	public void reviseEnter() {
		seletedDTO.setContent(new SimpleStringProperty(tf.getText()));
		String n = seletedDTO.getContent();
		if(n.length() <= 50) {

			service.commentsRevise(seletedDTO);
			Controller.cs.exit(reviseRoot);
			Controller.cs.alert("?????????????????????.");
			setListView();
		}else {
			Controller.cs.alert("?????? ????????? ?????? ?????? ?????? 50???????????????. \n?????? ????????? ?????? ?????? : " + n.length()+ "?????????");
		}

	}

	public void setImg() {
		ArrayList<VideoDTO> videoList = service.getVideoList();
		for(VideoDTO video : videoList) {
			int id = video.getVnum();
			ImageView iv = (ImageView)root.lookup("#fxImg"+id);
			Label title = (Label) root.lookup("#labelTitle"+id);
			title.setText(video.getTitle());
			iv.setImage(new Image(video.getThpath()));
			iv.setUserData(video);
		}
	}

	public void setUserData(VideoDTO dto) {

	}

	public void imgClickProc(MouseEvent e) {
		if(e.getClickCount()>1) {
			service.getVideo((ImageView)e.getSource());
		}
	}

	public void onEnter(ActionEvent e) {   
		TextField tf = (TextField)e.getSource();
		inputValue = tf.getText(); 
		if(inputValue.length() <= 50) {
			tf.clear();
			String id =((TextField)e.getSource()).getId();
			char ch = id.charAt(id.length()-1);
			int vnum = Character.getNumericValue(ch);
			CommentDTO dto = new CommentDTO(0, Controller.lu.getUserId(),inputValue,vnum);
			service.sendComments(dto);
			setListView();
		}else {
			Controller.cs.alert("?????? ????????? ?????? ?????? ?????? 50???????????????. \n?????? ????????? ?????? ?????? : " + inputValue.length()+ "?????????");
		}
	}

	public void setListView() {
		for(int i = 0 ; i <3;i++) {
			TableView<CommentDTO> fxTable = (TableView<CommentDTO>)root.lookup("#fxTable"+i);
			((TextField)root.lookup("#fxComments"+i)).setPromptText("Comments ???????????????");
			ObservableList<CommentDTO> list = fxTable.getItems();
			list.clear();
			ArrayList<CommentDTO> arr = service.getCommentList(i);

			for(CommentDTO data : arr) { 
				list.add(data);
			}
			fxTable.setItems(list);
		}
		btnRevDisable();
		btnDelDisable();
	}

	public void changeCont(CommentDTO seleteddto) {

		this.seletedDTO = seleteddto;

		//revisecontent.fxml????????? textField???  ??????
		tf = (TextField) reviseRoot.lookup("#changeCont");
		//videolist.fxml ??? ?????? tableview??? content ?????? setTest??? ????????? tf??? ??????.
		tf.setText(seleteddto.getContent());



	}


	public void reviseProc() {
		if(Controller.lu.getUserId().equals(seletedDTO.getUserId())) {
			vs.showContentView(seletedDTO);
		}else {
			Controller.cs.alert("???????????? ????????? ???????????????.");
		}
	}

	//?????? ?????? ??????
	public void deleteProc() { 

		//????????? ????????? userId??? ?????? TableView Column??? Id?????? ??????
		if(Controller.lu.getUserId().equals(seletedDTO.getUserId())) {
			//true??? service class??? commentsDelete();???????????? click();??????????????? ??????????????? selectedNum(cnum)?????? ????????? ????????????.
			service.commentsDelete(seletedDTO.getCnum());
			Controller.cs.alert("?????????????????????.");
			setListView();
		}else {
			Controller.cs.alert("???????????? ????????? ???????????????.");
		}
	}

	
	public void showSlide(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(paneSlide);
		slide.setToX(0);
		slide.play();
		paneSlide.setTranslateX(-180);
		slide.setOnFinished((ActionEvent e) -> {
			btnShow.setVisible(false);
			btnHide.setVisible(true);
		});
	}

	public void hideSlide(MouseEvent event) {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.4));
		slide.setNode(paneSlide);
		slide.setToX(-180);
		slide.play();
		paneSlide.setTranslateX(0);
		slide.setOnFinished((ActionEvent e) -> {
			btnShow.setVisible(true);
			btnHide.setVisible(false);
		});
	}

	public void modifyProc() {
		try {
			mom.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnEntered() {
		btnCompl.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 25; -fx-border-width: 3");

	}

	public void btnExited() {
		btnCompl.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 25;");
	}



}
