import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.Font.createFont;

public class ListaTarefas extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponentPanel;
    public ListaTarefas(){
        super("Lista de Tarefas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(ConstantesComum.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponent();
    }

    private void addGuiComponent(){
        JLabel bannerLabel = new JLabel("Lista de Tarefas!");
        bannerLabel.setFont(createFont("resources/LEMONMILK-Light.otf", 30f));
        bannerLabel.setBounds(
                (ConstantesComum.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
                15,
                ConstantesComum.BANNER_SIZE.width,
                ConstantesComum.BANNER_SIZE.height
        );

        taskPanel = new JPanel();

        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, ConstantesComum.TASKPANEL_SIZE.width, ConstantesComum.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(ConstantesComum.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy((JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        //velocidade do scroll
        JScrollBar verticalScrollbar = scrollPane.getVerticalScrollBar();
        verticalScrollbar.setUnitIncrement(20);

        //Botao adicionar tarefas
        JButton addTaskButton = new JButton("Adicionar Tarefa");
        addTaskButton.setFont(createFont("resources/LEMONMILK-Light.otf", 16f));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(-5, ConstantesComum.GUI_SIZE.height - 88,
                ConstantesComum.ADDTASK_BUTTON_SIZE.width, ConstantesComum.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource, float size) {
        String filePath = getClass().getClassLoader().getResource(resource).getPath();

        if(filePath.contains("%20")){
            filePath = getClass().getClassLoader().getResource(resource).getPath().replaceAll("%20", " ");
        }

        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        }
        catch(Exception e){
            System.out.println("Error na criação da fonte: " + e);
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Adicionar Tarefa")){
            ComponenteDaTask componenteTask = new ComponenteDaTask(taskComponentPanel);
            taskComponentPanel.add(componenteTask);

            if(taskComponentPanel.getComponentCount() > 1){
                ComponenteDaTask previousTask = (ComponenteDaTask) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            componenteTask.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
