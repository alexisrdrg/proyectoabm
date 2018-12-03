import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TablaMoto extends JFrame {
	 private static final long serialVersionUID = 1L;
	 private JLabel labelResultado;
	 private JTextField dato2;
	 private JTextField dato1;
	 
	 
	  public static void main(String[] args) {
		    EventQueue.invokeLater(new Runnable() {
		      public void run() {
		        try {
		        	TablaMoto frame = new TablaMoto();
		        	frame.setVisible(true);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
		  }
	  		public TablaMoto() {
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setBounds(100, 100, 800, 900);
		    JPanel contentPane = new JPanel();
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    setContentPane(contentPane);
		    contentPane.setLayout(null);
	  		
		    String[] datos = {"Codigo de Marca","Codigo de Modelo","Marca","Modelo","Codigo","Grupo","Codigo de Area","Año","Valor","Valor Año","Valor 1","Valor 2","Valor 3","Valor 4","Valor 5","Valor 6","Valor 7","Valor 8","Valor 9","Valor 10","Valor 11","Valor 12","Valor 13","Valor 14","Valor 15","Valor 16","Valor 17","Valor 18","Valor 19"};
		    
		    int pos = 30;
		    JLabel[] lbs =new JLabel [29]; 
		    for (int i=0;i<29;i++)
		    		{
		    	     lbs[i] = new JLabel(""+datos[i]);
		    		 lbs[i].setBounds(23, pos, 150, 14);
				     contentPane.add(lbs[i]);
				     pos=pos+20;
		    		}
		    
		    int postext = 30;
		    JTextField [] text =new JTextField [29]; 
		    for (int i=0;i<29;i++)
		    		{
		    	     text[i] = new JTextField();
		    		 text[i].setBounds(247, postext, 193, 20);
				     contentPane.add(text[i]);
				     text[i].setColumns(10);
				     postext=postext+20;
		    		}
		    
		    dato1 = new JTextField();
		    dato1 .setBounds(450, 150, 150, 14);
		    contentPane.add(dato1);
		    dato1 .setColumns(10);
		    
		    dato2 = new JTextField();
		    dato2 .setBounds(450, 170, 150, 14);
		    contentPane.add(dato2);
		    dato2 .setColumns(10);
		    
		    
		    labelResultado = new JLabel("resultado");
		    labelResultado.setBounds(450, 50, 150, 14);
		    contentPane.add(labelResultado);
		    
		    JButton btnAlta = new JButton("Alta");
		    btnAlta.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent arg0) {
		        labelResultado.setText("");        
		        try {
		          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/pruaba","root" ,"password");
		          Statement comando=conexion.createStatement();
		          comando.executeUpdate("insert into Datos(MarCodInf,ModCodInf,MarDesInf,ModDesInf,InfCodigo,InfGroup,InfCodRea,InfAnio00,InfVal00,infValAnio00,InfVal01,InfVal02,InfVal03,InfVal04,InfVal05,InfVal06,InfVal07,InfVal08,InfVal09,InfVal10,InfVal11,InfVal12,InfVal13,InfVal14,InfVal15,InfVal16,InfVal17,InfVal18,InfVal19) values ('"+text[0].getText()+"','"+text[1].getText()+"','"+text[2].getText()+"','"+text[3].getText()+"','"+text[4].getText()+"','"+text[5].getText()+"','"+text[6].getText()+"','"+text[7].getText()+"','"+text[8].getText()+"','"+text[9].getText()+"','"+text[10].getText()+"','"+text[11].getText()+"','"+text[12].getText()+"','"+text[13].getText()+"','"+text[14].getText()+"','"+text[15].getText()+"','"+text[16].getText()+"','"+text[17].getText()+"','"+text[18].getText()+"','"+text[19].getText()+"','"+text[20].getText()+"','"+text[21].getText()+"','"+text[22].getText()+"','"+text[23].getText()+"','"+text[24].getText()+"','"+text[25].getText()+"','"+text[26].getText()+"','"+text[27].getText()+"','"+text[28].getText()+"')");
		          conexion.close();
		          labelResultado.setText("se registraron los datos");
		          for(int i=0;i<29;i++)
		          {
		        	  text[i].setText("");
		          }
		        	  

		        } catch(SQLException ex){
		          setTitle(ex.toString());
		        }
		      }
		    });
		    btnAlta.setBounds(450, 30, 150, 14);
		    contentPane.add(btnAlta);	    
		    
		    JButton borrar = new JButton("Borrar");
		    borrar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        labelResultado.setText("");
		        try {
		        	Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/pruaba","root" ,"password");
			          Statement comando=conexion.createStatement();
			          int cantidad = comando.executeUpdate("delete from Datos where MarCodInf= '"+text[0].getText()+"' AND ModCodInf= '"+text[1].getText()+"'  ");			          conexion.close();
		          if (cantidad==1) {      
		        	  
		        	  
		            labelResultado.setText("Se borro el artículo con dicho código");
			          for(int i=0;i<29;i++)
			          {
			        	  text[i].setText("");
			          }
		          } else {
		            labelResultado.setText("No existe un artículo con dicho código");
			          
		          }
		          conexion.close();
		        } catch(SQLException ex){
		          setTitle(ex.toString());
		        }                
		      }
		    });
		    borrar.setBounds(450, 70, 150, 14);
		    contentPane.add(borrar);
		    cargarDriver();
		    
		    
		    JButton modificar = new JButton("Modificar");
		    modificar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        labelResultado.setText("");
		        try {
			          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/pruaba","root" ,"password");
			          
			          String query ="update Datos set MarCodInf = ?, ModCodInf = ?, MarDesInf = ?, ModDesInf=?, InfCodigo=?, InfGroup=?, InfCodRea=?, infAnio00=?, InfVal00=?, InfValAnio00=?, InfVal01=?, InfVal02=?, InfVal03=?, InfVal04=?, InfVal05=?, InfVal06=?, InfVal07=?, InfVal08=?, InfVal09=?, InfVal10=?, InfVal11=?, InfVal12=?, InfVal13=?, InfVal14=?, InfVal15=?, InfVal16=?, InfVal17=?, InfVal18=?, InfVal19=? WHERE MarCodInf=? AND ModCodInf=?" ;
			          PreparedStatement datossql = conexion.prepareStatement(query);
			          datossql.setInt(1, Integer.parseInt(text[0].getText()));
			          datossql.setInt(2, Integer.parseInt(text[1].getText()));
			          datossql.setString(3, text[2].getText());
			          datossql.setString(4, text[3].getText());
			          datossql.setInt(5, Integer.parseInt(text[4].getText()));
			          datossql.setInt(6, Integer.parseInt(text[5].getText()));
			          datossql.setInt(7, Integer.parseInt(text[6].getText()));
			          datossql.setInt(8, Integer.parseInt(text[7].getText()));
			          int a=9, b=8;
			          for(int i=0;i<21;i++)
			          {
			        	  datossql.setDouble(a, Double.parseDouble(text[b].getText()));
			        	  a=a+1;
			        	  b=b+1;
			          }
			          datossql.setInt(30, Integer.parseInt(text[0].getText()));
			          datossql.setInt(31, Integer.parseInt(text[1].getText()));
			          
			          datossql.executeUpdate();
			          
			          
			          conexion.close();
		            labelResultado.setText("Se modifico la descripcion y el precio del artículo con dicho código");
		          conexion.close();
		        } catch(SQLException ex){
		          setTitle(ex.toString());
		        }                
		      }
		    });
		    modificar.setBounds(450, 90, 150, 14);
		    contentPane.add(modificar);
		    cargarDriver();
		  
		    JButton btnConsulta = new JButton("Consulta CodMarca y ModModelo");
		    btnConsulta.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent arg0) {
		        labelResultado.setText("");    
		        try {
			          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/pruaba","root" ,"password");
		          Statement comando=conexion.createStatement();
		          ResultSet registro = comando.executeQuery("select * from Datos where MarCodInf= '"+dato1.getText()+"' AND ModCodInf= '"+dato2.getText()+"'  ");
		          if (registro.next()==true) {
		        	  text[0].setText(registro.getString("MarCodInf"));
		        	  text[1].setText(registro.getString("ModCodInf"));
		        	  text[2].setText(registro.getString("MarDesInf"));
		        	  text[3].setText(registro.getString("ModDesInf"));
		        	  text[4].setText(registro.getString("InfCodigo"));
		        	  text[5].setText(registro.getString("InfGroup"));
		        	  text[6].setText(registro.getString("InfCodRea"));
		        	  text[7].setText(registro.getString("InfAnio00"));
		        	  text[8].setText(registro.getString("InfVal00"));
		        	  text[9].setText(registro.getString("InfValAnio00"));
		        	  text[10].setText(registro.getString("InfVal01"));
		        	  text[11].setText(registro.getString("InfVal02"));
		        	  text[12].setText(registro.getString("InfVal03"));
		        	  text[13].setText(registro.getString("InfVal04"));
		        	  text[14].setText(registro.getString("InfVal05"));
		        	  text[15].setText(registro.getString("InfVal06"));
		        	  text[16].setText(registro.getString("InfVal07"));
		        	  text[17].setText(registro.getString("InfVal08"));
		        	  text[18].setText(registro.getString("InfVal09"));
		        	  text[19].setText(registro.getString("InfVal10"));
		        	  text[20].setText(registro.getString("InfVal11"));
		        	  text[21].setText(registro.getString("InfVal12"));
		        	  text[22].setText(registro.getString("InfVal13"));
		        	  text[23].setText(registro.getString("InfVal14"));
		        	  text[24].setText(registro.getString("InfVal15"));
		        	  text[25].setText(registro.getString("InfVal16"));
		        	  text[26].setText(registro.getString("InfVal17"));
		        	  text[27].setText(registro.getString("InfVal18"));
		        	  text[28].setText(registro.getString("InfVal19"));
		          } else {
		            labelResultado.setText("No existe un artículo con dicho código");
			          for(int i=0;i<29;i++)
			          {
			        	  text[i].setText("");
			          }
		          }
		          conexion.close();
		        } catch(SQLException ex){
		          setTitle(ex.toString());
		        }
		      }
		    });
		    btnConsulta.setBounds(450, 120, 300, 14);
		    contentPane.add(btnConsulta);
		    
		    
	  		}
	  		private void cargarDriver() {
			    try {
			      Class.forName("com.mysql.cj.jdbc.Driver");
			    }catch(Exception ex) {
			      setTitle(ex.toString());
			    }
			  }
	  		
		    
	

}
