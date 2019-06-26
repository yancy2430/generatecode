package com.tdeado.generatecode.view;

import javax.swing.*;
import java.io.File;

public class SelectFolderView extends JFileChooser {
    private String mTitle;
    public SelectFolderView(String title) {
        mTitle = title;
        this.setDialogTitle(mTitle);
        this.setDialogType(JFileChooser.OPEN_DIALOG);
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = this.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File dir = this.getSelectedFile();
            System.out.println(dir.getAbsolutePath());
        }
    }
//    public selectCallback();


}
