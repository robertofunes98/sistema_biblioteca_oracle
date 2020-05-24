/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import core.ConexionDB;
import core.Variables;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FA
 */
public class AgregarLibros extends javax.swing.JInternalFrame {

    /**
     * Creates new form AgregarLibros
     */
    
    ConexionDB conexion;
    LinkedList<LinkedList<String>> alAutores, alCategorias,alLibros;
    DefaultTableModel modeloLibros;
    
    boolean insertarCategoriaNueva=false, insertarNuevoAutor=false;
    
    
    public AgregarLibros(){
        initComponents();
        
        try{
            conexion=new ConexionDB(Variables.rutaDB, Variables.userDB, Variables.claveDB);
            
            cargarComboBoxes();
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            JOptionPane.showInternalMessageDialog(rootPane, "Error en la conexion a la base de datos. Contacte a su administrador", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
        modeloLibros=(DefaultTableModel) tblLibros.getModel();
        
        tblLibros.getColumnModel().getColumn(0).setPreferredWidth(65);
        tblLibros.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblLibros.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblLibros.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblLibros.getColumnModel().getColumn(4).setPreferredWidth(60);
        
        
        txtAgregarCategoria.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              cboxCategoria.setSelectedIndex(0);
              insertarCategoriaNueva=true;
            }
            public void removeUpdate(DocumentEvent e) {
               cboxCategoria.setSelectedIndex(0);
               insertarCategoriaNueva=true;
            }
            public void insertUpdate(DocumentEvent e) {
               cboxCategoria.setSelectedIndex(0);
               insertarCategoriaNueva=true;
            }
        });
        
