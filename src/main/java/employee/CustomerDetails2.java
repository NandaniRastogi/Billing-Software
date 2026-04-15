package employee;

import dbconnection.DbConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import dbconnection.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDetails2 extends JFrame {

    HashMap<String, ArrayList<String>> hm;
  Timer timer = null;
    JTable table;
    JTextField phoneField, nameField, emailField;
    JRadioButton male, female;
    JComboBox<String> paymentBox;
    JButton billBtn;
    String customerName="";

    public CustomerDetails2(HashMap hm) {
       
        this.hm = hm;
        
         setContentPane(new JLabel(new ImageIcon(getClass().getResource("/image/blue2.jpg"))));
setLayout(null);
        

        setTitle("Customer Details");
        setSize(1000, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Sr No.", "Id No.", "Name", "Price", "Quantity", "Total Price"}
        ));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 80, 550, 400);
        add(sp);

        // Labels
        JLabel title = new JLabel("Customer Details");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(650, 20, 300, 40);
        add(title);

        JLabel phoneLabel = new JLabel("Phone No.");
        phoneLabel.setBounds(620, 100, 100, 30);
        add(phoneLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(620, 150, 100, 30);
        add(nameLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(620, 200, 100, 30);
        add(emailLabel);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(620, 250, 100, 30);
        add(genderLabel);

        JLabel paymentLabel = new JLabel("Payment Mode");
        paymentLabel.setBounds(620, 320, 120, 30);
        add(paymentLabel);

        // TextFields
        phoneField = new JTextField();
        phoneField.setBounds(730, 100, 200, 30);
        add(phoneField);

        nameField = new JTextField();
        nameField.setBounds(730, 150, 200, 30);
        add(nameField);

        emailField = new JTextField();
        emailField.setBounds(730, 200, 200, 30);
        add(emailField);

        // Radio Buttons
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(730, 250, 80, 30);
        female.setBounds(820, 250, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        add(male);
        add(female);

        // ComboBox
        paymentBox = new JComboBox<>(new String[]{
                "Cash Payment", "Card Payment", "Online Payment", "Bank Payment"
        });
        paymentBox.setBounds(750, 320, 180, 30);
        add(paymentBox);

        // Button
        billBtn = new JButton("Final Billing");
        billBtn.setBounds(700, 400, 150, 50);
        add(billBtn);

        // Load Table Data
        loadTable();

        // Button Action
        billBtn.addActionListener(e -> {
           
            String phone = phoneField.getText();
            String name = nameField.getText();
            customerName = name;
            String email = emailField.getText();

            String gender = male.isSelected() ? "Male" : "Female";
            String payment = paymentBox.getSelectedItem().toString();

            JOptionPane.showMessageDialog(this,
                    "Billing Done!\nName: " + name +
                            "\nPhone: " + phone +
                            "\nPayment: " + payment);
            
             getCustomerDetailsAndSave();
             new FinalBilling(hm,customerName).setVisible(true);
             setVisible(false);
        });

        setVisible(true);
        
                 phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyReleased(java.awt.event.KeyEvent evt) {
        checkCustomerExists(phoneField.getText().trim());
    }
});
                 


phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyReleased(java.awt.event.KeyEvent evt) {

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(500, e -> {
            checkCustomerExists(phoneField.getText().trim());
        });

        timer.setRepeats(false);
        timer.start();
    }
});
// Background first

    }

    
    public void checkCustomerExists(String phone) {

    if (phone.isEmpty()) {
        return;
    }

    try {
        Connection con = dbconnection.DbConnection.createDbConnection();

        String query = "SELECT * FROM customer_details WHERE phoneNo = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, phone);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // ✅ Customer found → autofill fields
            nameField.setText(rs.getString("name"));
            emailField.setText(rs.getString("email"));

            String gender = rs.getString("gender");
            if (gender.equalsIgnoreCase("Male")) {
                male.setSelected(true);
            } else {
                female.setSelected(true);
            }

            paymentBox.setSelectedItem(rs.getString("payment"));

        } else {
            // ❌ Customer not found → clear fields
            nameField.setText("");
            emailField.setText("");
            male.setSelected(false);
            female.setSelected(false);
            paymentBox.setSelectedIndex(0);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}




   

    // Load table data
    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        int serialNo = 1;

        if (hm != null) {
            for (String key : hm.keySet()) {
                ArrayList<String> al = hm.get(key);

                Object[] row = {
                        serialNo++,
                        key,
                        al.get(0),
                        al.get(1),
                        al.get(2),
                        al.get(3)
                };

                model.addRow(row);
            }
        }
    }
    
    
    public void getCustomerDetailsAndSave() {

    String phone = phoneField.getText().trim();
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String gender = male.isSelected() ? "Male" : "Female";
    String payment = paymentBox.getSelectedItem().toString();

    // Basic validation
    if (phone.isEmpty() || name.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all required fields");
        return;
    }

    
  DbConnection.saveToDatabase(phone, name, email, gender, payment);
}
    
    

  
}