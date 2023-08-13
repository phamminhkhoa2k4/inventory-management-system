package inventory.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ModelUser {



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
    
    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
    public ModelUser( String userName, ImageIcon avatar, String Position) {
        this.userName = userName;
        this.avatar = avatar;
        this.Position = Position;
    }

    public ModelUser() {
    }


    private String userName;
    private ImageIcon avatar;
    private String Position;

    
}
