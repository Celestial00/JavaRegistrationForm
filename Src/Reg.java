
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class Reg extends JFrame implements ActionListener{

    String userName;
    String Password;
    String gender;
    String email;
    String phone;
    String City;


    JLabel InvalidE, InvalidP;
    JLabel NotSel;
    JComboBox cb;
    JTextField User;
    JTextField Email;
    JPasswordField pass;
    JButton btn, clear;
    JRadioButton f, m;
    JTextField country;
    JCheckBox checkBox;

    Reg(){

        String[] cities = {"Hyderabad","Jamshro ", "karachi"};
       
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        InvalidP = new JLabel(" Only Numbers ");
        InvalidP.setForeground(Color.red);
        InvalidP.setVisible(false);
        InvalidP.setBounds(350, 350, 100, 25);

        InvalidE = new JLabel(" Must Contain @");
        InvalidE.setForeground(Color.red);
        InvalidE.setVisible(false);
        InvalidE.setBounds(350, 200, 125, 25);

        NotSel = new JLabel(" terms not accepted* ");
        NotSel.setForeground(Color.red);
        NotSel.setVisible(false);

        clear = new JButton("clear");
        clear.addActionListener(this);
        clear.setBounds(300, 450, 100, 25);

        btn = new JButton("Register");
        btn.addActionListener(this);
        btn.setBounds(190, 450, 100, 25);

        checkBox = new JCheckBox("Accept Terms and Conditions");
        checkBox.setBounds(190, 400, 230, 25);
        
        country = new JTextField(16);
        country.setBounds(190, 350, 100, 25);


        cb = new JComboBox(cities);
        cb.setBounds(190, 300, 100, 25);

        f = new JRadioButton("Female");
        f.setFocusable(false);
        f.setBounds(190, 250, 100, 25);
        m = new JRadioButton("Male");
        m.setFocusable(false);
        m.setBounds(300, 250, 100, 25);
          
         ButtonGroup group = new ButtonGroup();
          group.add(f);
          group.add(m);
        
        NotSel.setBounds(30, 50, 180, 25);
        JLabel UserN = new JLabel("User name: ");
        UserN.setBounds(70, 100, 100, 25);
        JLabel PassN = new JLabel("User Password: ");
        PassN.setBounds(70, 150, 125 , 25);
        JLabel EmailN = new JLabel("Email ");
        EmailN.setBounds(70, 200, 125, 25);
        JLabel Gender = new JLabel("Gender: ");
        Gender.setBounds(70, 250, 125, 25);
        JLabel city = new JLabel("City ");
        city.setBounds(70, 300, 125, 25);
        JLabel Phone = new JLabel("Phone: ");
        Phone.setBounds(70, 350, 125, 25);
        JLabel Genders = new JLabel("Gender: ");
        Genders.setBounds(70, 250, 125, 25);

        User = new JTextField(16);
        User.setBounds(190, 100, 100, 25);
        pass = new JPasswordField(16);
        pass.setBounds(190, 150, 100, 25);
        Email = new JTextField(16);
        Email.setBounds(190, 200, 100, 25);
      
        this.add(InvalidP);
        this.add(InvalidE);
        this.add(NotSel);
        this.add(Phone);  
        this.add(city);
        this.add(Genders);
        this.add(btn);
        this.add(clear);
        this.add(checkBox);
        this.add(country);
        this.add(cb);
        this.add(f);
        this.add(m);
        this.add(EmailN);
        this.add(Email);
        this.add(UserN);
        this.add(PassN);
        this.add(User);
        this.add(pass);
        this.setVisible(true);


    }

    public void DataStore(){


        try{

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registeration", "root", "");
        String sql = "insert into Register(name, email, pass, gender, phone, city)values(?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, userName);
        pst.setString(2, email);
        pst.setString(3, gender);
        pst.setString(4, phone);
        pst.setString(5, City );
        pst.executeUpdate();



        }
        catch(Exception e){

            e.printStackTrace();
        }



    }

    public void CheckGender(){


      if(f.isSelected()){

        gender = "F";

      }

      if(m.isSelected()){

        gender = "M";

      }


    }

    public Boolean CheckPhone(){

         phone = country.getText();

        if(phone.matches("[0-9]+")){

            return true;

        }

        else{

            return false;
        }


    }

  

    public Boolean CheckEmail(){

        String email = Email.getText();
        
        if(email.contains("@") == true){

            return true;

        }

        else{

            return false;

        }
         


    }



    @Override
    public void actionPerformed(ActionEvent e) {

    
        
        if(checkBox.isSelected() == false){

           NotSel.setVisible(true);

        }




        if(e.getSource() == btn){

              CheckGender();
             userName = User.getText();
             Password = String.valueOf(pass.getPassword());
             email = Email.getText();
             phone = country.getText();
             City = (String)cb.getSelectedItem();

            if(CheckEmail() == true){

                InvalidE.setVisible(false);

            }

            else{


                InvalidE.setVisible(true);
                
            }

            if(CheckPhone() == true){

                InvalidP.setVisible(false);

            }

            else{



                InvalidP.setVisible(true);
            
            }
            
            
             

        }

        if(e.getSource() == clear){
            
          InvalidE.setVisible(false);
          InvalidP.setVisible(false);  
          NotSel.setVisible(false); 
          User.setText("");
          pass.setText("");
          Email.setText("");
          country.setText("");            

        }


        
    }
    



}
