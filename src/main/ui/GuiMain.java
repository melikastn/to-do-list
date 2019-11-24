package ui;

import model.PriorityTask;
import model.RegularTask;
import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GuiMain {
    private JButton enterButton;
    private JPanel panel1;
    private JTextField input;
    private JLabel result;
    private JComboBox<String> dropDownOptions;
    private JLabel toDoListLabel;
    private JCheckBox urgency;
    private JCheckBox importance;
    private ToDoList toDoList = new ToDoList();
    private String enter = "press enter when done.";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public GuiMain() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String state = (String) dropDownOptions.getSelectedItem();
                updateToDoListDisplay();
                if (state.equals("add Regular Task")) {
                    makeTask();
                } else if (state.equals("add Priority task")) {
                    makePriorityTaks();
                } else if (state.equals("Check off Task")) {
                    int numItem = Integer.parseInt(input.getText());
                    toDoList.findAndCross(numItem);
                } else if (state.equals("save list")) {
                    saveList();
                } else if (state.equals("load list")) {
                    loadList();
                }
                updateToDoListDisplay();
            }

        });
        dropDownOptions.addItem(null);
        dropDownOptions.addItem("add Regular Task");
        dropDownOptions.addItem("add Priority task");
        dropDownOptions.addItem("Check off Task");
        dropDownOptions.addItem("save list");
        dropDownOptions.addItem("load list");
        dropDownOptions.setSelectedItem(null);
        OptionManager handler = new OptionManager();
        dropDownOptions.addItemListener(handler);

    }

    private void loadList() {
        try {
            toDoList.load(input.getText());
            JOptionPane.showMessageDialog(enterButton, "Loaded successfully");
            result.setText("What would you like to do next?");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(enterButton, "File not found!");
        }
    }

    private void makeTask() {
        Task task = new RegularTask(input.getText());
        toDoList.insert(task);
        result.setText("What would you like to do next?");
    }

    private void makePriorityTaks() {
        Task task = new PriorityTask(input.getText(),urgency.isSelected(),importance.isSelected());
        toDoList.insert(task);
        result.setText("What would you like to do next?");
    }

    private void saveList() {
        try {
            toDoList.save(input.getText());
            result.setText("What would you like to do next?");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            // technically never happens
        }
    }

    private void updateToDoListDisplay() {
        String display = toDoList.printAll();
        String listTitle = "Melika's To Do List :";
        toDoListLabel.setText("<html>" + listTitle + "<br/>" + display.replaceAll("<", "&lt;").replaceAll(">",
                "&gt;").replaceAll("\n", "<br/>") + "</html>");
    }

    private class OptionManager implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == dropDownOptions) {
                if (dropDownOptions.getSelectedItem().equals("add Regular Task")) {
                    result.setText("<html>What is the task?" + "<br/>" + enter + "<html>");
                }
                if (dropDownOptions.getSelectedItem().equals("add Priority task")) {
                    result.setText("<html>What is the task and its importance/urgency?" + "<br/>" + enter + "<html>");
                }
                if (dropDownOptions.getSelectedItem().equals("Check off Task")) {
                    result.setText("<html> What task would you like to check off?" + "<br/>" + enter + "<html>");
                }
                if (dropDownOptions.getSelectedItem().equals("save list")) {
                    result.setText("<html> name your file:" + "<br/>" + enter + "<html>");
                }
                if (dropDownOptions.getSelectedItem().equals("load list")) {
                    result.setText("<html> what file do you want to load?" + "<br/>" + enter + "<html>");
                }
            }
            updateToDoListDisplay();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Melika's To Do List!");
        frame.setContentPane(new GuiMain().panel1);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
