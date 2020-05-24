/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import core.ConexionDB;
import core.Variables;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author FA
 */
public class ListadoAutoresCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListadoAutoresCategorias
     */
    
    ConexionDB conexion;
    DefaultTableModel modeloAutores, modeloCategorias;
    LinkedList<LinkedList<String>> alAutores, alCategorias;
    
    public ListadoAutoresCategorias() {
        initComponents();
        
        modeloAutores = (DefaultTableModel) tblAutores.getModel();
        modeloCategorias = (DefaultTableModel) tblCategorias.getModel();
        
        try{
            conexion=new ConexionDB(Variables.rutaDB, Variables.userDB, Variables.claveDB);
            
            cargarTablas();
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            
        }
    }
    
    private void cargarTablas() {
        limpiarTablas();
        try{
            //cargar datos autores
            ResultSet rsResultadoAutores=conexion.ejecutar("select id_autor, nombre from autor");

            alAutores=conexion.convertirRsToArrayList(rsResultadoAutores);

            for(LinkedList<String> aux : alAutores)
            {
                modeloAutores.addRow(new String[] {aux.get(0), aux.get(1)});
            }
            
            //cargar datos categorias
            ResultSet rsResultadoCategorias=conexion.ejecutar("select id_categoria, nombre from categoria");

            alCategorias=conexion.convertirRsToArrayList(rsResultadoCategorias);

            for(LinkedList<String> aux : alCategorias)
            {
                modeloCategorias.addRow(new String[] {aux.get(0), aux.get(1)});
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }
    
    private void limpiarTablas(){
        modeloAutores.setRowCount(0);
        modeloCategorias.setRowCount(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btnEliminarAutor = new javax.swing.JButton();
        btnModificarAutor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAutores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tfBuscadorAutores = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        tfBuscadorCategorias = new javax.swing.JTextField();
        btnModificarCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(233, 242, 241));

        btnEliminarAutor.setBackground(new java.awt.Color(1, 64, 46));
        btnEliminarAutor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEliminarAutor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/basura_32x32.png"))); // NOI18N
        btnEliminarAutor.setText("Eliminar Autor");
        btnEliminarAutor.setBorder(null);
        btnEliminarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAutorActionPerformed(evt);
            }
        });

        btnModificarAutor.setBackground(new java.awt.Color(1, 64, 46));
        btnModificarAutor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificarAutor.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar_32x32.png"))); // NOI18N
        btnModificarAutor.setText("Modificar Autor");
        btnModificarAutor.setBorder(null);
        btnModificarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAutorActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 64, 46));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(233, 242, 241));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(233, 242, 241));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listado de Autores/Categorías");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/autor_32x32.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/categorias_32x32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(1, 64, 46));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblAutores.setBorder(new javax.swing.border.MatteBorder(null));
        tblAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Autor", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAutores.setRowHeight(32);
        tblAutores.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(tblAutores);

        jLabel2.setBackground(new java.awt.Color(233, 242, 241));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(233, 242, 241));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar_16x16.png"))); // NOI18N
        jLabel2.setText("Buscar por nombre:");

        tfBuscadorAutores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscadorAutoresKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tfBuscadorAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfBuscadorAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(1, 64, 46));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCategorias.setBorder(new javax.swing.border.MatteBorder(null));
        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Autor", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategorias.setRowHeight(32);
        tblCategorias.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jScrollPane2.setViewportView(tblCategorias);

        jLabel5.setBackground(new java.awt.Color(233, 242, 241));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(233, 242, 241));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar_16x16.png"))); // NOI18N
        jLabel5.setText("Buscar por nombre:");

        tfBuscadorCategorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscadorCategoriasKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tfBuscadorCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfBuscadorCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnModificarCategoria.setBackground(new java.awt.Color(1, 64, 46));
        btnModificarCategoria.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar_32x32.png"))); // NOI18N
        btnModificarCategoria.setText("Modificar Categoría");
        btnModificarCategoria.setBorder(null);
        btnModificarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setBackground(new java.awt.Color(1, 64, 46));
        btnEliminarCategoria.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEliminarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/basura_32x32.png"))); // NOI18N
        btnEliminarCategoria.setText("Eliminar Categoría");
        btnEliminarCategoria.setBorder(null);
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificarCategoria))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnEliminarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAutorActionPerformed
        // TODO add your handling code here:

        if (tblAutores.getSelectedRows().length > 0) {
            JPanel pnlAdvertencia = new JPanel();

            JLabel lblAdvertencia = new JLabel();

            lblAdvertencia.setText("¿Esta seguro que desea eliminar el autor?\n Esta accion eliminara todos los libros asociados a el y no se puede deshacer");

            lblAdvertencia.setForeground(Color.red);

            pnlAdvertencia.add(lblAdvertencia);

            int dialogResult = JOptionPane.showConfirmDialog(rootPane, pnlAdvertencia, "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    conexion.ejecutarComando("delete from autor where id_autor = '" + modeloAutores.getValueAt(tblAutores.getSelectedRow(), 0) + "'");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, "error: " + e);
                }
                cargarTablas();
            }

        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarAutorActionPerformed

    private void btnModificarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAutorActionPerformed
        // TODO add your handling code here:
        if (tblAutores.getSelectedRows().length > 0) {
            ModificarAutorDialog jDialog = new ModificarAutorDialog(FormularioPrincipal.contex, true, modeloAutores.getValueAt(tblAutores.getSelectedRow(), 0).toString(),
                    modeloAutores.getValueAt(tblAutores.getSelectedRow(), 1).toString());
            jDialog.setLocationRelativeTo(this);
            jDialog.setVisible(true);

            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarTablas();
                }
            });
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarAutorActionPerformed

    private void tfBuscadorAutoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscadorAutoresKeyReleased
        // TODO add your handling code here:
        
        buscarAutorPorNombre(tfBuscadorAutores.getText());

    }//GEN-LAST:event_tfBuscadorAutoresKeyReleased

    private void tfBuscadorCategoriasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscadorCategoriasKeyReleased
        // TODO add your handling code here:
        buscarCategoriaPorNombre(tfBuscadorCategorias.getText());
    }//GEN-LAST:event_tfBuscadorCategoriasKeyReleased

    private void btnModificarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCategoriaActionPerformed
        // TODO add your handling code here:
        if (tblCategorias.getSelectedRows().length > 0) {
            ModificarCategoriaDialog jDialog = new ModificarCategoriaDialog(FormularioPrincipal.contex, true, modeloCategorias.getValueAt(tblCategorias.getSelectedRow(), 0).toString(),
                    modeloCategorias.getValueAt(tblCategorias.getSelectedRow(), 1).toString());
            jDialog.setLocationRelativeTo(this);
            jDialog.setVisible(true);

            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarTablas();
                }
            });
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        // TODO add your handling code here:
        if (tblCategorias.getSelectedRows().length > 0) {
            JPanel pnlAdvertencia = new JPanel();

            JLabel lblAdvertencia = new JLabel();

            lblAdvertencia.setText("¿Esta seguro que desea eliminar la categoría?\n Esta accion eliminara todos los libros asociados a el y no se puede deshacer");

            lblAdvertencia.setForeground(Color.red);

            pnlAdvertencia.add(lblAdvertencia);

            int dialogResult = JOptionPane.showConfirmDialog(rootPane, pnlAdvertencia, "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    conexion.ejecutarComando("delete from categoria where id_categoria = '" + modeloCategorias.getValueAt(tblCategorias.getSelectedRow(), 0) + "'");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, "error: " + e);
                }
                cargarTablas();
            }

        }else{
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void buscarAutorPorNombre(String nombre){
        try{
            ResultSet rsResultado=conexion.ejecutar("select id_autor, nombre from autor where nombre LIKE '"+nombre+"%'");

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            modeloAutores.setRowCount(0);

            for(LinkedList<String> aux : alResultados)
            {
                modeloAutores.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }
    
    private void buscarCategoriaPorNombre(String nombre){
        try{
            ResultSet rsResultado=conexion.ejecutar("select id_categoria, nombre from categoria where nombre LIKE '"+nombre+"%'");

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            modeloCategorias.setRowCount(0);

            for(LinkedList<String> aux : alResultados)
            {
                modeloCategorias.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarAutor;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnModificarAutor;
    private javax.swing.JButton btnModificarCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAutores;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextField tfBuscadorAutores;
    private javax.swing.JTextField tfBuscadorCategorias;
    // End of variables declaration//GEN-END:variables
}
