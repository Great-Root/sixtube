package member;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyMain{

	public void deleteMember(){
		Stage stage=new Stage();

		FXMLLoader loader=new FXMLLoader(getClass().getResource("delete.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);

		ModifyController controller=loader.getController();
		controller.setRoot(root);

		stage.setScene(scene);
		stage.show();
	}



	public void start() throws Exception {

		Stage stage=new Stage();

		FXMLLoader loader=new FXMLLoader(getClass().getResource("modify.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);

		ModifyController controller=loader.getController();
		controller.setRoot(root);

		stage.setScene(scene);
		stage.show();
	}
}
