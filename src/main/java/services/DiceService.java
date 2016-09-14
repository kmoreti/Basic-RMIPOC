package services;

import interfaces.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Dice service
 */
public class DiceService implements Service {

    JComboBox numOfDice;
    DicePanel [] dices;

    public JPanel getGuiPanel() {
        JPanel mainPanel = new JPanel();
        JPanel optionPanel = new JPanel();
        JPanel dicePanel = new JPanel();
        JButton button = new JButton("Roll' em!");
        String [] choices = {"1", "2", "3", "4", "5"};
        dices = new DicePanel [choices.length];
        numOfDice = new JComboBox(choices);
        button.addActionListener(new RollEmListener());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        optionPanel.add(numOfDice);
        optionPanel.add(button);
        mainPanel.add(optionPanel);
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new DicePanel();
            dices[i].setVisible(false);
            dicePanel.add(dices[i]);
        }
        mainPanel.add(dicePanel);
        return mainPanel;
    }

    public class RollEmListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            for (DicePanel dice:dices) {
                dice.setVisible(false);
            }
            String diceOutput = "";
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.valueOf(selection);
            for (int i = 0; i < numOfDiceToRoll; i++) {
                int r = (int) ((Math.random() * 6) + 1);
                dices[i].setNum(r);
                dices[i].repaint();
                dices[i].setVisible(true);
            }
        }
    }

    public class DicePanel extends JPanel {
        private int num;

        public Dimension getPreferredSize() {
            return new Dimension(100, 100);
        }
        public void paintComponent (Graphics g) {
            g.setColor(new Color(250,250,250));
            g.fillRect(0,0,100,100);

            g.setColor(new Color(0,0,0));

            switch (num) {
                case 5:
                    g.fillOval(15, 14, 20, 20);
                    g.fillOval(65, 70, 20, 20);
                case 3:
                    g.fillOval(65, 14, 20, 20);
                    g.fillOval(15, 70, 20, 20);
                case 1:
                    g.fillOval(40, 42, 20, 20);
                    break;
                case 6:
                    g.fillOval(15, 42, 20, 20);
                    g.fillOval(65, 42, 20, 20);
                case 4:
                    g.fillOval(15, 14, 20, 20);
                    g.fillOval(65, 70, 20, 20);
                case 2:
                    g.fillOval(65, 14, 20, 20);
                    g.fillOval(15, 70, 20, 20);
                    break;
                default:
                    break;
            }
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
