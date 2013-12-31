/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package com.rti.xihui.fromscratch.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* 
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class SimpleGUI extends JPanel
                        implements ActionListener {
    protected JButton b1;
    
    private Runnable runnableOnExit;
    
    private JFrame frame;

    private SimpleGUI(JFrame frame, Runnable runnableOnExit) {
      
    	this.runnableOnExit = runnableOnExit;
    	this.frame = frame;
    	
        b1 = new JButton("Exit");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("exit");      

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
      
        add(b1);

    }

    public void actionPerformed(ActionEvent e) {
        runnableOnExit.run();
        frame.setVisible(false);
        frame.dispose();
    }


  
    public static JPanel createGUI(final String name, final Runnable runnableOnExit){
    	//Create and set up the window.
        final JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        final SimpleGUI newContentPane = new SimpleGUI(frame, runnableOnExit);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                newContentPane.setOpaque(true); //content panes must be opaque
                frame.setContentPane(newContentPane);
                frame.addWindowListener(new WindowAdapter() {
                	@Override
                	public void windowClosing(WindowEvent e) {
                		runnableOnExit.run();
                	}
				});

                //Display the window.
//                frame.pack();
                frame.setSize(200, 100);
                frame.setVisible(true);
            }
        });
        return newContentPane;
    }
}