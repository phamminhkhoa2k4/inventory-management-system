package inventory.form;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import inventory.database.ExecuteQuery;
import inventory.event.SearchOptinEvent;
import inventory.message.MessageOptionDiaLog;
import inventory.swing.SearchOption;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import inventory.message.MessageDiaLog;
import inventory.table.ReportOption;
import java.awt.Component;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

//import test.PageConfigDialog;
//import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintOrientation;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.openxmlformats.schemas.drawingml.x2006.main.STPaperSize;

public class SynthesisReportPage extends javax.swing.JPanel {

    public SynthesisReportPage() {
        initComponents();
        setOpaque(false);
        //        call method display data table
        DisplayDataTable();

        //       search 
        txt.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txt.setHint("Search by " + option.getName() + "...");
            }
        });
        txt.addOption(new SearchOption("Date", new ImageIcon(getClass().getResource("/inventory/icon/calendar.png"))));

        setBackground(new Color(30, 30, 30));
        table.fixTable(jScrollPane1);
    }

    //      display data on table
    public void DisplayDataTable() {
        String SQL = "Select * From account";
        ResultSet rs = ExecuteQuery.ExecuteSyntaxSelect(SQL);
//      create a DefaultTableModel take from account table 
        DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
//        create a array include 5 element to capacity colunm value in table 
        Object obj[] = new Object[5];
        try {
            while (rs.next()) { 
                obj[0] = rs.getString("account_id");
                obj[1] = rs.getString("Username");
                obj[2] = rs.getString("Password");
                obj[3] = rs.getString("Avatar");
                obj[4] = rs.getString("Position");

                tbModel.addRow(obj);

            }
        } catch (SQLException ex) {
            System.out.println("loi lay data");
        }
    }

    public int orientation = 0;

    public void printToPdf(JTable table, String paperSize, int orientation) {

        try {
            // Mở hộp thoại chọn nơi lưu file và đặt mặc định là thư mục hiện tại
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            // Đặt bộ lọc cho hộp thoại để chỉ cho phép lưu file với định dạng PDF
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
            fileChooser.setFileFilter(filter);

            // Hiển thị hộp thoại lưu file và lấy đường dẫn được chọn
            int result = fileChooser.showSaveDialog(null);
            File selectedFile = null;
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().endsWith(".pdf")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".pdf");
                }
            } else {
                return;
            }

            // Tạo đối tượng Document với kích cỡ giấy và orientation được chọn
            Rectangle pageSize = PageSize.LETTER;
            if (paperSize.equals("A4")) {
                pageSize = PageSize.A4;
            } else if (paperSize.equals("LETTER")) {
                pageSize = PageSize.LETTER;
            }
            Document document = new Document(pageSize.rotate());
            if (orientation == 1) {
                document.setPageSize(pageSize.rotate());
            }

            // Tạo đối tượng PdfWriter để ghi dữ liệu vào file (sử dụng đường dẫn được chọn)
            PdfWriter.getInstance(document, new FileOutputStream(selectedFile));

            // Mở file để bắt đầu ghi dữ liệu
            document.open();

            //Thêm nội dung tiêu đề
            Paragraph title = new Paragraph("Synthesis Report");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Thêm khoảng trắng
            Chunk chunk = new Chunk(" ");
            document.add(chunk);

            // Tạo đối tượng PdfPTable với số cột bằng số cột của JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // Thêm các cột từ JTable vào PdfPTable
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // Thêm các dòng từ JTable vào PdfPTable
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    TableCellRenderer renderer = table.getCellRenderer(i, j);
                    Component component = table.prepareRenderer(renderer, i, j);
                    pdfTable.addCell(new PdfPCell(componentToPdfCell(component)));
                }
            }

            // Thêm PdfPTable vào Document
            document.add(pdfTable);

            // Đóng Document để kết thúc việc ghi dữ liệu
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell componentToPdfCell(Component component) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Phrase(component.getName()));
        return cell;
    }

