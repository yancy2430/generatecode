package com.tdeado.generatecode.view;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JPanel {
    private int x;
    private int y;
    private int h;
    private int llw;
    private int tfw;

    public MyTextField(int x, int y, int h, int llw, int tfw) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.llw = llw;
        this.tfw = tfw;
        init();
    }
    public MyTextField(LayoutManager layout, int x, int y, int h, int llw, int tfw) {
        super(layout);
        this.x = x;
        this.y = y;
        this.h = h;
        this.llw = llw;
        this.tfw = tfw;
        init();
    }
    public void init(){
        JLabel label = new JLabel("数据库ip：");
        label.setBounds(0, 0, 100, h);
        this.add(label, BorderLayout.WEST);
        // 创建文本框对象
        TextField tf = new TextField();
        tf.setBounds(x+llw, h, 100, h);
        this.add(tf, BorderLayout.WEST);
    }


}
