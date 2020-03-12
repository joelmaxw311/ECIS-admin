/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis.admin;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author joelm
 */
public abstract class PromptDialog <T> extends JDialog {
    
    private boolean confirmed = false;
    
    public PromptDialog() {
        super((Window)null);
        setModal(true);
    }
    
    protected void setConfirmed(boolean value) {
        this.confirmed = value;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    protected void submit() {
        setConfirmed(true);
        setVisible(false);
    }
    
    protected void cancel() {
        setConfirmed(false);
        setVisible(false);
    }
    
    /***
     * Dispose the prompt when it is no longer needed
     */
    public void free() {
        this.dispose();
    }
    
    public abstract T getResult();

}