//private void printToWord(JTable table) {
//    try {
//        // Hiển thị hộp thoại lưu file để người dùng chọn vị trí lưu trữ và tên file
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Export to Word");
//        fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Word Document (*.docx)", "docx"));
//        int userSelection = fileChooser.showSaveDialog(this);
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            String filePath = fileToSave.getAbsolutePath();
//            if (!filePath.toLowerCase().endsWith(".docx")) {
//                filePath += ".docx";
//            }
//
//            // Tạo file Word
//            XWPFDocument document = new XWPFDocument();
//            XWPFTable wordTable = document.createTable();
//            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//            
//            // ...
//            // Tiếp tục với phần còn lại của phương thức printToWord
//            // ...
//            
//            // Lưu file và hiển thị thông báo cho người dùng
//            FileOutputStream out = new FileOutputStream(filePath);
//            document.write(out);
//            out.close();
//            document.close();
//            JOptionPane.showMessageDialog(null, "Table export to MS Word Success!");
//        }
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//}
    private void printToWord(JTable table, String paperSize, int orientation) {
        try {
            // Hiển thị hộp thoại lưu file để người dùng chọn vị trí lưu trữ và tên file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export to Word");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Word Document (*.docx)", "docx"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".docx")) {
                    filePath += ".docx";
                }

                // Tạo file Word
                XWPFDocument document = new XWPFDocument();

                // Tạo bảng word và lấy dữ liệu từ JTable
                XWPFTable wordTable = document.createTable();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int rowCount = tableModel.getRowCount();
                int colCount = tableModel.getColumnCount();
                for (int i = 0; i < rowCount; i++) {
                    XWPFTableRow tableRow = wordTable.createRow();
                    for (int j = 0; j < colCount; j++) {
                        XWPFParagraph para = tableRow.getCell(j).addParagraph();
                        para.createRun().setText(tableModel.getValueAt(i, j).toString());
                    }
                }

                // Thiết lập kích thước giấy in
                int width = 0;
                int height = 0;
                if (orientation == 1) {
                    // portrait
                    switch (paperSize.toLowerCase()) {
                        case "a4":
                            width = 210;
                            height = 297;
                            break;
                        case "letter":
                            width = (int) 215.9;
                            height = (int) 279.4;
                            break;
                        default:
                            // default values for other paper sizes
                            break;
                    }
                } else {
                    // landscape
                    switch (paperSize.toLowerCase()) {
                        case "a4":
                            width = 297;
                            height = 210;
                            break;
                        case "letter":
                            width = (int) 279.4;
                            height = (int) 215.9;
                            break;
                        default:
                            // default values for other paper sizes
                            break;
                    }
                }
                document.getDocument().getBody().addNewSectPr().addNewPgSz().setW(BigInteger.valueOf(width * 20));
                document.getDocument().getBody().addNewSectPr().addNewPgSz().setH(BigInteger.valueOf(height * 20));

                // Xuất file Word và thông báo thành công cho người dùng
                FileOutputStream out = new FileOutputStream(filePath);
                document.write(out);
                out.close();
                JOptionPane.showMessageDialog(null, "Table exported to Microsoft Word successfully!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printToExcel(JTable table, String paperSize, int orientation) throws IOException, PrinterException {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Create a new Workbook and a Sheet
            Workbook workbook = new HSSFWorkbook(); // or new XSSFWorkbook() for .xlsx files
            Sheet sheet = workbook.createSheet();

            // Create a Font for styling cells
            Font headerFont = workbook.createFont();
//            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            // Create a CellStyle with the Font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row for the header
            Row headerRow = sheet.createRow(0);

            // Create header cells
            for (int i = 0; i < table.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
                cell.setCellStyle(headerCellStyle);
            }

            // Create data rows
            for (int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);

                for (int j = 0; j < table.getColumnCount(); j++) {
                    row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                }
            }

            // Resize columns to fit their content
            for (int i = 0; i < table.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Set paper size
            PageFormat pageFormat = new PageFormat();
            Paper paper = new Paper();
            if (paperSize.equals("A4")) {
                paper.setSize(595.0, 842.0); // A4 size in points
            } else if (paperSize.equals("A5")) {
                paper.setSize(420.0, 595.0); // A5 size in points
            } else {
                throw new IllegalArgumentException("Invalid paper size specified.");
            }
            paper.setImageableArea(0.0, 0.0, paper.getWidth(), paper.getHeight());
            pageFormat.setPaper(paper);

            // Set print options
            PrinterJob printJob = PrinterJob.getPrinterJob();
//            printJob.setPrintable(new ExcelPrintable(workbook, sheet), pageFormat);
            // Set orientation
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            if (orientation == 1) {
                attributes.add(OrientationRequested.PORTRAIT);
            } else if (orientation == 0 ) {
                attributes.add(OrientationRequested.LANDSCAPE);
            } 
            printJob.print(attributes);

            // Closing the workbook
//            workbook.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollButton1 = new inventory.table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new inventory.table.TableDark();
        jPanel1 = new javax.swing.JPanel();
        txt = new inventory.swing.TextFieldSearchOption();
        PrintWORDBtn = new inventory.swing.Button();
        PrintEXCELBtn = new inventory.swing.Button();
        PrintPDFBtn = new inventory.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        Portrait = new inventory.swing.RadioButtonCustom();
        Landscape = new inventory.swing.RadioButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        PageSizeOption = new inventory.swing.ComboBoxSuggestion();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number of Warehouses", "Category", "Amount Sold", "Quantity of Inventory", "Profit", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PrintWORDBtn.setBackground(new java.awt.Color(0, 51, 204));
        PrintWORDBtn.setForeground(new java.awt.Color(255, 255, 255));
        PrintWORDBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/doc.png"))); // NOI18N
        PrintWORDBtn.setText("Print Out a WORD File");
        PrintWORDBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintWORDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintWORDBtnActionPerformed(evt);
            }
        });

        PrintEXCELBtn.setBackground(new java.awt.Color(0, 255, 51));
        PrintEXCELBtn.setForeground(new java.awt.Color(255, 255, 255));
        PrintEXCELBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/xls-file.png"))); // NOI18N
        PrintEXCELBtn.setText("Print Out a EXCEL File");
        PrintEXCELBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintEXCELBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintEXCELBtnActionPerformed(evt);
            }
        });

        PrintPDFBtn.setBackground(new java.awt.Color(255, 153, 51));
        PrintPDFBtn.setForeground(new java.awt.Color(255, 255, 255));
        PrintPDFBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon/pdf-file.png"))); // NOI18N
        PrintPDFBtn.setText("Print Out a PDF File");
        PrintPDFBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PrintPDFBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintPDFBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Page Size :");

        Portrait.setText("Portrait");
        Portrait.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Landscape.setSelected(true);
        Landscape.setText("Landscape");
        Landscape.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Oriention :");

        PageSizeOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A4", "A5", "A6", "A7", "A8", "A9", "A10", "B0" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 95, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Landscape, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Portrait, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PageSizeOption, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrintWORDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrintEXCELBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrintPDFBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PageSizeOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Landscape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Portrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(PrintPDFBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(PrintEXCELBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(PrintWORDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void PrintPDFBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintPDFBtnActionPerformed
        String paperSize = (String) PageSizeOption.getSelectedItem();
