package org.exemple;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.util.Vector;


public class CargoGUI extends JFrame {

    private JLabel idLabel, weightLabel, volumeLabel,coutText,coutLabel;
    private JTextField idField, weightField, volumeField;
    private JButton addButton, addCargoButton;
    private JTable merchandiseTable;
    private DefaultTableModel tableModel;
    private JTextField distanceField;
    private JPanel inputPanel, tablePanel, cargoPanel,coutPanel,coutTextPanel,panel;
    private JComboBox<String> typeField;
    private DefaultListModel<String> model;
    private JList<String> list;
    private Vector<Cargaison> cargaisons;

    public CargoGUI() {
        super("Cargo Management");
        cargaisons =new Vector<Cargaison>();


        coutLabel=new JLabel("Cout de cargaison");
        coutText=new JLabel("0.0");
        coutTextPanel=new JPanel();
        coutTextPanel.setPreferredSize(new Dimension(200,30));
        coutTextPanel.add(coutText);
        coutTextPanel.setBackground(Color.GRAY);


        idLabel = new JLabel("Num: ");
        weightLabel = new JLabel("Poids: ");
        volumeLabel = new JLabel("Volume: ");

        idField = new JTextField(10);
        weightField = new JTextField(10);
        volumeField = new JTextField(10);

        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Num");
        tableModel.addColumn("Poids");
        tableModel.addColumn("Volume");
        merchandiseTable = new JTable(tableModel);



        JLabel distanceLabel = new JLabel("Distance: ");
        JLabel typeLabel = new JLabel("Type: ");
        distanceField = new JTextField(10);
        typeField = new JComboBox<String>(new String[]{"aerienne","routiere"});

        addCargoButton = new JButton("Add Cargo");
        addCargoButton.addActionListener(new AddCargoButtonListener());



        inputPanel = new JPanel();
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(volumeLabel);
        inputPanel.add(volumeField);
        inputPanel.add(addButton);

        EmptyBorder panelBorder = new EmptyBorder(25, 25, 25, 25);


        cargoPanel = new JPanel();
        cargoPanel.setLayout(new GridLayout(3,1,5,5));
        cargoPanel.setBorder(panelBorder);
        cargoPanel.add(distanceLabel);
        cargoPanel.add(distanceField);
        cargoPanel.add(typeLabel);
        cargoPanel.add(typeField);
        cargoPanel.add(new JLabel());
        cargoPanel.add(addCargoButton);


        tablePanel = new JPanel();
        JLabel table=new JLabel();



        panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(panelBorder);
        panel.add(cargoPanel);

        JPanel addMarchandisePanel = new JPanel(new BorderLayout());
        addMarchandisePanel.add(inputPanel,BorderLayout.SOUTH);
        panel.add(addMarchandisePanel);




        model=new DefaultListModel<>();
        list=new JList<>(model);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            listValueChanged(e);
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);

        list.setLayoutOrientation(JList.VERTICAL);

        panel.add(scrollPane);
        panel.add(new JScrollPane(merchandiseTable) );

        coutPanel=new JPanel();
        coutPanel.add(coutLabel);
        coutPanel.add(coutTextPanel);

        panel.add(coutPanel);
        add(panel);

        setSize(1200, 500);
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (list.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null,"Please select Cargo");
            }
            else{
                try {
                    int id = Integer.parseInt(idField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    double volume = Double.parseDouble(volumeField.getText());
                    Marchandise marchandise = new Marchandise(id, weight, volume);
                    cargaisons.get(list.getSelectedIndex()).addMarchandise(marchandise);
                    tableModel.addRow(new Object[]{id, weight, volume});
                    setCoutText();
                }
                catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null,"invalid parameters");
                }
            }
                idField.setText("");
                weightField.setText("");
                volumeField.setText("");

        }
    }

    private class AddCargoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double distance = Double.parseDouble(distanceField.getText());
                String type = (String) typeField.getSelectedItem();
                if(type=="aerienne")
                    cargaisons.addElement(new CargaisonAerienne(distance));
                else
                    cargaisons.addElement(new CargaisonRoutiere(distance));

                model.addElement("cargo "+ type+" distance "+distance);
            }
            catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(null,"invalid parameters");
            }
            distanceField.setText("");
        }
    }
    public void setCoutText() {
        Cargaison cargaison = cargaisons.get(list.getSelectedIndex());
        if(cargaison instanceof CargaisonAerienne)
            coutText.setText(String.valueOf(((CargaisonAerienne) cargaison).cout()));
        else
            coutText.setText(String.valueOf(((CargaisonRoutiere) cargaison).cout()));
    }

        public void listValueChanged(ListSelectionEvent e) {
        tableModel.setRowCount(0);
        setCoutText();
            Cargaison cargaison = cargaisons.get(list.getSelectedIndex());
        Vector<Marchandise> marchandises = cargaison.getMarchandises();
            for (int i = 0; i < marchandises.size() ; i++) {
                tableModel.addRow(new Object[]{marchandises.get(i).getNum(), marchandises.get(i).getWeight(), marchandises.get(i).getVolume()});
            }
        }

    public static void main(String[] args) {
        CargoGUI gui = new CargoGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
