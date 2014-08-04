/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webcrawler.manager;

/**
 *
 * @author jose
 */
public interface UIManager {
    
    public void setProgressBarValue(int val);
    
    public void showMessageDialog(String message, String dialogHeader);
    
    public void showErrorMessageDialog(String message, String dialogHeader);
    
    public void enableSearching();
    
    public void resetProgressBar();
    
    public void setUIFileInfo(String fileName, int length);
    
}
