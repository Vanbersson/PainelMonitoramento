/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Basica.Cliente;
import Basica.Mecanico;
import Dao.DCliente;
import Dao.DMecanico;
import Dao.IDCliente;
import Dao.IDMecanico;
import Excecao.ErroConexaoSQL;
import Excecao.ErroDao;
import Util.LookAndFeel;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import java.awt.Color;
import java.awt.Component;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrador
 */
public class jFPainel extends javax.swing.JFrame {
    
    List<Cliente> lista = new ArrayList<>();
    AnnotationResolver ResolverCliente = new AnnotationResolver(Cliente.class);
    ObjectTableModel tableModel = new ObjectTableModel(ResolverCliente,"codigo,nome,chassi,placa,ordem_OS,dataEmissao,dataPrevista,dataentrega,descricao,situacao_os");
    
     List<Mecanico> lista2 = new ArrayList<>();
     AnnotationResolver ResolverCliente2 = new AnnotationResolver(Mecanico.class);
     ObjectTableModel tableModel2 = new ObjectTableModel(ResolverCliente2,"mecanico,nomeMecanico,nro_os,cod_servico,tipo_os,situacao,qtdHora,des_servico");
     
     
     
    /**
     * Creates new form jFPainel
     */
    public jFPainel() {
        LookAndFeel.setAparencia();
        initComponents();
        tamanhoColumn();
        cores();
        
        osAberta_Fechada_Cancelada();
        totalOS();
        nomeMecanico();
        new letrero().start();
        new dataHora().start();
         
    }
    
     //conta todas as O.S na tabela
    private void totalOS(){
         jLabeltotalOS.setText("Total O.S:. "+Integer.toString(jTable_OS.getModel().getRowCount()));
    }
    
