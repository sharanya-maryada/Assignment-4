/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInteface.Patient;

import business.StringVerifier;
import business.Patient;
import business.Person;
import business.PersonDirectory;
import business.VitalSign;
import business.VitalSignRecord;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.*;
import java.util.ArrayList;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author sharanya maryada
 */
public class ManageVitalSignsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VitalSignManagerJJPanel
     */
    private PersonDirectory personDirectory;
    private JPanel userProcessContainer;

    public ManageVitalSignsJPanel(JPanel userProcessContainer, PersonDirectory personDirectory) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.personDirectory= personDirectory;
        InputVerifier stringVerifier = new StringVerifier();
        searchBoxJTextField.setInputVerifier(stringVerifier);
        ArrayList<Person> personList = personDirectory.getPersonHistory();
        populatePatientsTable(personList);
        populateVitalSignTable(null);
    }

    private void populatePatientsTable(ArrayList<Person> personList) {
        DefaultTableModel model = (DefaultTableModel) viewPatientsJTable.getModel();
        model.setRowCount(0);
        if(personList.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No Persons found. Please add Persons", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (Person person : personList) {
            Object[] row = new Object[6];
            row[0] = person;
            row[1]= person.getAge();
            row[3]=person.getCity();
            row[4]=person.getCommunity();
            row[5]=person.gethouse();
            if(person.getPatient()!=null)
            {
                row[2] = person.getPatient().getPatientID();
            }
            else
            {
                row[2] = "Patient Not Created";
            }
            
            model.addRow(row);
        }
    }

    private void populateVitalSignTable(Person person) {

        DefaultTableModel model = (DefaultTableModel) viewVitalSignsJTable.getModel();
        model.setRowCount(0);
        if (person != null) {
            int patientAge = person.getAge();
            ArrayList<VitalSign> vitalSignList = person.getPatient().getVitalSignHistory().getHistory();

            if (vitalSignList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No vital signs found. Please add vital signs", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for (VitalSign vitalSign : vitalSignList) {
                Object[] row = new Object[2];
                row[0] = vitalSign;
                row[1] = VitalSignStatus(patientAge, vitalSign);
                model.addRow(row);
            }
        }
    }
    
    private void populateAbnoramlCommunityTable(HashMap<String,Integer> abnoramlList) {

        DefaultTableModel model = (DefaultTableModel) viewVitalSignsJTable1.getModel();
        model.setRowCount(0);
        if (abnoramlList != null) {
                    for(Map.Entry<String, Integer> entry : abnoramlList.entrySet()){
                        Object[] row = new Object[2];
                        row[0] = entry.getKey();
                        row[1] = entry.getValue();
                        model.addRow(row);
            System.out.println("Community:  " + entry.getKey()+ "Abnormal Count:   " + entry.getValue());
        }
        }
    }

    private String VitalSignStatus(int patientAge, VitalSign vitalSign) {
        String vitalSignStatus = "Normal";

        int respirationRate = vitalSign.getRespiratoryRate();
        int heartRate = vitalSign.getHeartRate();
        int bloodPressure = vitalSign.getBloodPressure();
        float weight = vitalSign.getWeight();

        /*Toddler*/
        if (patientAge >= 1 && patientAge <= 3) {
            if ((respirationRate < 20 || respirationRate > 30) /*Respiration Rate*/
                    || (heartRate < 80 || heartRate > 130) /*Heart Rate*/
                    || (bloodPressure < 80 || bloodPressure > 110) /*Blood Pressure*/
                    || (weight < 22 || weight > 31)) /*Weight*/ {
                vitalSignStatus = "Abnormal";
            }
        }
        /*Preschooler*/
        if (patientAge >= 4 && patientAge <= 5) {
            if ((respirationRate < 20 || respirationRate > 30)
                    || (heartRate < 80 || heartRate > 120)
                    || (bloodPressure < 80 || bloodPressure > 110)
                    || (weight < 31 || weight > 40)) {
                vitalSignStatus = "Abnormal";
            }
        }
        /*School Age*/
        if (patientAge >= 6 && patientAge <= 12) {
            if ((respirationRate < 20 || respirationRate > 30)
                    || (heartRate < 70 || heartRate > 110)
                    || (bloodPressure < 80 || bloodPressure > 120)
                    || (weight < 41 || weight > 92)) {
                vitalSignStatus = "Abnormal";
            }
        }
        /*Adolescent*/
        if (patientAge >= 13) {
            if ((respirationRate < 12 || respirationRate > 20)
                    || (heartRate < 55 || heartRate > 105)
                    || (bloodPressure < 110 || bloodPressure > 120)
                    || (weight < 110)) {
                vitalSignStatus = "Abnormal";
            }
        }
        return vitalSignStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayDetailsJButton = new javax.swing.JButton();
        editVitalSignsJButton = new javax.swing.JButton();
        addVitalSignsJButton = new javax.swing.JButton();
        deleteVitalSignJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewPatientsJTable = new javax.swing.JTable();
        backJButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewVitalSignsJTable = new javax.swing.JTable();
        viewVitalSignJButton = new javax.swing.JButton();
        refreshVitalSignsJButton = new javax.swing.JButton();
        searchBoxJTextField = new javax.swing.JTextField();
        searchPatientJButton = new javax.swing.JButton();
        refreshPatientsJButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        viewVitalSignsJTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(500, 700));
        setPreferredSize(new java.awt.Dimension(500, 700));

        displayDetailsJButton.setText("Display Details");
        displayDetailsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayDetailsJButtonActionPerformed(evt);
            }
        });

        editVitalSignsJButton.setText("Edit Vital Signs");
        editVitalSignsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVitalSignsJButtonActionPerformed(evt);
            }
        });

        addVitalSignsJButton.setText("Add Vital Sign");
        addVitalSignsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVitalSignsJButtonActionPerformed(evt);
            }
        });

        deleteVitalSignJButton.setText("Delete Vital Sign");
        deleteVitalSignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVitalSignJButtonActionPerformed(evt);
            }
        });

        viewPatientsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Patient Name", "Age", "Patient ID", "City", "Community", "House No."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(viewPatientsJTable);

        backJButton.setText("Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        viewVitalSignsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(viewVitalSignsJTable);
        if (viewVitalSignsJTable.getColumnModel().getColumnCount() > 0) {
            viewVitalSignsJTable.getColumnModel().getColumn(0).setResizable(false);
            viewVitalSignsJTable.getColumnModel().getColumn(1).setResizable(false);
        }

        viewVitalSignJButton.setText("View Vital Signs");
        viewVitalSignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewVitalSignJButtonActionPerformed(evt);
            }
        });

        refreshVitalSignsJButton.setText("Abnormal Status Count");
        refreshVitalSignsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshVitalSignsJButtonActionPerformed(evt);
            }
        });

        searchPatientJButton.setText("Search Patient");
        searchPatientJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientJButtonActionPerformed(evt);
            }
        });

        refreshPatientsJButton.setText("Refresh Patients");
        refreshPatientsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPatientsJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        viewVitalSignsJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Community", "Abnormal Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(viewVitalSignsJTable1);
        if (viewVitalSignsJTable1.getColumnModel().getColumnCount() > 0) {
            viewVitalSignsJTable1.getColumnModel().getColumn(0).setResizable(false);
            viewVitalSignsJTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshVitalSignsJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(backJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchPatientJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addVitalSignsJButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(displayDetailsJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchBoxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(refreshPatientsJButton))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewVitalSignJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editVitalSignsJButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteVitalSignJButton)))
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addVitalSignsJButton)
                            .addComponent(backJButton)
                            .addComponent(displayDetailsJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchPatientJButton)
                                .addComponent(refreshPatientsJButton))
                            .addComponent(searchBoxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewVitalSignJButton)
                            .addComponent(editVitalSignsJButton)
                            .addComponent(deleteVitalSignJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(refreshVitalSignsJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void viewVitalSignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewVitalSignJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewVitalSignsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        VitalSign vitalSign = (VitalSign) viewVitalSignsJTable.getValueAt(selectedRow, 0);
        ViewUpdateVitalSignsJPanel vuvsJPanel = new ViewUpdateVitalSignsJPanel(userProcessContainer, vitalSign, Boolean.FALSE);
        userProcessContainer.add("vuvsJPanel", vuvsJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_viewVitalSignJButtonActionPerformed

    private void editVitalSignsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVitalSignsJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewVitalSignsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        VitalSign vitalSign = (VitalSign) viewVitalSignsJTable.getValueAt(selectedRow, 0);

        ViewUpdateVitalSignsJPanel vuvsJPanel = new ViewUpdateVitalSignsJPanel(userProcessContainer, vitalSign, Boolean.TRUE);
        userProcessContainer.add("vuvsJPanel", vuvsJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_editVitalSignsJButtonActionPerformed

    private void addVitalSignsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVitalSignsJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewPatientsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Person person = (Person) viewPatientsJTable.getValueAt(selectedRow, 0);
        Patient patient= person.getPatient();
        if(patient!=null)
        {
            CreateVitalSignJPanel cvsJPanel = new CreateVitalSignJPanel(userProcessContainer, patient);
            userProcessContainer.add("cvsJPanel", cvsJPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Patient not created, Please create Patient first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addVitalSignsJButtonActionPerformed

    private void displayDetailsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayDetailsJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewPatientsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Person person = (Person) viewPatientsJTable.getValueAt(selectedRow, 0);
        Patient patient= person.getPatient();
        if(patient!=null)
        {
            populateVitalSignTable(person);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Patient not created, Please create Patient first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_displayDetailsJButtonActionPerformed

    private void deleteVitalSignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVitalSignJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = viewPatientsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.");
            return;
        }
        Person person = (Person) viewPatientsJTable.getValueAt(selectedRow, 0);
        Patient patient= person.getPatient();
        if(patient==null)
        {
            JOptionPane.showMessageDialog(this, "Patient not created, Please create Patient first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        selectedRow = viewVitalSignsJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        VitalSign vitalSign = (VitalSign) viewVitalSignsJTable.getValueAt(selectedRow, 0);

        int flag= JOptionPane.showConfirmDialog(this, "Are you sure want to remove?","Warning", JOptionPane.YES_NO_OPTION);
        if(flag==0)
        {
            patient.getVitalSignHistory().deleteVitalSign(vitalSign);
            refreshVialSigns();
        }
    }//GEN-LAST:event_deleteVitalSignJButtonActionPerformed

    private void refreshVitalSignsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshVitalSignsJButtonActionPerformed
        // TODO add your handling code here:
       HashMap<String,Integer> abnormalCountList = abnormalcount();
       populateAbnoramlCommunityTable(abnormalCountList);
       

    }//GEN-LAST:event_refreshVitalSignsJButtonActionPerformed

    private void searchPatientJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientJButtonActionPerformed
        // TODO add your handling code here:
        String key = searchBoxJTextField.getText().trim();
        if(key.length()==0)
        {
            JOptionPane.showMessageDialog(this, "Please enter key.","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        /*Storing searched patients in an array to display in table.*/
        ArrayList<Person> searchPatients = personDirectory.searchPatient(key);
        populatePatientsTable(searchPatients);
    }//GEN-LAST:event_searchPatientJButtonActionPerformed

    private void refreshPatientsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPatientsJButtonActionPerformed
        // TODO add your handling code here:
        searchBoxJTextField.setText("");
        populatePatientsTable(personDirectory.getPersonHistory());
    }//GEN-LAST:event_refreshPatientsJButtonActionPerformed
    
    
    private HashMap<String,Integer> abnormalcount() {
        HashMap<String,Integer> abnormalList  = new HashMap<>();
        System.out.println("I am here");
        ArrayList<Person> personsList = personDirectory.getPersonHistory();
        for(Person p : personsList){
           Patient patient = p.getPatient();
           if(patient!= null){
                System.out.println(p.getPersonName());
                VitalSignRecord v= patient.getVitalSignHistory();
                if(v != null){
                    
                  ArrayList<VitalSign> vitalSignList = v.getHistory();
                    for(VitalSign vitalSign: vitalSignList){
                        System.out.println(VitalSignStatus(p.getAge(), vitalSign));
                        if(abnormalList.containsKey(p.getCommunity())){
                             Integer n = abnormalList.get(p.getCommunity());
                             n = n+1;
                            abnormalList.put(p.getCommunity(), n);
                        }else{
                            abnormalList.put(p.getCommunity(), 1);
                        }
                    }    
                }
                
           }
        }
        System.out.println(abnormalList.entrySet());
        for(Map.Entry<String, Integer> entry : abnormalList.entrySet()){
            System.out.println("Community:  " + entry.getKey()+ "Abnormal Count:   " + entry.getValue());
        }
        return abnormalList;
    }
        
    private void refreshVialSigns() {
        int selectedRow = viewPatientsJTable.getSelectedRow();
        if (selectedRow < 0) {
            populateVitalSignTable(null);
        } else {
            Person person = (Person) viewPatientsJTable.getValueAt(selectedRow, 0);
            Patient patient= person.getPatient();
            if(patient!=null)
            {
                populateVitalSignTable(person);
            }
            else
            {
                populateVitalSignTable(null);
            }
        }
    }

//    private void createChart()
//    {
//        DefaultCategoryDataset vitalSignDataset=new DefaultCategoryDataset();
//        int selectedRow = viewPatientsJTable.getSelectedRow();
//        Person person = (Person) viewPatientsJTable.getValueAt(selectedRow, 0);
//        Patient patient= person.getPatient();
//        if(patient==null)
//        {
//            JOptionPane.showMessageDialog(this, "Patient not created, Please create Patient first.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        ArrayList<VitalSign> vitalSignList = patient.getVitalSignHistory().getHistory();
//        /*At least 2 vital sign records needed to show chart */
//        if (vitalSignList.isEmpty() || vitalSignList.size() == 1) {
//            JOptionPane.showMessageDialog(this, "No vital signs or only one vital sign found. At least 2 vital sign records needed to show chart!", "Warning", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        for (VitalSign vitalSign : vitalSignList) {
//            vitalSignDataset.addValue(vitalSign.getRespiratoryRate(),"RR", vitalSign.getTimestamp());
//            vitalSignDataset.addValue(vitalSign.getHeartRate(),"HR", vitalSign.getTimestamp());
//            vitalSignDataset.addValue(vitalSign.getBloodPressure(),"BP", vitalSign.getTimestamp());
//            vitalSignDataset.addValue(vitalSign.getWeight(),"WT", vitalSign.getTimestamp());
//        }
//        
//        JFreeChart vitalSignChart= ChartFactory.createBarChart3D("Vital Sign Chart", "Time Stamp", "Rate", vitalSignDataset, PlotOrientation.VERTICAL, true, false, false);
//        vitalSignChart.setBackgroundPaint(Color.white);
//        CategoryPlot vitalSignChartPlot = vitalSignChart.getCategoryPlot();
//        vitalSignChartPlot.setBackgroundPaint(Color.lightGray);    
//        
//        CategoryAxis vitalSignDomainAxis = vitalSignChartPlot.getDomainAxis();
//        vitalSignDomainAxis.setCategoryLabelPositions(
//            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
//        );
//        
//        NumberAxis vitalSignRangeAxis = (NumberAxis) vitalSignChartPlot.getRangeAxis();
//        vitalSignRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        
//        ChartFrame chartFrame = new ChartFrame("Chart", vitalSignChart);
//        chartFrame.setVisible(true);
//        chartFrame.setSize(500, 500);
//
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVitalSignsJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton deleteVitalSignJButton;
    private javax.swing.JButton displayDetailsJButton;
    private javax.swing.JButton editVitalSignsJButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton refreshPatientsJButton;
    private javax.swing.JButton refreshVitalSignsJButton;
    private javax.swing.JTextField searchBoxJTextField;
    private javax.swing.JButton searchPatientJButton;
    private javax.swing.JTable viewPatientsJTable;
    private javax.swing.JButton viewVitalSignJButton;
    private javax.swing.JTable viewVitalSignsJTable;
    private javax.swing.JTable viewVitalSignsJTable1;
    // End of variables declaration//GEN-END:variables
}
