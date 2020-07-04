package gamer;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gamer extends JFrame{
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel boardPanel;
    private JPanel boardPaneA;
    private JPanel boardPaneB;
    private JPanel pointsPanel;
    private JButton btnTime;
    private JButton btnSetCurrent;
    private JButton btnTeamA;
    private JButton btnTeamB;
    private JButton btnSetsA;
    private JButton btnSetsB;
    private JButton btnPointA;
    private JButton btnPointB;
    private JButton btnAplus;
    private JButton btnAminus;
    private JButton btnBplus;
    private JButton btnBminus;
    private JTextPane btnHistory;
    private int setCounter;
    private int setCounterA;
    private int setCounterB;
    private int pointsA;
    private int pointsB;
    private StringBuilder history;

    public Gamer() {
        setTitle("GAME BOARD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setSize(400, 450);
        init();
    }

    private void init() {
        
        mainPanel = new JPanel(new GridLayout(4, 1));
        
        titlePanel = new JPanel(new FlowLayout());
        
        boardPanel = new JPanel(new GridLayout(1, 2));
        boardPaneA = new JPanel(new GridLayout(3, 1));
        boardPaneB = new JPanel(new GridLayout(3, 1));

        pointsPanel = new JPanel(new GridLayout(2, 2));
        
        btnTime = new JButton();
        btnSetCurrent = new JButton();
        
        btnTeamA = new JButton();
        btnTeamB = new JButton();
        
        btnSetsA = new JButton();
        btnSetsB = new JButton();

        pointsA = 0;
        pointsB = 0;
        btnPointA = new JButton();
        btnPointB = new JButton();

        btnAplus = new JButton("(+) 1 point");
        btnAminus = new JButton("(-) 1 point");
        btnBplus = new JButton("(+) 1 point");
        btnBminus = new JButton("(-) 1 point");
        
        btnHistory = new JTextPane();

        titlePanel.add(btnTime);
        titlePanel.add(btnSetCurrent);
                
        boardPaneA.add(btnTeamA);
        boardPaneA.add(btnSetsA);
        boardPaneA.add(btnPointA);
        boardPaneB.add(btnTeamB);
        boardPaneB.add(btnSetsB);
        boardPaneB.add(btnPointB);
        
        boardPanel.add(boardPaneA);
        boardPanel.add(boardPaneB);
        
        pointsPanel.add(btnAplus);
        pointsPanel.add(btnBplus);
        pointsPanel.add(btnAminus);
        pointsPanel.add(btnBminus);
        
        mainPanel.add(titlePanel);
        mainPanel.add(boardPanel);
        mainPanel.add(pointsPanel);
        mainPanel.add(btnHistory);
  
        add(mainPanel);
        
        settings();
        
    }

    public int getSetCounterA() {
		return setCounterA;
	}

	public void setSetCounterA(int setCounterA) {
		this.setCounterA = setCounterA;
	}

	public int getSetCounterB() {
		return setCounterB;
	}

	public void setSetCounterB(int setCounterB) {
		this.setCounterB = setCounterB;
	}

	private void settings() {
        btnAplus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculus(1);
            }
        });

        btnAminus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculus(2);
            }
        });

        btnBplus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculus(3);
            }
        });

        btnBminus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculus(4);
            }
        });
        
        int n = JOptionPane.showConfirmDialog(null, "Deseja iniciar nova Partida?");
        if(n == 0){
            btnTime.setText("Hora do jogo: "+JOptionPane.showInputDialog(null, "Informe o horário do jogo"));
            btnTeamA.setText("Time: "+JOptionPane.showInputDialog(null, "Informe o nome do primeiro time"));
            btnTeamB.setText("Time: "+JOptionPane.showInputDialog(null, "Informe o nome do segundo time"));
            
            setCounter = 1;
            setCounterA = 0;
            setCounterB = 0;
            history = new StringBuilder("");
            
            setVisible(true);
            renderBoard();
        }
    }

    private void calculus(int n) {
        switch (n) {
            case 1:
                pointsA++;
                break;
            case 2:
                pointsA--;
                break;
            case 3:
                pointsB++;
                break;
            case 4:
                pointsB--;
                break;
        }
        checker();
        renderBoard();
    }

    private void checker() {
        if (setCounter < 5) {
            if ((pointsA >= 25 && pointsB <= (pointsA - 2))) {
                validade(1);
            } else if ((pointsB >= 25 && pointsA <= (pointsB - 2))) {
                validade(2);
            }
        } else {
            if ((pointsA >= 15 && pointsB <= (pointsA - 2))) {
                validade(1);
            } else if ((pointsB >= 15 && pointsA <= (pointsB - 2))) {
                validade(2);
            }
        }

        renderBoard();
    }

    public void validade(int x) {
        int n = JOptionPane.showConfirmDialog(null, "Confirmar término de SET");
        if (n == 0) {
            if (x == 1) {
                setCounterA++;
            } else if (x == 2) {
                setCounterB++;
            }
            history.append("Set "+setCounter+" "+btnTeamA.getText()+": "+pointsA+" X "+
                    " "+btnTeamB.getText()+": "+pointsB+ "\n");
            
            pointsA = 0;
            pointsB = 0;
            setCounter++;
        }

        if (setCounterA == 3 || setCounterB == 3) {
            n = JOptionPane.showConfirmDialog(null, "Confirmar término de jogo");
            if (n == 0 && setCounterA == 3) {
                JOptionPane.showMessageDialog(null, "TEAM A WINNER");
            } else if (n == 0 && setCounterB == 3) {
                JOptionPane.showMessageDialog(null, "TEAM B WINNER");
            }
            settings();
        }
    }

    private void renderBoard() {
        btnSetCurrent.setText("Current Set: " + setCounter);
        btnSetsA.setText("won sets: " + setCounterA);
        btnSetsB.setText("won sets: " + setCounterB);

        btnPointA.setText("Points: " + pointsA);
        btnPointB.setText("Points: " + pointsB);
        
        String text = history.toString();
        btnHistory.setText(text);

    }	

}