    //Lista os nomes dos mecânicos
    public void nomeMecanico(){     
        IDMecanico mec = new DMecanico();
        ArrayList<Mecanico> lista = new ArrayList<>();    
        try {
            lista = mec.ListaNome_Mecanico();
            //Adciona os nomes ao jComboBox
            for(int i=0;i< lista.size();i++){
                jComboBoxMecanico.addItem(lista.get(i).getNomeMecanico());
                
                if((jTextFieldCodigoMecanico.getText().equals(lista.get(i).getMecanico())) && (!"".equals(jTextFieldCodigoMecanico.getText()))) {
                    jComboBoxMecanico.setSelectedIndex(i+1); 
                }
            }
            
        } catch (ErroConexaoSQL ex) {
            Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroDao ex) {
            Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //Lista todos os Serviços do Mecânico
    public void servicoMecanico(){
        
        IDMecanico mec = new DMecanico();       
        ArrayList<Mecanico> lista = new ArrayList<>();
        //Pega a posição selecionada do jCombobox
        int posicao = jComboBoxMecanico.getSelectedIndex();
        
        try {
             
            lista = mec.ListaNome_Mecanico();
            jTextFieldCodigoMecanico.setText(lista.get(posicao-1).getMecanico());      
                 
            jTabbedPane2.setSelectedIndex(1); // seleciona a aba
            lista2 =  mec.ListaTodos_Servico_Mecanico(jTextFieldCodigoMecanico.getText());
            tableModel2.setData(lista2);
            
        } catch (ErroConexaoSQL ex) {
            Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroDao ex) {
            Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //Colorir a linha da tabela
    private void cores(){
        //Cores do JTable
         jTable_OS.setBackground(Color.LIGHT_GRAY);
         jTable_OS.setSelectionBackground(Color.BLACK);
         
         jTableServico.setBackground(Color.LIGHT_GRAY);
         jTableServico.setSelectionBackground(Color.BLACK);
         
    }
    
    //Tamanho das Colunas da tabela
    private void tamanhoColumn(){
        // Table_OS
        TableColumn codigoColumn = jTable_OS.getColumnModel().getColumn(0);
        codigoColumn.setMinWidth(60);
        codigoColumn.setMaxWidth(60);
        
        TableColumn nomeColumn = jTable_OS.getColumnModel().getColumn(1);
        nomeColumn.setMinWidth(300);
        nomeColumn.setMaxWidth(300);
        
        TableColumn chassiColumn = jTable_OS.getColumnModel().getColumn(2);
        chassiColumn.setMinWidth(250);
        chassiColumn.setMaxWidth(250);
        
        TableColumn placaColumn = jTable_OS.getColumnModel().getColumn(3);
        placaColumn.setMinWidth(100);
        placaColumn.setMaxWidth(100);
        
        TableColumn Nro_osComun = jTable_OS.getColumnModel().getColumn(4);
        Nro_osComun.setMinWidth(70);
        Nro_osComun.setMaxWidth(70);
        
        TableColumn data1Column = jTable_OS.getColumnModel().getColumn(5);
        data1Column.setMinWidth(120);
        data1Column.setMaxWidth(120);
        
        TableColumn data2Column = jTable_OS.getColumnModel().getColumn(6);
        data2Column.setMinWidth(120);
        data2Column.setMaxWidth(120);
        
        TableColumn data3Column = jTable_OS.getColumnModel().getColumn(7);
        data3Column.setMinWidth(120);
        data3Column.setMaxWidth(120);
        
        TableColumn situacaoCulo = jTable_OS.getColumnModel().getColumn(9);
        situacaoCulo.setMaxWidth(30);
        situacaoCulo.setMinWidth(30);
        
        //Table_Ser_OS
        TableColumn MecColumn = jTableServico.getColumnModel().getColumn(0);
        MecColumn.setMinWidth(60);
        MecColumn.setMaxWidth(60);
        
        TableColumn MecNomeColumn = jTableServico.getColumnModel().getColumn(1);
        MecNomeColumn.setMinWidth(300);
        MecNomeColumn.setMaxWidth(300);
        
        TableColumn MecOSColumn = jTableServico.getColumnModel().getColumn(2);
        MecOSColumn.setMinWidth(70);
        MecOSColumn.setMaxWidth(70);
        
        TableColumn CodColumn = jTableServico.getColumnModel().getColumn(3);
        CodColumn.setMinWidth(100);
        CodColumn.setMaxWidth(100);
        
        TableColumn tipoColumn = jTableServico.getColumnModel().getColumn(4);
        tipoColumn.setMinWidth(100);
        tipoColumn.setMaxWidth(100);
        
        TableColumn situColumn = jTableServico.getColumnModel().getColumn(5);
        situColumn.setMinWidth(100);
        situColumn.setMaxWidth(100);
        
        TableColumn temColumn = jTableServico.getColumnModel().getColumn(6);
        temColumn.setMinWidth(100);
        temColumn.setMaxWidth(100);   
    }
        
    //mostra os serviços da O.S selecionada
    class ThreadClicked_Table extends Thread{
            @Override
            public void run() {
                IDMecanico ser = new DMecanico();
                
                int linha= jTable_OS.getSelectedRow(); //pega a linha selecionada         
                String dado = tableModel.getValueAt(linha, 4).toString(); //pega o número da O.S da linha selecionada
                try {
                    lista2 = ser.ListaServico_OS(dado);
                    tableModel2.setData(lista2);
                     
                } catch (ErroConexaoSQL ex) {
                    Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ErroDao ex) {
                    Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

    }
    
    //Letrero
    class letrero extends Thread{       
        @Override
        public void run(){
            jLabelLetrero.setText("BEM VINDO A UNIÃO IMPLEMENTOS");
            int tamanho=601;
            for(int i=600;i < tamanho;i--){
                
                try{sleep(20); }catch(Exception ex){}
                jLabelLetrero.setBounds(i,jLabelLetrero.getY(),jLabelLetrero.getWidth(),jLabelLetrero.getHeight() );
               if(i == -550){
                   i=600;
               }
                    
            }
            
        }    
    }
    
    //Mostra data e Hora atual na tela principal
    class dataHora extends Thread{
        public void run(){
            while(true){
                DateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date dat =new Date();
                jLabel_DataHora.setText(d.format(dat));
            }
    
        }
    }
    
    private void limpaCampo(){
        jTextFieldCodigoCli.setText(null);
        jTextFieldNomeCli.setText(null);
        jTextFieldNro_OS.setText(null);
        jTextFieldPlaca.setText(null);
        jTextFieldCodigoMecanico.setText(null);
        
        jCheckBoxSer_And.setSelected(false);
        jCheckBoxSer_Par.setSelected(false);
        jCheckBoxSer_Fech.setSelected(false);
        
        jCheckBoxOSAberta.setSelected(false);
        jCheckBoxOSFechada.setSelected(false);
        jCheckBoxOSCancelada.setSelected(false);
        
        jComboBoxMecanico.setSelectedIndex(0);
        
    }
    
    //Verifica a situação do jCheckBox
    private String verificaChecBox_OS(){
        String situacao ="0";       
        if(jCheckBoxOSAberta.isSelected())
            situacao = "0";
        if(jCheckBoxOSFechada.isSelected())
            situacao = "9";
        if(jCheckBoxOSCancelada.isSelected())
            situacao = "7";
        return situacao;
    }
    
    //Verifica a situação da O.S
    private void osAberta_Fechada_Cancelada(){
        IDCliente c = new DCliente(); 
        
            try {
                lista = c.situacao_OS(verificaChecBox_OS());
                tableModel.setData(lista);
                jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
    }
    
    private void conslta_cli_Codigo(){
        IDCliente cli = new DCliente();
        
        if(!"".equals(jTextFieldCodigoCli.getText())){
            try {
                
               lista = cli.lista_OS_ClienteCodigo(jTextFieldCodigoCli.getText());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
              
        
    }
    
    private void consulta_cli_Nome(){
        IDCliente cli = new DCliente();
        
        if(!"".equals(jTextFieldNomeCli.getText())){
            try {
                
               lista = cli.Lista_os_ClienteNome(jTextFieldNomeCli.getText());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private void consulta_cli_Nro_OS(){
        IDCliente cli = new DCliente();
        
        if(!"".equals(jTextFieldNro_OS.getText())){
            try {
                
               lista = cli.Lista_os_ClienteNro_OS(jTextFieldNro_OS.getText());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private void consulta_cli_Placa(){
      IDCliente cli = new DCliente();
        
        if(!"".equals(jTextFieldPlaca.getText())){
            try {
                
               lista = cli.Lista_os_ClientePlca(jTextFieldPlaca.getText());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    private void consulta_cli_Situ_OSCodigo(){
       IDCliente cli = new DCliente();
          try {
                
               lista = cli.Lista_Cli_Situa_OSCodigo(jTextFieldCodigoCli.getText(),verificaChecBox_OS());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
    
    private void consulta_cli_Situ_OSNome(){
       IDCliente cli = new DCliente();
          try {
                
               lista = cli.Lista_Cli_Situa_OSNome(jTextFieldNomeCli.getText(),verificaChecBox_OS());
               tableModel.setData(lista);
              jTabbedPane2.setSelectedIndex(0);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
    
    private String verificaChecbox_Servico(){
        String situacao = null;
        if(jCheckBoxSer_And.isSelected())
            situacao = "N";
        if(jCheckBoxSer_Fech.isSelected())
            situacao = "F";
        if(jCheckBoxSer_Par.isSelected())
            situacao = "P";
        return situacao;
    }
    
    private void consulta_Mec_situa(){
        IDMecanico mec = new DMecanico();
        if(!"".equals(jTextFieldCodigoMecanico.getText())){
            try {
                
               lista2 = mec.Lista_Mecanico_Situacao(jTextFieldCodigoMecanico.getText(), verificaChecbox_Servico());
                tableModel2.setData(lista2);
                jTabbedPane2.setSelectedIndex(1);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }
    
    private void consulta_Sit_Servi(){
        IDMecanico mec = new DMecanico();
        try {              
                lista2 = mec.Lista_Ser_Situacao(verificaChecbox_Servico());
                tableModel2.setData(lista2);
                jTabbedPane2.setSelectedIndex(1);
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabelLetrero = new javax.swing.JLabel();
        jLabel_DataHora = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldCodigoCli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNomeCli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNro_OS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jCheckBoxOSAberta = new javax.swing.JCheckBox();
        jCheckBoxOSFechada = new javax.swing.JCheckBox();
        jCheckBoxOSCancelada = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jCheckBoxSer_And = new javax.swing.JCheckBox();
        jCheckBoxSer_Par = new javax.swing.JCheckBox();
        jCheckBoxSer_Fech = new javax.swing.JCheckBox();
        jTextFieldCodigoMecanico = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxMecanico = new javax.swing.JComboBox();
        jButtonConsulta = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_OS = new JTable(tableModel){
            public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
                Component c = super.prepareRenderer(renderer, row, column);

                if(!isRowSelected(row)){
                    c.setBackground(getBackground());
                    int linha = convertRowIndexToModel(row);

                    String situacao = getModel().getValueAt(linha, 9).toString();
                    if(situacao.equals("9")){

                        c.setForeground(Color.DARK_GRAY);

                    }else{
                        c.setForeground(new Color(0,168,89));
                        //c.setForeground(new Color(0,175,239));
                    }

                }
                return c;
            }

        };
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabeltotalOS = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableServico = new JTable(tableModel2){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
                Component component = super.prepareRenderer(renderer, row, column);

                if(!isRowSelected(row)){
                    component.setBackground(getBackground());
                    int linha = convertRowIndexToModel(row);
                    String situacao = getModel().getValueAt(linha, 5).toString();

                    if(situacao.equals("P")){
                        component.setForeground(Color.YELLOW);
                    }else if(situacao.equals("F")){
                        component.setForeground(Color.RED);
                    }else if(situacao.equals("O")){
                        component.setForeground(Color.GREEN);
                    }else if(situacao.equals("N")){
                        component.setForeground(Color.BLUE);
                    }else if(situacao.equals("E")){
                        component.setForeground(Color.BLUE);
                    }

                }

                return component;

            }
        };
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setLayout(null);

        jLabelLetrero.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabelLetrero.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLetrero.setText("Letreiro");
        jPanel12.add(jLabelLetrero);
        jLabelLetrero.setBounds(0, 10, 600, 26);

        jLabel_DataHora.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_DataHora.setText("Data/Hora");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel_DataHora)))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_DataHora)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Capa", jPanel2);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clientes16x16.png"))); // NOI18N
        jLabel7.setText("Código Cliente");

        jTextFieldCodigoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clientes16x16.png"))); // NOI18N
        jLabel8.setText("Nome Cliente");

        jTextFieldNomeCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/relatorio16x16.png"))); // NOI18N
        jLabel9.setText("Nro O.S");

        jTextFieldNro_OS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/transportadoras16x16.png"))); // NOI18N
        jLabel10.setText("Placa");

        jTextFieldPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Situação O.S"));

        jCheckBoxOSAberta.setText("Aberta");

        jCheckBoxOSFechada.setText("Fechada");

        jCheckBoxOSCancelada.setText("Cancelada");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxOSAberta)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxOSFechada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxOSCancelada)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheckBoxOSAberta)
                .addComponent(jCheckBoxOSFechada)
                .addComponent(jCheckBoxOSCancelada))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Situação Serviço"));

        jCheckBoxSer_And.setText("Andamento");

        jCheckBoxSer_Par.setText("Parado");

        jCheckBoxSer_Fech.setText("Fechado");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxSer_And)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxSer_Par)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxSer_Fech)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSer_And)
                    .addComponent(jCheckBoxSer_Par)
                    .addComponent(jCheckBoxSer_Fech)))
        );

        jTextFieldCodigoMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/16x16 mechanic.png"))); // NOI18N
        jLabel11.setText("Mecânico");

        jComboBoxMecanico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default" }));
        jComboBoxMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMecanicoActionPerformed(evt);
            }
        });

        jButtonConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Pesquisar16x16.png"))); // NOI18N
        jButtonConsulta.setText("Consultar");
        jButtonConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todas_Consultas(evt);
            }
        });

        jButtonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/16x16Clear.png"))); // NOI18N
        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldNro_OS, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldCodigoCli, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel9))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jTextFieldCodigoMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBoxMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jTextFieldNomeCli)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNro_OS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpar)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Filtro", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1335, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuração", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 51, 204));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable_OS.setAutoCreateRowSorter(true);
        jTable_OS.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jTable_OS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_OSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_OS);

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/OSAberta.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/OSFechada.png"))); // NOI18N

        jLabel3.setText("O.S Abertas");

        jLabel4.setText("O.S Fechada");

        jLabeltotalOS.setText("total O.S");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabeltotalOS)
                .addGap(108, 108, 108))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabeltotalOS)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Ordem de Serviço", jPanel8);

        jTableServico.setAutoCreateRowSorter(true);
        jTableServico.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(jTableServico);

        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Parado.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Andamento.png"))); // NOI18N

        jLabel6.setText("Fechado");

        jLabel12.setText("Andamento");

        jLabel13.setText("Parado");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Fechado.png"))); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Serviços da O.S ", jPanel9);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Empresa 2.1 [PERNAMBUCO SERVICE]");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(614, 614, 614)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_OSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_OSMouseClicked
        if(evt.getClickCount() == 2){ //Verifica se ouver duplo click na tabela
           jTabbedPane2.setSelectedIndex(1);
           
           new ThreadClicked_Table().start();
           
       }
    }//GEN-LAST:event_jTable_OSMouseClicked

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limpaCampo();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        limpaCampo();
        jTabbedPane2.setSelectedIndex(0);
        osAberta_Fechada_Cancelada();
        totalOS();
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jComboBoxMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMecanicoActionPerformed
        IDMecanico mec = new DMecanico();
        if(!"Default".equals(jComboBoxMecanico.getSelectedItem())){    
            ArrayList<Mecanico> lista = new ArrayList<>();
            //Pega a posição selecionada do jCombobox
            int posicao = jComboBoxMecanico.getSelectedIndex();

            try {
                lista = mec.ListaNome_Mecanico();
                jTextFieldCodigoMecanico.setText(lista.get(posicao-1).getMecanico());
            } catch (ErroConexaoSQL ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroDao ex) {
                Logger.getLogger(jFPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBoxMecanicoActionPerformed

    private void todas_Consultas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todas_Consultas
        consulta_cli_Nro_OS();
            consulta_cli_Placa();
            
           if(((!"".equals(jTextFieldCodigoCli.getText())) && (jCheckBoxOSAberta.isSelected())) || ((!"".equals(jTextFieldCodigoCli.getText())) && (jCheckBoxOSFechada.isSelected())) || ((!"".equals(jTextFieldCodigoCli.getText())) && (jCheckBoxOSCancelada.isSelected()))){
               consulta_cli_Situ_OSCodigo();
           }else{
               conslta_cli_Codigo();
               
                if((jCheckBoxOSAberta.isSelected()) || (jCheckBoxOSFechada.isSelected()) || (jCheckBoxOSCancelada.isSelected())) {
                     osAberta_Fechada_Cancelada();
                }
 
           }
           if(((!"".equals(jTextFieldNomeCli.getText())) && (jCheckBoxOSAberta.isSelected())) || ((!"".equals(jTextFieldNomeCli.getText())) && (jCheckBoxOSFechada.isSelected())) || ((!"".equals(jTextFieldNomeCli.getText())) && (jCheckBoxOSCancelada.isSelected()))){
               consulta_cli_Situ_OSNome();
           }else{
               consulta_cli_Nome();
           }
           
           if((jCheckBoxSer_And.isSelected()) || (jCheckBoxSer_Par.isSelected()) || (jCheckBoxSer_Fech.isSelected())){
             
                if(((!"".equals(jTextFieldCodigoMecanico.getText())) && (jCheckBoxSer_And.isSelected())) || ((!"".equals(jTextFieldCodigoMecanico.getText())) && (jCheckBoxSer_Par.isSelected())) || ((!"".equals(jTextFieldCodigoMecanico.getText())) && (jCheckBoxSer_Fech.isSelected()))) {
                        consulta_Mec_situa();
                }else{
                    consulta_Sit_Servi();
                }    
           }else{
                if(!"".equals(jTextFieldCodigoMecanico.getText())){
                    nomeMecanico();
                }
                if(!"Default".equals(jComboBoxMecanico.getSelectedItem())) {
                    servicoMecanico();
                }
           }
           
          
           
           
        
        totalOS();
    }//GEN-LAST:event_todas_Consultas

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jFPainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFPainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFPainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFPainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFPainel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JCheckBox jCheckBoxOSAberta;
    private javax.swing.JCheckBox jCheckBoxOSCancelada;
    private javax.swing.JCheckBox jCheckBoxOSFechada;
    private javax.swing.JCheckBox jCheckBoxSer_And;
    private javax.swing.JCheckBox jCheckBoxSer_Fech;
    private javax.swing.JCheckBox jCheckBoxSer_Par;
    private javax.swing.JComboBox jComboBoxMecanico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLetrero;
    private javax.swing.JLabel jLabel_DataHora;
    private javax.swing.JLabel jLabeltotalOS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableServico;
    private javax.swing.JTable jTable_OS;
    private javax.swing.JTextField jTextFieldCodigoCli;
    private javax.swing.JTextField jTextFieldCodigoMecanico;
    private javax.swing.JTextField jTextFieldNomeCli;
    private javax.swing.JTextField jTextFieldNro_OS;
    private javax.swing.JTextField jTextFieldPlaca;
    // End of variables declaration//GEN-END:variables
}
