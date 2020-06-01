/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import core.ConexionDB;
import core.Encriptacion;
import core.Variables;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author funes
 */
public class FormularioAgregarusuarios extends javax.swing.JInternalFrame {

    ConexionDB conexion;
    LinkedList<LinkedList<String>> alResultados;
    DefaultTableModel modelousuarios;
    
    /**
     * Creates new form FormularioAgregarusuarios
     */
    public FormularioAgregarusuarios() {
        initComponents();
        
        try{
            conexion=new ConexionDB(Variables.rutaDB, Variables.userDB, Variables.claveDB);
            
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            JOptionPane.showInternalMessageDialog(rootPane, "Error en la conexion a la base de datos. Contacte a su administrador", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
        modelousuarios=(DefaultTableModel) tblusuarios.getModel();
        
        cargarTabla();
    }
    
    private void cargarTabla() {
        limpiarTabla();
        try{
            ResultSet rsResultado=conexion.ejecutar("select * from oina_usuario");

            alResultados=conexion.convertirRsToArrayList(rsResultado);

            for(LinkedList<String> aux : alResultados)
            {
                String tipoUsuario = "";
                
                if(aux.get(4).equals("0"))
                    tipoUsuario = "Usuario";
                else if(aux.get(4).equals("1"))
                    tipoUsuario = "Admin";
                else
                    tipoUsuario = "Prestamista";
                
                
                
                
                modelousuarios.addRow(new String[] {
                            aux.get(0), 
                            tipoUsuario, 
                            aux.get(2).trim().equals("null") ? "" : aux.get(2).trim(),
                            aux.get(3).trim().equals("null") ? "" : aux.get(2).trim()
                        });
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }
    
    private void limpiarTabla() {
        modelousuarios.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tbClave = new javax.swing.JTextField();
        cbTipoUsuario = new javax.swing.JComboBox<>();
        tbUserName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblusuarios = new javax.swing.JTable();
        btnAgregarUsuario = new javax.swing.JButton();
        btnModificarLibro = new javax.swing.JButton();
        btnEliminarLibro = new javax.swing.JButton();
        tbNombres = new javax.swing.JTextField();
        tbApellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(52, 128, 118));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/autor_32x32.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categorias_32x32.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Agregar usuarios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1018, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
                    .addGap(40, 40, 40)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel4.setBackground(new java.awt.Color(233, 242, 241));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbClave.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbClaveKeyReleased(evt);
            }
        });

        cbTipoUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione una opcion--", "Usuario", "Administrador", "Prestamista" }));

        tbUserName.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUserNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbUserNameKeyReleased(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(233, 242, 241));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 64, 46));
        jLabel11.setText("Tipo de usuario");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(1, 64, 46));
        jLabel12.setText("Clave");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(1, 64, 46));
        jLabel13.setText("Usuario");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(1, 64, 46));
        jLabel15.setText("Nombres");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(1, 64, 46));
        jLabel16.setText("Apellidos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12))
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(26, 26, 26)
                .addComponent(jLabel16)
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addGap(38, 38, 38)
                .addComponent(jLabel11)
                .addGap(123, 123, 123))
        );

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 64, 46));
        jLabel1.setText("Usuarios");

        tblusuarios.setBorder(new javax.swing.border.MatteBorder(null));
        tblusuarios.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        tblusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Tipo de usuario", "Nombres", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblusuarios.setRowHeight(32);
        tblusuarios.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblusuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblusuariosFocusLost(evt);
            }
        });
        tblusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblusuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblusuarios);

        btnAgregarUsuario.setBackground(new java.awt.Color(52, 128, 118));
        btnAgregarUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAgregarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar_32x32.png"))); // NOI18N
        btnAgregarUsuario.setText("Agregar usuario");
        btnAgregarUsuario.setBorder(null);
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        btnModificarLibro.setBackground(new java.awt.Color(52, 128, 118));
        btnModificarLibro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificarLibro.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar_32x32.png"))); // NOI18N
        btnModificarLibro.setText("Modificar usuario");
        btnModificarLibro.setBorder(null);
        btnModificarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLibroActionPerformed(evt);
            }
        });

        btnEliminarLibro.setBackground(new java.awt.Color(52, 128, 118));
        btnEliminarLibro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEliminarLibro.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/basura_32x32.png"))); // NOI18N
        btnEliminarLibro.setText("Eliminar usuario");
        btnEliminarLibro.setBorder(null);
        btnEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLibroActionPerformed(evt);
            }
        });

        tbNombres.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNombresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNombresKeyReleased(evt);
            }
        });

        tbApellidos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tbApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbApellidosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbApellidosKeyReleased(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("<html>Nota: Recuerde que los nombres y los apellidos<br>son opcionales, puede dejarlos en blanco</html>");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("<html>El usuario no debe llevar espacios. Este <br> le servira para acceder al sistema o <br>prestar libros</html>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbTipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tbUserName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbApellidos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbNombres, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tbClave, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(736, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(tbNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(tbClave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarLibro)
                    .addComponent(btnEliminarLibro)
                    .addComponent(btnAgregarUsuario))
                .addGap(20, 20, 20))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(328, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1110, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbClaveKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbClaveKeyReleased

    private void tbUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUserNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbUserNameKeyReleased

    private void tbUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUserNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbUserNameKeyPressed

    private void tblusuariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblusuariosFocusLost
        // TODO add your handling code he
    }//GEN-LAST:event_tblusuariosFocusLost

    private void tblusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblusuariosMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblusuariosMouseClicked

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        // TODO add your handling code here:
        String usuario = tbUserName.getText().trim();

        if (usuario.isEmpty())
            JOptionPane.showMessageDialog(rootPane, "El campo 'Usuario' es requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        else if (tbClave.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "El campo 'Clave' es requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        else if(cbTipoUsuario.getSelectedIndex()==0)
            JOptionPane.showMessageDialog(rootPane, "Seleccione un tipo de usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
        else 
        {
            try {
                String nombres = tbNombres.getText().trim().equals("") ? "null" : tbNombres.getText().trim();
                
                String apellidos = tbApellidos.getText().trim().equals("") ? "null" : tbApellidos.getText().trim();
                
                String sql = "INSERT INTO oina_usuario VALUES ('" + usuario + "','"
                        +Encriptacion.encrypt(tbClave.getText())+"','"+nombres+"', '"+apellidos+"',"
                        +(cbTipoUsuario.getSelectedIndex()-1)+")";
                
                int filasAfectadas = conexion.ejecutarComando(sql);
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(rootPane, "El ususario ha sido agregado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    cargarTabla();
                } else
                    JOptionPane.showMessageDialog(rootPane, "Lo sentimos, ha ocurrido un error. Contacte con su administrador", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(rootPane, "Ocurrio un error. Por favor tome en cuenta que 2 usuarios no pueden tener el mismo nombre. Si esto no resuelve su error contacte con su administrador", "Error",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FormularioAgregarusuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnModificarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLibroActionPerformed
        // TODO add your handling code here:
        if (tblusuarios.getSelectedRows().length > 0) {
            ModificarUsuarioDialog jDialog = new ModificarUsuarioDialog(FormularioPrincipal.contex, true, 
                    modelousuarios.getValueAt(tblusuarios.getSelectedRow(), 0).toString(),Integer.parseInt(alResultados.get(tblusuarios.getSelectedRow()).get(1)));
            jDialog.setLocationRelativeTo(this);
            jDialog.setVisible(true);

            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarTabla();
                }
            });
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarLibroActionPerformed

    private void btnEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLibroActionPerformed
        // TODO add your handling code here:

        if (tblusuarios.getSelectedRows().length > 0) {
            JPanel pnlAdvertencia = new JPanel();

            JLabel lblAdvertencia = new JLabel();

            lblAdvertencia.setText("¿Esta seguro que desea eliminar el usuario?\n Esta accion no se puede deshacer");

            lblAdvertencia.setForeground(Color.red);

            pnlAdvertencia.add(lblAdvertencia);

            int dialogResult = JOptionPane.showConfirmDialog(rootPane, pnlAdvertencia, "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    conexion.ejecutarComando("delete from oina_usuario where nombre = '" + modelousuarios.getValueAt(tblusuarios.getSelectedRow(), 0) + "'");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, "error: " + e);
                }
                cargarTabla();
            }

        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarLibroActionPerformed

    private void tbNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNombresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNombresKeyPressed

    private void tbNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNombresKeyReleased

    private void tbApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbApellidosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbApellidosKeyPressed

    private void tbApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbApellidosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbApellidosKeyReleased

    private void limpiarCampos() {
        tbUserName.setText("");
        tbClave.setText("");
        tbNombres.setText("");
        tbApellidos.setText("");
        cbTipoUsuario.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnEliminarLibro;
    private javax.swing.JButton btnModificarLibro;
    private javax.swing.JComboBox<String> cbTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tbApellidos;
    private javax.swing.JTextField tbClave;
    private javax.swing.JTextField tbNombres;
    private javax.swing.JTextField tbUserName;
    private javax.swing.JTable tblusuarios;
    // End of variables declaration//GEN-END:variables

    
}
