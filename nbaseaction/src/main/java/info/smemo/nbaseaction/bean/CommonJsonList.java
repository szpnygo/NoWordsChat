package info.smemo.nbaseaction.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonJsonList<T> implements Serializable {

    public int code;
    public String message;
    public ArrayList<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

}
