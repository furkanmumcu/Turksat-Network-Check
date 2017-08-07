package com.turksat.networkcheck;
import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 31/07/2017.
 */

@ManagedBean//(name = "teditor")
public class TextEditor {

    private String text;

    private String text2;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
