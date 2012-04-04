/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReversiPanel.java
 *
 * Created on Mar 19, 2011, 4:01:07 PM
 */

package com.luugiathuy.games.reversi;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

/**
 *
 * @author luugiathuy
 */
public class ReversiPanel extends javax.swing.JPanel implements Observer{

	private static final long serialVersionUID = -1506476411269777398L;
	/** the board */
	private Board mBoard;
	
    /** Creates new form ReversiPanel */
    public ReversiPanel() {
        initComponents();
        
        init();
    }
    
    /** Add board */
    private void init() {
    	mBoard = new Board();
		this.add(mBoard, BorderLayout.CENTER);
		
		Reversi.getInstance().addObserver(this);
		Reversi.getInstance().newGame();
    }
    
    /** Sets status message */
    public void setMessage(String msg) {
    	lblStatus.setText(msg);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        moveListPanel = new javax.swing.JPanel();
        moveListScroller = new javax.swing.JScrollPane();
        jMoveList = new javax.swing.JList();
        lblMoveList = new javax.swing.JLabel();

        setForeground(new java.awt.Color(242, 17, 39));
        setLayout(new java.awt.BorderLayout());

        lblStatus.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblStatus.setText("lblStatus");
        add(lblStatus, java.awt.BorderLayout.SOUTH);

        moveListPanel.setLayout(new java.awt.BorderLayout());

        moveListScroller.setPreferredSize(new java.awt.Dimension(100, 130));

        jMoveList.setFont(new java.awt.Font("Verdana", 0, 16));
        jMoveList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        moveListScroller.setViewportView(jMoveList);

        moveListPanel.add(moveListScroller, java.awt.BorderLayout.EAST);

        lblMoveList.setText("Move List:");
        moveListPanel.add(lblMoveList, java.awt.BorderLayout.NORTH);

        add(moveListPanel, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jMoveList;
    private javax.swing.JLabel lblMoveList;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel moveListPanel;
    private javax.swing.JScrollPane moveListScroller;
    // End of variables declaration//GEN-END:variables

	@Override
	public void update(Observable arg0, Object arg1) {
		Reversi reversiGame = Reversi.getInstance();
		jMoveList.setListData(reversiGame.getMoveList());
		jMoveList.setSelectedIndex(reversiGame.getMoveList().size() - 1);
		switch (reversiGame.getGameState()) {
		case Reversi.PLAYING:
			setMessage("White " + reversiGame.getWhiteScore() + " - " + reversiGame.getBlackScore() + " Black");
			mBoard.repaint();
			break;
		case Reversi.ENDED:
			String msg = "";
			int blackScore = reversiGame.getBlackScore();
			int whiteScore = reversiGame.getWhiteScore();
			if (blackScore > whiteScore)
				msg = "Black win: " + blackScore + " - " + whiteScore;
			else if (blackScore < whiteScore)
				msg = "White win: " + whiteScore + " - " + blackScore;
			else
				msg = "Draw: " + blackScore + " - " + whiteScore;
			JOptionPane.showMessageDialog(this, msg);
			break;
		}
		
	}

}