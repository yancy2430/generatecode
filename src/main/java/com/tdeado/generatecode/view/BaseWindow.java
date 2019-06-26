package com.tdeado.generatecode.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BaseWindow extends JFrame {
    private String mTitle;

    public BaseWindow(String title) throws HeadlessException {
        super(title);
        this.mTitle = title;
    }

    public BaseWindow() throws HeadlessException {
    }

    public BaseWindow(GraphicsConfiguration gc) {
        super(gc);
    }
    public BaseWindow(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.mTitle = title;
    }

    public BaseWindow init(int w,int h,int x,int y){
        //创建frame
        //调整frame的大小和初始位置
        this.setSize(w, h);
        this.setLocation(x, y);
        return this;
    }

    public void addTextField(int x, int y, int h, int llw, int tfw) throws IOException {
        JLabel label = new JLabel("数据库ip：");
        label.setBounds(x, y, llw, h);
        this.add(label);
        // 创建文本框对象
        TextField tf = new TextField();
        tf.setBounds(x+llw, h, tfw, h);
        this.add(tf);
    }
}
