package lanou.carhometwo.login;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/11/4.
 */
public class LoginUser extends BmobUser {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