//        int orientation = 0;
        Landscape.addActionListener(e -> {
            orientation = 0;
        });
        Portrait.addActionListener(e -> {
            orientation = 1;
        });

        printToPdf(table, paperSize, orientation);
    }//GEN-LAST:event_PrintPDFBtnActionPerformed

    private void PrintEXCELBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintEXCELBtnActionPerformed
        String paperSize = (String) PageSizeOption.getSelectedItem(); 
          Landscape.addActionListener(e -> {
            orientation = 0;
        });
        Portrait.addActionListener(e -> {
            orientation = 1;
        });
        try {
            printToExcel(table,paperSize,orientation);
        } catch (IOException ex) {
            Logger.getLogger(SynthesisReportPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrinterException ex) {
            Logger.getLogger(SynthesisReportPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PrintEXCELBtnActionPerformed

    private void PrintWORDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintWORDBtnActionPerformed
        String paperSize = (String) PageSizeOption.getSelectedItem();
        Landscape.addActionListener(e -> {
            orientation = 0;
        });
        Portrait.addActionListener(e -> {
            orientation = 1;
        });
        printToWord(table, paperSize, orientation);

    }//GEN-LAST:event_PrintWORDBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private inventory.swing.RadioButtonCustom Landscape;
    private inventory.swing.ComboBoxSuggestion PageSizeOption;
    private inventory.swing.RadioButtonCustom Portrait;
    private inventory.swing.Button PrintEXCELBtn;
    private inventory.swing.Button PrintPDFBtn;
    private inventory.swing.Button PrintWORDBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private inventory.table.TableDark table;
    private inventory.table.TableScrollButton tableScrollButton1;
    private inventory.swing.TextFieldSearchOption txt;
    // End of variables declaration//GEN-END:variables
}
