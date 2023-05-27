import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ComponenteDaTask extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }

    private JPanel parentPanel;

    public ComponenteDaTask(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        //Task field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(ConstantesComum.TASKFIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(ConstantesComum.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);

        //Delete
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(ConstantesComum.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){ //Marcando que esta concluido
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText("<html><s>" + taskText + "</s></html>");
        }
        else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

        if(e.getActionCommand().equalsIgnoreCase("X")){
            //deletando o componente
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