        txtAgregarAutor.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              cboxAutor.setSelectedIndex(0);
              insertarNuevoAutor=true;
            }
            public void removeUpdate(DocumentEvent e) {
               cboxAutor.setSelectedIndex(0);
              insertarNuevoAutor=true;
            }
            public void insertUpdate(DocumentEvent e) {
              cboxAutor.setSelectedIndex(0);
              insertarNuevoAutor=true;
            }
        });
    }
    
    private void cargarTabla(int opcion, String busqueda) {
        limpiarTabla();
        String sql="";
        switch(opcion)
        {
            case 0:
                sql="select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad, autor.id_autor, "
                        + "categoria.id_categoria from libro inner join autor on autor.id_autor = libro.id_autor "
                        + "INNER JOIN categoria on categoria.id_categoria = libro.id_categoria where libro.id_libro LIKE '"+busqueda+"%'";
                break;
                
                case 1:
                sql="select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad, autor.id_autor, "
                        + "categoria.id_categoria from libro inner join autor on autor.id_autor = libro.id_autor "
                        + "INNER JOIN categoria on categoria.id_categoria = libro.id_categoria where libro.nombre LIKE '"+busqueda+"%'";
                break;
        }
        try{
            ResultSet rsResultado=conexion.ejecutar(sql);

            alLibros=conexion.convertirRsToArrayList(rsResultado);

            for(LinkedList<String> aux : alLibros)
            {
                modeloLibros.addRow(new String[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4)});
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }
    
    private void limpiarTabla() {
        modeloLibros.setRowCount(0);
    }
    
    private void cargarComboBoxes() throws SQLException{
        cargarCBoxAutores();
        cargarCBoxCategorias();
    }
    
    private void cargarCBoxAutores() throws SQLException{
        ResultSet resultadoAutores = conexion.ejecutar("SELECT id_autor, nombre FROM autor");
        alAutores = conexion.convertirRsToArrayList(resultadoAutores);
        
        cboxAutor.addItem("-- Seleccionar Autor --");
            
        if(!alAutores.isEmpty()){
            for(LinkedList<String> autor: alAutores){
                cboxAutor.addItem(autor.get(1));
            }
        }
    }
    
    private void cargarCBoxCategorias() throws SQLException{
        ResultSet resultadoCategorias = conexion.ejecutar("SELECT id_categoria, nombre FROM categoria");
        alCategorias = conexion.convertirRsToArrayList(resultadoCategorias);
        
        cboxCategoria.addItem("-- Seleccionar Categoria --");
            
        if(!alCategorias.isEmpty()){
            for(LinkedList<String> categoria: alCategorias){
                cboxCategoria.addItem(categoria.get(1));
            }
        }
    }
    
    private boolean codigoExistente(String codigo) throws SQLException{
        ResultSet resultado = conexion.ejecutar("SELECT id_libro FROM libro WHERE id_libro = '" + codigo + "'");
        LinkedList<LinkedList<String>> codigos = conexion.convertirRsToArrayList(resultado);
        
        return (!codigos.isEmpty());
    }
    
    private void limpiarControles(){
        txtCodigo.setText("");
        txtNombre.setText("");
        cboxAutor.setSelectedIndex(0);
        cboxCategoria.setSelectedIndex(0);
        spnCantidad.setValue(0);
        spnPrecio.setValue(0.00);
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
        btnAgregarLibro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        cboxAutor = new javax.swing.JComboBox<>();
        cboxCategoria = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        txtCodigo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        spnPrecio = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAgregarCategoria = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAgregarAutor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnAgregarLibro.setBackground(new java.awt.Color(1, 64, 46));
        btnAgregarLibro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAgregarLibro.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar_32x32.png"))); // NOI18N
        btnAgregarLibro.setText("Agregar");
        btnAgregarLibro.setBorder(null);
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 64, 46));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Agregar Libros");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/libros_32x32.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/libros_32x32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(233, 242, 241));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 58, 250, 40));

        cboxAutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxAutorItemStateChanged(evt);
            }
        });
        jPanel2.add(cboxAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 125, 178, 40));

        cboxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxCategoriaItemStateChanged(evt);
            }
        });
        jPanel2.add(cboxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 186, 178, 40));

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnCantidad.setRequestFocusEnabled(false);
        jPanel2.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 106, 40));

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 12, 160, 40));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 64, 46));
        jLabel4.setText("Cantidad");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 64, 46));
        jLabel5.setText("Categoría");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 64, 46));
        jLabel6.setText("Autor");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 64, 46));
        jLabel3.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 64, 46));
        jLabel2.setText("Código");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 64, 46));
        jLabel11.setText("Precio");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(38, 38, 38)
                .addComponent(jLabel11)
                .addGap(38, 38, 38))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, 367));

        tblLibros.setBorder(new javax.swing.border.MatteBorder(null));
        tblLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID libro", "Nombre", "Autor", "Categoria", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLibros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblLibros.setRowHeight(32);
        tblLibros.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblLibros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblLibrosFocusLost(evt);
            }
        });
        tblLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLibros);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 58, 636, 285));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 64, 46));
        jLabel1.setText("Libros");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 24, -1, -1));

        spnPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 0.01d));
        spnPrecio.setRequestFocusEnabled(false);
        jPanel2.add(spnPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 303, 106, 40));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(1, 64, 46));
        jLabel10.setText("$");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 307, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(1, 64, 46));
        jLabel12.setText("Seleccionar");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 166, -1, -1));

        txtAgregarCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAgregarCategoriaKeyReleased(evt);
            }
        });
        jPanel2.add(txtAgregarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 187, 178, 40));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("o");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 167, -1, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(1, 64, 46));
        jLabel14.setText("Agregar");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 166, -1, -1));

        txtAgregarAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAgregarAutorKeyReleased(evt);
            }
        });
        jPanel2.add(txtAgregarAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 126, 178, 40));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(1, 64, 46));
        jLabel15.setText("Agregar");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 106, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("o");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 106, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(1, 64, 46));
        jLabel17.setText("Seleccionar");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 106, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed
        // TODO add your handling code here:
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        int indiceAutor = cboxAutor.getSelectedIndex();
        int indiceCategoria = cboxCategoria.getSelectedIndex();
        String sCantidad = String.valueOf(spnCantidad.getValue());
        String sPrecio = String.valueOf(spnPrecio.getValue());
        
        if(txtAgregarAutor.getText().trim().isEmpty() && insertarNuevoAutor)
            JOptionPane.showMessageDialog(rootPane, "No puede dejar en blanco el nombre del autor si desea agregar uno nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        else if(txtAgregarCategoria.getText().trim().isEmpty() && insertarCategoriaNueva)
            JOptionPane.showMessageDialog(rootPane, "No puede dejar en blanco el nombre de la categoria si desea agregar una nueva", "Advertencia", JOptionPane.WARNING_MESSAGE);
        else if(!codigo.isEmpty() && !nombre.isEmpty() && (indiceAutor>0 || insertarNuevoAutor) && (indiceCategoria>0 || insertarCategoriaNueva)){
            try {
                if(codigoExistente(codigo)){
                    JOptionPane.showMessageDialog(rootPane, "El código " + codigo + " ya está en uso, por favor digite uno nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }else{
                    
                    String idAutor = null,idCategoria=null;
                    
                    if(insertarCategoriaNueva)
                    {
                        String nombreCategoria = txtAgregarCategoria.getText().trim();
        
                        try {
                            String sql = "INSERT INTO categoria(nombre) VALUES ('" + nombreCategoria + "')";
                            int filasAfectadas = conexion.ejecutarComando(sql);
                            if (filasAfectadas > 0) {
                                JOptionPane.showMessageDialog(rootPane, "La categoría ha sido agregada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                                txtAgregarCategoria.setText("");
                                
                                ResultSet rsResultados=conexion.ejecutar("SELECT MAX(id_categoria) AS id FROM categoria");
                                
                                rsResultados.next();
                                
                                idCategoria=rsResultados.getString(1);
                                
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Lo sentimos, ha ocurrido un error", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(AgregarLibros.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                    if(insertarNuevoAutor)
                    {
                        String nombreAutor = txtAgregarAutor.getText().trim();
        
                        try {
                            String sql = "INSERT INTO autor(nombre) VALUES ('" + nombreAutor + "')";
                            int filasAfectadas = conexion.ejecutarComando(sql);
                            if (filasAfectadas > 0) {
                                JOptionPane.showMessageDialog(rootPane, "El autor ha sido agregado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                                txtAgregarAutor.setText("");
                                
                                ResultSet rsResultados=conexion.ejecutar("SELECT MAX(id_autor) AS id FROM autor");
                                
                                rsResultados.next();
                                
                                idAutor=rsResultados.getString(1);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Lo sentimos, ha ocurrido un error", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(AgregarLibros.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                    if(!insertarCategoriaNueva)
                        idCategoria = alCategorias.get(indiceCategoria - 1).get(0);
                    
                    
                    if(!insertarNuevoAutor)
                        idAutor = alAutores.get(indiceAutor - 1).get(0);
                    
                    
                    String sql = "INSERT INTO libro(id_libro, nombre, cantidad, id_autor, id_categoria, precio) VALUES ('"+codigo+"','"+nombre+"','"+sCantidad+"','"+idAutor+"','"+idCategoria+"','"+sPrecio+"')";
                    int filasAfectadas = conexion.ejecutarComando(sql);
                    if(filasAfectadas>0){
                        JOptionPane.showMessageDialog(rootPane, "El libro ha sido agregado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        limpiarControles();
                        cargarComboBoxes();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Lo sentimos, ha ocurrido un error", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } 
                }
            } catch (SQLException ex) {
                Logger.getLogger(AgregarLibros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Todos los campos son requeridos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void tblLibrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblLibrosFocusLost
        // TODO add your handling code he
    }//GEN-LAST:event_tblLibrosFocusLost

    private void tblLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLibrosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLibrosMouseClicked

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
        cargarTabla(0,txtCodigo.getText());
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        cargarTabla(1,txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtAgregarCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarCategoriaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarCategoriaKeyReleased

    private void cboxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxCategoriaItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED && cboxCategoria.getSelectedIndex()!=0)
        {
            txtAgregarAutor.setText("");
            insertarCategoriaNueva=false;
        }
    }//GEN-LAST:event_cboxCategoriaItemStateChanged

    private void txtAgregarAutorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarAutorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarAutorKeyReleased

    private void cboxAutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxAutorItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED && cboxAutor.getSelectedIndex()!=0)
        {
            txtAgregarCategoria.setText("");
            insertarNuevoAutor=false;
        }
    }//GEN-LAST:event_cboxAutorItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JComboBox<String> cboxAutor;
    private javax.swing.JComboBox<String> cboxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnPrecio;
    private javax.swing.JTable tblLibros;
    private javax.swing.JTextField txtAgregarAutor;
    private javax.swing.JTextField txtAgregarCategoria;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
