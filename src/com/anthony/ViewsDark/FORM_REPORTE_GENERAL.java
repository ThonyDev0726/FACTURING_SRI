package com.anthony.ViewsDark;

import VO.ArchivosVO;
import com.anthony.MainDark.MainAdministradorDark;
import com.anthony.Models.*;
import com.anthony.ModelsDAO.*;
import com.anthony.VisorPdf.JnaFileChooser;
import com.anthony.swing.scrollbar.ScrollBarCustom;
import com.anthony.toast.Toast;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mivisorpdf.MiVisorPDF;

public class FORM_REPORTE_GENERAL extends javax.swing.JPanel {

    /* ============================
    VARIABLES DB
    ============================ */
    CLIENTE cli = new CLIENTE();
    CLIENTE_DAO daoCli = new CLIENTE_DAO();
    EMPRESA emp = new EMPRESA();
    EMPRESA_DAO empDao = new EMPRESA_DAO();
    EMPLEADO empleado = new EMPLEADO();
    EMPLEADO_DAO empleadoDao = new EMPLEADO_DAO();
    PROVEEDOR prov = new PROVEEDOR();
    PROVEEDOR_DAO provDao = new PROVEEDOR_DAO();
    PRODUCTO pro = new PRODUCTO();
    PRODUCTO_DAO proDao = new PRODUCTO_DAO();
    FACTURA_DAO daoFac = new FACTURA_DAO();
    FACTURA fac = new FACTURA();
    USUARIO_DAO daoUsu = new USUARIO_DAO();
    USUARIO usu = new USUARIO();
    RoundBorder border = new RoundBorder(0);
    Calendar fecha = Calendar.getInstance();
    int year = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH) + 1;
    /* ============================
    VARIABLES PDF
    ============================ */
    Font negrita = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
    Font negritaSmall = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);
    Font negritaSmallBold = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
    Font negritaMediuamBold = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
    Font ExtraBold = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    Font normalBold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
    Font normalBlanco = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
    Font normalBoldAzul = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLUE);
    Font normal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
    com.itextpdf.text.Paragraph VACIO = new com.itextpdf.text.Paragraph("RichardPerez", normalBlanco);

    /* ============================
        CONSTRUCTORES
    ============================ */
    public FORM_REPORTE_GENERAL() {
        initComponents();
        scroll();
        init();
    }

    public FORM_REPORTE_GENERAL(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        scroll();
        init();
    }

    private void init() {

    }

    private void scroll() {
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
    }

    /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
//    private void reporteClientes() throws FileNotFoundException, DocumentException, BadElementException, IOException {
//        /* ============================
//        DATOS PRINCIPALES FECHAS
//        ============================ */
//
//        String MES = "";
//        switch (mes) {
//            case 1:
//                MES = "ENERO";
//                break;
//            case 2:
//                MES = "FEBRERO";
//                break;
//            case 3:
//                MES = "MARZO";
//                break;
//            case 4:
//                MES = "ABRIL";
//                break;
//            case 5:
//                MES = "MAYO";
//                break;
//            case 6:
//                MES = "JUNIO";
//                break;
//            case 7:
//                MES = "JULIO";
//                break;
//            case 8:
//                MES = "AGOSTO";
//                break;
//            case 9:
//                MES = "SEPTIEMRBRE";
//                break;
//            case 10:
//                MES = "OCTUBRE";
//                break;
//            case 11:
//                MES = "NOVIEMBRE";
//                break;
//            case 12:
//                MES = "DICIEMBRE";
//                break;
//            default:
//                throw new AssertionError();
//        }
//        /* ============================
//        DATOS PRINCIPALES DEL DOCUMENTO
//        ============================ */
//        FileOutputStream archivo;
//        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/CLIENTES/" + "REP" + ".pdf";
//        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
//        archivo = new FileOutputStream(file);
//        Document doc = new Document();
//        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
//        doc.open();
//
//        /* ============================
//        FUENTES Y TIPOS
//        ============================ */
//        //Espacio 
//        Paragraph espacio = new Paragraph();
//        espacio.add("\n");
//        /* ============================
//        PARTE ENCABEZADO
//        ============================ */
//        PdfPTable encabezado = new PdfPTable(4);
//        encabezado.setWidthPercentage(100);
//        encabezado.getDefaultCell().setBorder(0);
//        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
//        encabezado.setWidths(ColumnaEncabezado);
//        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
//        //VARIABLES EMPRESA
//        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
//        emp = empDao.list();
//        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
//                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
//                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
//                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
//                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
//        /* ============================
//        AGREGAR TABLA DATOS FACTURA
//        ============================ */
//        //ENVIO DATOS A LA TABLA
//        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
//        encabezado.addCell(img);//img empresa
//        encabezado.addCell(parrafoEmpresa);//datos empresa
//        encabezado.addCell("");//espacio
//
//        /* ============================
//        AGREGAR CODIGO DE BARRAS
//        ============================ */
//        PdfPTable codigoBarras = new PdfPTable(1);
//        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        Barcode128 b = new Barcode128();
//        b.setCode("REPORTE CLIENTE 0000001");
//        b.setBarHeight(40);
//        PdfPCell barCodeCell = new PdfPCell();
//        barCodeCell.setBorder(0);
//        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
//        codigoBarras.setWidthPercentage(40f);
//        codigoBarras.addCell(barCodeCell);
//
//        encabezado.addCell(codigoBarras);//datos factura
//        /* ============================
//        DATOS DB
//        ============================ */
//        PdfPTable datosClientesMain = new PdfPTable(1);
//        datosClientesMain.setWidthPercentage(100);
//        PdfPTable datosClientes = new PdfPTable(4);
//        datosClientesMain.getDefaultCell().setBorder(0);
//        float[] columnaCliente = new float[]{30f, 60f, 30f, 60f};
//        datosClientes.setWidths(columnaCliente);
//        datosClientes.getDefaultCell().setBorder(0);
//        List<CLIENTE> listCh = daoCli.listar();
//        Iterator<CLIENTE> iterCli = listCh.iterator();
//        cli = null;
//        while (iterCli.hasNext()) {
//            cli = iterCli.next();
//            com.itextpdf.text.Paragraph CLI_ID = new com.itextpdf.text.Paragraph("CLIENTE:", normalBoldAzul);
//            com.itextpdf.text.Paragraph CLI_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(cli.getID_CLIENTE()), normalBold);
//            com.itextpdf.text.Paragraph CLI_NOMBRES = new com.itextpdf.text.Paragraph("Nombres", normalBold);
//            com.itextpdf.text.Paragraph CLI_NOMBRES_DB = new com.itextpdf.text.Paragraph(cli.getCLI_NOMBRES(), normal);
//            com.itextpdf.text.Paragraph CLI_APELLIDOS = new com.itextpdf.text.Paragraph("Apellidos", normalBold);
//            com.itextpdf.text.Paragraph CLI_APELLIDOS_DB = new com.itextpdf.text.Paragraph(cli.getCLI_APELLIDOS(), normal);
//            com.itextpdf.text.Paragraph CLI_CEDULA = new com.itextpdf.text.Paragraph("Cedula", normalBold);
//            com.itextpdf.text.Paragraph CLI_CEDULA_DB = new com.itextpdf.text.Paragraph(cli.getCLI_CEDULA(), normal);
//            com.itextpdf.text.Paragraph CLI_RUC = new com.itextpdf.text.Paragraph("RUC", normalBold);
//            com.itextpdf.text.Paragraph CLI_RUC_DB = new com.itextpdf.text.Paragraph(cli.getCLI_RUC(), normal);
//            com.itextpdf.text.Paragraph CLI_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
//            com.itextpdf.text.Paragraph CLI_TELEFONO_DB = new com.itextpdf.text.Paragraph(cli.getCLI_TELEFONO(), normal);
//            com.itextpdf.text.Paragraph CLI_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
//            com.itextpdf.text.Paragraph CLI_DIRECCION_DB = new com.itextpdf.text.Paragraph(cli.getCLI_DIRECCION(), normal);
//            com.itextpdf.text.Paragraph CLI_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
//            com.itextpdf.text.Paragraph CLI_EMAIL_DB = new com.itextpdf.text.Paragraph(cli.getCLI_EMAIL(), normal);
//            com.itextpdf.text.Paragraph CLI_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
//            com.itextpdf.text.Paragraph CLI_ESTADO_DB = new com.itextpdf.text.Paragraph(cli.getCLI_ESTADO() + "\n", normal);
//            datosClientes.addCell(CLI_ID);
//            datosClientes.addCell(CLI_ID_DB);
//            datosClientes.addCell(VACIO);
//            datosClientes.addCell(VACIO);
//            datosClientes.addCell(CLI_NOMBRES);
//            datosClientes.addCell(CLI_NOMBRES_DB);
//            datosClientes.addCell(CLI_APELLIDOS);
//            datosClientes.addCell(CLI_APELLIDOS_DB);
//            datosClientes.addCell(CLI_CEDULA);
//            datosClientes.addCell(CLI_CEDULA_DB);
//            datosClientes.addCell(CLI_RUC);
//            datosClientes.addCell(CLI_RUC_DB);
//            datosClientes.addCell(CLI_TELEFONO);
//            datosClientes.addCell(CLI_TELEFONO_DB);
//            datosClientes.addCell(CLI_DIRECCION);
//            datosClientes.addCell(CLI_DIRECCION_DB);
//            datosClientes.addCell(CLI_EMAIL);
//            datosClientes.addCell(CLI_EMAIL_DB);
//            datosClientes.addCell(CLI_ESTADO);
//            datosClientes.addCell(CLI_ESTADO_DB);
//            datosClientes.addCell(VACIO);
//            datosClientes.addCell(VACIO);
//            datosClientes.addCell(VACIO);
//            datosClientes.addCell(VACIO);
//        }
//
//        //agregarmos a la tabladatosClientesMain
//        datosClientesMain.addCell(datosClientes);
//
//        /* ============================
//        AGREGAR DATOS AL DOCUMENTO
//        ============================ */
//        doc.add(encabezado);
//        doc.add(espacio);
//        doc.add(datosClientesMain);
//        doc.close();
////        ruta_archivo = ruta;
////        abrir_pdf(ruta_archivo);
//    }
//
//    private void reporteEmpleados() throws FileNotFoundException, DocumentException, BadElementException, IOException {
//        /* ============================
//        DATOS PRINCIPALES FECHAS
//        ============================ */
//
//        String MES = "";
//        switch (mes) {
//            case 1:
//                MES = "ENERO";
//                break;
//            case 2:
//                MES = "FEBRERO";
//                break;
//            case 3:
//                MES = "MARZO";
//                break;
//            case 4:
//                MES = "ABRIL";
//                break;
//            case 5:
//                MES = "MAYO";
//                break;
//            case 6:
//                MES = "JUNIO";
//                break;
//            case 7:
//                MES = "JULIO";
//                break;
//            case 8:
//                MES = "AGOSTO";
//                break;
//            case 9:
//                MES = "SEPTIEMRBRE";
//                break;
//            case 10:
//                MES = "OCTUBRE";
//                break;
//            case 11:
//                MES = "NOVIEMBRE";
//                break;
//            case 12:
//                MES = "DICIEMBRE";
//                break;
//            default:
//                throw new AssertionError();
//        }
//        /* ============================
//        DATOS PRINCIPALES DEL DOCUMENTO
//        ============================ */
//        FileOutputStream archivo;
//        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/EMPLEADOS/" + "REP" + ".pdf";
//        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
//        archivo = new FileOutputStream(file);
//        Document doc = new Document();
//        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
//        doc.open();
//
//        /* ============================
//        FUENTES Y TIPOS
//        ============================ */
//        //Espacio 
//        Paragraph espacio = new Paragraph();
//        espacio.add("\n");
//        /* ============================
//        PARTE ENCABEZADO
//        ============================ */
//        PdfPTable encabezado = new PdfPTable(4);
//        encabezado.setWidthPercentage(100);
//        encabezado.getDefaultCell().setBorder(0);
//        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
//        encabezado.setWidths(ColumnaEncabezado);
//        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
//        //VARIABLES EMPRESA
//        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
//        emp = empDao.list();
//        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
//                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
//                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
//                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
//                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
//        /* ============================
//        AGREGAR TABLA DATOS FACTURA
//        ============================ */
//        //ENVIO DATOS A LA TABLA
//        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
//        encabezado.addCell(img);//img empresa
//        encabezado.addCell(parrafoEmpresa);//datos empresa
//        encabezado.addCell("");//espacio
//
//        /* ============================
//        AGREGAR CODIGO DE BARRAS
//        ============================ */
//        PdfPTable codigoBarras = new PdfPTable(1);
//        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        Barcode128 b = new Barcode128();
//        b.setCode("REPORTE EMPLEADO 0000001");
//        b.setBarHeight(40);
//        PdfPCell barCodeCell = new PdfPCell();
//        barCodeCell.setBorder(0);
//        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
//        codigoBarras.setWidthPercentage(40f);
//        codigoBarras.addCell(barCodeCell);
//
//        encabezado.addCell(codigoBarras);//datos factura
//        /* ============================
//        DATOS DB
//        ============================ */
//        PdfPTable datosEmpleadosMain = new PdfPTable(1);
//        datosEmpleadosMain.setWidthPercentage(100);
//        PdfPTable datosEmpleados = new PdfPTable(4);
//        datosEmpleadosMain.getDefaultCell().setBorder(0);
//        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
//        datosEmpleados.setWidths(columnaEmpleados);
//        datosEmpleados.getDefaultCell().setBorder(0);
//        List<EMPLEADO> listEmp = empleadoDao.listar();
//        Iterator<EMPLEADO> iterEmp = listEmp.iterator();
//        empleado = null;
//        while (iterEmp.hasNext()) {
//            empleado = iterEmp.next();
//            com.itextpdf.text.Paragraph EMP_ID = new com.itextpdf.text.Paragraph("EMPLEADO:", normalBoldAzul);
//            com.itextpdf.text.Paragraph EMP_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(empleado.getID_EMPLEADO()), normalBold);
//            com.itextpdf.text.Paragraph EMP_NOMBRES = new com.itextpdf.text.Paragraph("Nombres", normalBold);
//            com.itextpdf.text.Paragraph EMP_NOMBRES_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_NOMBRES(), normal);
//            com.itextpdf.text.Paragraph EMP_APELLIDOS = new com.itextpdf.text.Paragraph("Apellidos", normalBold);
//            com.itextpdf.text.Paragraph EMP_APELLIDOS_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_APELLIDOS(), normal);
//            com.itextpdf.text.Paragraph EMP_CEDULA = new com.itextpdf.text.Paragraph("Cedula", normalBold);
//            com.itextpdf.text.Paragraph EMP_CEDULA_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_CEDULA(), normal);
//            com.itextpdf.text.Paragraph EMP_RUC = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
//            com.itextpdf.text.Paragraph EMP_RUC_DB = new com.itextpdf.text.Paragraph(empleado.getSUC_NOMBRE(), normal);
//            com.itextpdf.text.Paragraph EMP_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
//            com.itextpdf.text.Paragraph EMP_TELEFONO_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_TELEFONO(), normal);
//            com.itextpdf.text.Paragraph EMP_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
//            com.itextpdf.text.Paragraph EMP_DIRECCION_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_DIRECCION(), normal);
//            com.itextpdf.text.Paragraph EMP_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
//            com.itextpdf.text.Paragraph EMP_EMAIL_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_EMAIL(), normal);
//            com.itextpdf.text.Paragraph EMP_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
//            com.itextpdf.text.Paragraph EMP_ESTADO_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_ESTADO() + "\n", normal);
//            datosEmpleados.addCell(EMP_ID);
//            datosEmpleados.addCell(EMP_ID_DB);
//            datosEmpleados.addCell(VACIO);
//            datosEmpleados.addCell(VACIO);
//            datosEmpleados.addCell(EMP_NOMBRES);
//            datosEmpleados.addCell(EMP_NOMBRES_DB);
//            datosEmpleados.addCell(EMP_APELLIDOS);
//            datosEmpleados.addCell(EMP_APELLIDOS_DB);
//            datosEmpleados.addCell(EMP_CEDULA);
//            datosEmpleados.addCell(EMP_CEDULA_DB);
//            datosEmpleados.addCell(EMP_RUC);
//            datosEmpleados.addCell(EMP_RUC_DB);
//            datosEmpleados.addCell(EMP_TELEFONO);
//            datosEmpleados.addCell(EMP_TELEFONO_DB);
//            datosEmpleados.addCell(EMP_DIRECCION);
//            datosEmpleados.addCell(EMP_DIRECCION_DB);
//            datosEmpleados.addCell(EMP_EMAIL);
//            datosEmpleados.addCell(EMP_EMAIL_DB);
//            datosEmpleados.addCell(EMP_ESTADO);
//            datosEmpleados.addCell(EMP_ESTADO_DB);
//            datosEmpleados.addCell(VACIO);
//            datosEmpleados.addCell(VACIO);
//            datosEmpleados.addCell(VACIO);
//            datosEmpleados.addCell(VACIO);
//        }
//
//        //agregarmos a la tabladatosClientesMain
//        datosEmpleadosMain.addCell(datosEmpleados);
//
//        /* ============================
//        AGREGAR DATOS AL DOCUMENTO
//        ============================ */
//        doc.add(encabezado);
//        doc.add(espacio);
//        doc.add(datosEmpleadosMain);
//        doc.close();
////        ruta_archivo = ruta;
////        abrir_pdf(ruta_archivo);
//    }
//
//    private void reporteProveedores() throws FileNotFoundException, DocumentException, BadElementException, IOException {
//        /* ============================
//        DATOS PRINCIPALES FECHAS
//        ============================ */
//
//        String MES = "";
//        switch (mes) {
//            case 1:
//                MES = "ENERO";
//                break;
//            case 2:
//                MES = "FEBRERO";
//                break;
//            case 3:
//                MES = "MARZO";
//                break;
//            case 4:
//                MES = "ABRIL";
//                break;
//            case 5:
//                MES = "MAYO";
//                break;
//            case 6:
//                MES = "JUNIO";
//                break;
//            case 7:
//                MES = "JULIO";
//                break;
//            case 8:
//                MES = "AGOSTO";
//                break;
//            case 9:
//                MES = "SEPTIEMRBRE";
//                break;
//            case 10:
//                MES = "OCTUBRE";
//                break;
//            case 11:
//                MES = "NOVIEMBRE";
//                break;
//            case 12:
//                MES = "DICIEMBRE";
//                break;
//            default:
//                throw new AssertionError();
//        }
//        /* ============================
//        DATOS PRINCIPALES DEL DOCUMENTO
//        ============================ */
//        FileOutputStream archivo;
//        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/PROVEEDORES/" + "REP" + ".pdf";
//        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
//        archivo = new FileOutputStream(file);
//        Document doc = new Document();
//        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
//        doc.open();
//
//        /* ============================
//        FUENTES Y TIPOS
//        ============================ */
//        //Espacio 
//        Paragraph espacio = new Paragraph();
//        espacio.add("\n");
//        /* ============================
//        PARTE ENCABEZADO
//        ============================ */
//        PdfPTable encabezado = new PdfPTable(4);
//        encabezado.setWidthPercentage(100);
//        encabezado.getDefaultCell().setBorder(0);
//        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
//        encabezado.setWidths(ColumnaEncabezado);
//        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
//        //VARIABLES EMPRESA
//        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
//        emp = empDao.list();
//        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
//                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
//                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
//                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
//                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
//        /* ============================
//        AGREGAR TABLA DATOS FACTURA
//        ============================ */
//        //ENVIO DATOS A LA TABLA
//        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
//        encabezado.addCell(img);//img empresa
//        encabezado.addCell(parrafoEmpresa);//datos empresa
//        encabezado.addCell("");//espacio
//
//        /* ============================
//        AGREGAR CODIGO DE BARRAS
//        ============================ */
//        PdfPTable codigoBarras = new PdfPTable(1);
//        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        Barcode128 b = new Barcode128();
//        b.setCode("REPORTE PROVEEDOR 0000001");
//        b.setBarHeight(40);
//        PdfPCell barCodeCell = new PdfPCell();
//        barCodeCell.setBorder(0);
//        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
//        codigoBarras.setWidthPercentage(40f);
//        codigoBarras.addCell(barCodeCell);
//
//        encabezado.addCell(codigoBarras);//datos factura
//        /* ============================
//        DATOS DB
//        ============================ */
//        PdfPTable datosProveedorMain = new PdfPTable(1);
//        datosProveedorMain.setWidthPercentage(100);
//        PdfPTable datosProveedor = new PdfPTable(4);
//        datosProveedorMain.getDefaultCell().setBorder(0);
//        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
//        datosProveedor.setWidths(columnaEmpleados);
//        datosProveedor.getDefaultCell().setBorder(0);
//        List<PROVEEDOR> listProv = provDao.listar();
//        Iterator<PROVEEDOR> iterProv = listProv.iterator();
//        prov = null;
//        while (iterProv.hasNext()) {
//            prov = iterProv.next();
//            com.itextpdf.text.Paragraph PRO_ID = new com.itextpdf.text.Paragraph("PROVEEDOR:", normalBoldAzul);
//            com.itextpdf.text.Paragraph PRO_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(prov.getID_PROVEEDOR()), normalBold);
//            com.itextpdf.text.Paragraph PRO_WEB = new com.itextpdf.text.Paragraph("WEB:", normalBold);
//            com.itextpdf.text.Paragraph PRO_WEB_DB = new com.itextpdf.text.Paragraph(prov.getPRO_WEB(), normal);
//            com.itextpdf.text.Paragraph PRO_EMPRESA = new com.itextpdf.text.Paragraph("Empresa", normalBold);
//            com.itextpdf.text.Paragraph PRO_EMPRESA_DB = new com.itextpdf.text.Paragraph(prov.getPRO_EMPRESA(), normal);
//            com.itextpdf.text.Paragraph PRO_SUCURSAL = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
//            com.itextpdf.text.Paragraph PRO_SUCURSAL_DB = new com.itextpdf.text.Paragraph(prov.getSUC_NOMBRE(), normal);
//            com.itextpdf.text.Paragraph PRO_CONTACTO = new com.itextpdf.text.Paragraph("Contacto", normalBold);
//            com.itextpdf.text.Paragraph PRO_CONTACTO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_CONTACTO(), normal);
//            com.itextpdf.text.Paragraph PRO_RUC = new com.itextpdf.text.Paragraph("RUC", normalBold);
//            com.itextpdf.text.Paragraph PRO_RUC_DB = new com.itextpdf.text.Paragraph(prov.getPRO_RUC(), normal);
//            com.itextpdf.text.Paragraph PRO_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
//            com.itextpdf.text.Paragraph PRO_TELEFONO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_TELEFONO(), normal);
//            com.itextpdf.text.Paragraph PRO_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
//            com.itextpdf.text.Paragraph PRO_DIRECCION_DB = new com.itextpdf.text.Paragraph(prov.getPRO_DIRECCION(), normal);
//            com.itextpdf.text.Paragraph PRO_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
//            com.itextpdf.text.Paragraph PRO_EMAIL_DB = new com.itextpdf.text.Paragraph(prov.getPRO_EMAIL(), normal);
//            com.itextpdf.text.Paragraph PRO_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
//            com.itextpdf.text.Paragraph PRO_ESTADO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_ESTADO() + "\n", normal);
//            datosProveedor.addCell(PRO_ID);
//            datosProveedor.addCell(PRO_ID_DB);
//            datosProveedor.addCell(PRO_SUCURSAL);
//            datosProveedor.addCell(PRO_SUCURSAL_DB);
//            datosProveedor.addCell(PRO_EMPRESA);
//            datosProveedor.addCell(PRO_EMPRESA_DB);
//            datosProveedor.addCell(PRO_CONTACTO);
//            datosProveedor.addCell(PRO_CONTACTO_DB);
//            datosProveedor.addCell(PRO_RUC);
//            datosProveedor.addCell(PRO_RUC_DB);
//            datosProveedor.addCell(PRO_TELEFONO);
//            datosProveedor.addCell(PRO_TELEFONO_DB);
//            datosProveedor.addCell(PRO_DIRECCION);
//            datosProveedor.addCell(PRO_DIRECCION_DB);
//            datosProveedor.addCell(PRO_EMAIL);
//            datosProveedor.addCell(PRO_EMAIL_DB);
//            datosProveedor.addCell(PRO_WEB);
//            datosProveedor.addCell(PRO_WEB_DB);
//            datosProveedor.addCell(PRO_ESTADO);
//            datosProveedor.addCell(PRO_ESTADO_DB);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//        }
//
//        //agregarmos a la tabladatosClientesMain
//        datosProveedorMain.addCell(datosProveedor);
//
//        /* ============================
//        AGREGAR DATOS AL DOCUMENTO
//        ============================ */
//        doc.add(encabezado);
//        doc.add(espacio);
//        doc.add(datosProveedorMain);
//        doc.close();
////        ruta_archivo = ruta;
////        abrir_pdf(ruta_archivo);
//    }
//
//    private void reporteProductos() throws FileNotFoundException, DocumentException, BadElementException, IOException {
//        /* ============================
//        DATOS PRINCIPALES FECHAS
//        ============================ */
//
//        String MES = "";
//        switch (mes) {
//            case 1:
//                MES = "ENERO";
//                break;
//            case 2:
//                MES = "FEBRERO";
//                break;
//            case 3:
//                MES = "MARZO";
//                break;
//            case 4:
//                MES = "ABRIL";
//                break;
//            case 5:
//                MES = "MAYO";
//                break;
//            case 6:
//                MES = "JUNIO";
//                break;
//            case 7:
//                MES = "JULIO";
//                break;
//            case 8:
//                MES = "AGOSTO";
//                break;
//            case 9:
//                MES = "SEPTIEMRBRE";
//                break;
//            case 10:
//                MES = "OCTUBRE";
//                break;
//            case 11:
//                MES = "NOVIEMBRE";
//                break;
//            case 12:
//                MES = "DICIEMBRE";
//                break;
//            default:
//                throw new AssertionError();
//        }
//        /* ============================
//        DATOS PRINCIPALES DEL DOCUMENTO
//        ============================ */
//        FileOutputStream archivo;
//        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/PRODUCTOS/" + "REP7" + ".pdf";
//        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
//        archivo = new FileOutputStream(file);
//        Document doc = new Document();
//        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
//        doc.open();
//
//        /* ============================
//        FUENTES Y TIPOS
//        ============================ */
//        //Espacio 
//        Paragraph espacio = new Paragraph();
//        espacio.add("\n");
//        /* ============================
//        PARTE ENCABEZADO
//        ============================ */
//        PdfPTable encabezado = new PdfPTable(4);
//        encabezado.setWidthPercentage(100);
//        encabezado.getDefaultCell().setBorder(0);
//        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
//        encabezado.setWidths(ColumnaEncabezado);
//        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
//        //VARIABLES EMPRESA
//        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
//        emp = empDao.list();
//        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
//                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
//                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
//                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
//                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
//        /* ============================
//        AGREGAR TABLA DATOS FACTURA
//        ============================ */
//        //ENVIO DATOS A LA TABLA
//        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
//        encabezado.addCell(img);//img empresa
//        encabezado.addCell(parrafoEmpresa);//datos empresa
//        encabezado.addCell("");//espacio
//
//        /* ============================
//        AGREGAR CODIGO DE BARRAS
//        ============================ */
//        PdfPTable codigoBarras = new PdfPTable(1);
//        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        Barcode128 b = new Barcode128();
//        b.setCode("REPORTE PRODUCTO 0000001");
//        b.setBarHeight(40);
//        PdfPCell barCodeCell = new PdfPCell();
//        barCodeCell.setBorder(0);
//        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
//        codigoBarras.setWidthPercentage(40f);
//        codigoBarras.addCell(barCodeCell);
//
//        encabezado.addCell(codigoBarras);//datos factura
//        /* ============================
//        DATOS DB
//        ============================ */
//        PdfPTable datosProveedorMain = new PdfPTable(1);
//        datosProveedorMain.setWidthPercentage(100);
//        PdfPTable datosProveedor = new PdfPTable(4);
//        datosProveedorMain.getDefaultCell().setBorder(0);
//        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
//        datosProveedor.setWidths(columnaEmpleados);
//        datosProveedor.getDefaultCell().setBorder(0);
//        List<PRODUCTO> listPro = proDao.listar();
//        Iterator<PRODUCTO> iterPro = listPro.iterator();
//        pro = null;
//        while (iterPro.hasNext()) {
//            pro = iterPro.next();
//            com.itextpdf.text.Paragraph PRO_ID = new com.itextpdf.text.Paragraph("PRODUCTO:", normalBoldAzul);
//            com.itextpdf.text.Paragraph PRO_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(pro.getID_PRODUCTO()), normalBold);
//            com.itextpdf.text.Paragraph PRO_NOMBRE = new com.itextpdf.text.Paragraph("Nombre:", normalBold);
//            com.itextpdf.text.Paragraph PRO_NOMBRE_DB = new com.itextpdf.text.Paragraph(pro.getPRO_NOMBRE(), normal);
//            com.itextpdf.text.Paragraph PRO_SUC = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
//            com.itextpdf.text.Paragraph PRO_PROVEEDOR_DB = new com.itextpdf.text.Paragraph(pro.getPRO_EMPRESA(), normal);
//            com.itextpdf.text.Paragraph PRO_PROVEEDOR = new com.itextpdf.text.Paragraph("Proveedor", normalBold);
//            com.itextpdf.text.Paragraph PRO_SUCURSAL_DB = new com.itextpdf.text.Paragraph(pro.getSUC_NOMBRE(), normal);
//            com.itextpdf.text.Paragraph PRO_DETALLE = new com.itextpdf.text.Paragraph("Detalle", normalBold);
//            com.itextpdf.text.Paragraph PRO_DETALLE_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DETALLE(), normal);
//            com.itextpdf.text.Paragraph PRO_COD_PRIN = new com.itextpdf.text.Paragraph("Codigo Principal", normalBold);
//            com.itextpdf.text.Paragraph PRO_COD_PRIN_DB = new com.itextpdf.text.Paragraph(pro.getPRO_COD_PRINC(), normal);
//            com.itextpdf.text.Paragraph PRO_COD_AUX = new com.itextpdf.text.Paragraph("Codigo Auxiliar", normalBold);
//            com.itextpdf.text.Paragraph PRO_COD_AUX_DB = new com.itextpdf.text.Paragraph(pro.getPRO_COD_AUX(), normal);
//            com.itextpdf.text.Paragraph PRO_DET_EXTRA = new com.itextpdf.text.Paragraph("Detalle Extra", normalBold);
//            com.itextpdf.text.Paragraph PRO_DET_EXTRA_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DETALLE_EXTRA(), normal);
//            com.itextpdf.text.Paragraph PRO_STOCK = new com.itextpdf.text.Paragraph("Stock", normalBold);
//            com.itextpdf.text.Paragraph PRO_STOCK_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_STOCK()), normal);
//            com.itextpdf.text.Paragraph PRO_FABRI = new com.itextpdf.text.Paragraph("Precio de Fabrica", normalBold);
//            com.itextpdf.text.Paragraph PRO_FABRI_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_PRECIO_FABRICA()) + " USD", normal);
//            com.itextpdf.text.Paragraph PRO_GANAN = new com.itextpdf.text.Paragraph("Ganancia", normalBold);
//            com.itextpdf.text.Paragraph PRO_GANAN_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_GANANCIA()) + " %", normal);
//            com.itextpdf.text.Paragraph PRO_PVP = new com.itextpdf.text.Paragraph("Precio de Venta", normalBold);
//            com.itextpdf.text.Paragraph PRO_PVP_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_PVP()) + " USD", normal);
//            com.itextpdf.text.Paragraph PRO_TIPO_IVA = new com.itextpdf.text.Paragraph("Tipo IVA", normalBold);
//            com.itextpdf.text.Paragraph PRO_TIPO_IVA_DB = new com.itextpdf.text.Paragraph(pro.getPRO_TIPO_IVA(), normal);
//            com.itextpdf.text.Paragraph PRO_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
//            com.itextpdf.text.Paragraph PRO_ESTADO_DB = new com.itextpdf.text.Paragraph(pro.getPRO_ESTADO(), normal);
//            com.itextpdf.text.Paragraph PRO_CATEGO = new com.itextpdf.text.Paragraph("Categoria", normalBold);
//            com.itextpdf.text.Paragraph PRO_CATEGO_DB = new com.itextpdf.text.Paragraph(pro.getPRO_CATEGORIA() + "\n", normal);
//            com.itextpdf.text.Paragraph PRO_DESCU = new com.itextpdf.text.Paragraph("Descuento", normalBold);
//            com.itextpdf.text.Paragraph PRO_DESCU_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DESCUENTO() + "\n", normal);
//            datosProveedor.addCell(PRO_ID);
//            datosProveedor.addCell(PRO_ID_DB);
//            datosProveedor.addCell(PRO_ESTADO);
//            datosProveedor.addCell(PRO_ESTADO_DB);
//            datosProveedor.addCell(PRO_NOMBRE);
//            datosProveedor.addCell(PRO_NOMBRE_DB);
//            datosProveedor.addCell(PRO_SUC);
//            datosProveedor.addCell(PRO_PROVEEDOR_DB);
//            datosProveedor.addCell(PRO_PROVEEDOR);
//            datosProveedor.addCell(PRO_SUCURSAL_DB);
//            datosProveedor.addCell(PRO_DETALLE);
//            datosProveedor.addCell(PRO_DETALLE_DB);
//            datosProveedor.addCell(PRO_CATEGO);
//            datosProveedor.addCell(PRO_CATEGO_DB);
//            datosProveedor.addCell(PRO_COD_PRIN);
//            datosProveedor.addCell(PRO_COD_PRIN_DB);
//            datosProveedor.addCell(PRO_COD_AUX);
//            datosProveedor.addCell(PRO_COD_AUX_DB);
//            datosProveedor.addCell(PRO_DET_EXTRA);
//            datosProveedor.addCell(PRO_DET_EXTRA_DB);
//            datosProveedor.addCell(PRO_STOCK);
//            datosProveedor.addCell(PRO_STOCK_DB);
//            datosProveedor.addCell(PRO_FABRI);
//            datosProveedor.addCell(PRO_FABRI_DB);
//            datosProveedor.addCell(PRO_GANAN);
//            datosProveedor.addCell(PRO_GANAN_DB);
//            datosProveedor.addCell(PRO_PVP);
//            datosProveedor.addCell(PRO_PVP_DB);
//            datosProveedor.addCell(PRO_TIPO_IVA);
//            datosProveedor.addCell(PRO_TIPO_IVA_DB);
//            datosProveedor.addCell(PRO_DESCU);
//            datosProveedor.addCell(PRO_DESCU_DB);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//            datosProveedor.addCell(VACIO);
//        }
//
//        //agregarmos a la tabladatosClientesMain
//        datosProveedorMain.addCell(datosProveedor);
//
//        /* ============================
//        AGREGAR DATOS AL DOCUMENTO
//        ============================ */
//        doc.add(encabezado);
//        doc.add(espacio);
//        doc.add(datosProveedorMain);
//        doc.close();
////        ruta_archivo = ruta;
////        abrir_pdf(ruta_archivo);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new com.anthony.swing.RoundPanel();
        pictureBox1 = new com.anthony.swing.PictureBox();
        jLabel2 = new javax.swing.JLabel();
        button1 = new com.anthony.swing.Button();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        pictureBox10 = new com.anthony.swing.PictureBox();
        jLabel11 = new javax.swing.JLabel();
        button10 = new com.anthony.swing.Button();
        roundPanel4 = new com.anthony.swing.RoundPanel();
        pictureBox2 = new com.anthony.swing.PictureBox();
        jLabel3 = new javax.swing.JLabel();
        button2 = new com.anthony.swing.Button();
        roundPanel8 = new com.anthony.swing.RoundPanel();
        pictureBox3 = new com.anthony.swing.PictureBox();
        jLabel4 = new javax.swing.JLabel();
        button3 = new com.anthony.swing.Button();
        roundPanel15 = new com.anthony.swing.RoundPanel();
        pictureBox4 = new com.anthony.swing.PictureBox();
        jLabel5 = new javax.swing.JLabel();
        button4 = new com.anthony.swing.Button();
        roundPanel19 = new com.anthony.swing.RoundPanel();
        pictureBox5 = new com.anthony.swing.PictureBox();
        jLabel6 = new javax.swing.JLabel();
        button5 = new com.anthony.swing.Button();
        scrollBarCustom2 = new com.anthony.swing.scrollbar.ScrollBarCustom();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("REPORTES GENERALES");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(22, 23, 23));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBar(scrollBarCustom2);
        jScrollPane1.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        roundPanel1.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Clientes");

        button1.setBackground(new java.awt.Color(0, 102, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Descargar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox10.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Ventas generadas");

        button10.setBackground(new java.awt.Color(0, 102, 51));
        button10.setForeground(new java.awt.Color(255, 255, 255));
        button10.setText("Descargar");
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel4.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Empleados");

        button2.setBackground(new java.awt.Color(0, 102, 51));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Descargar");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel8.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Proveedores");

        button3.setBackground(new java.awt.Color(0, 102, 51));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Descargar");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel15.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Productos");

        button4.setBackground(new java.awt.Color(0, 102, 51));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setText("Descargar");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel15Layout = new javax.swing.GroupLayout(roundPanel15);
        roundPanel15.setLayout(roundPanel15Layout);
        roundPanel15Layout.setHorizontalGroup(
            roundPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(button4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel15Layout.setVerticalGroup(
            roundPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel19.setBackground(new java.awt.Color(32, 32, 32));

        pictureBox5.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/IconoFacturing-vf.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Usuarios");

        button5.setBackground(new java.awt.Color(0, 102, 51));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setText("Descargar");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel19Layout = new javax.swing.GroupLayout(roundPanel19);
        roundPanel19.setLayout(roundPanel19Layout);
        roundPanel19Layout.setHorizontalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel19Layout.setVerticalGroup(
            roundPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {roundPanel1, roundPanel15, roundPanel19, roundPanel3, roundPanel4, roundPanel8});

        jScrollPane1.setViewportView(jPanel1);

        scrollBarCustom2.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollBarCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBarCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            reporteClientes();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        try {
            reporteEmpleados();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        try {
            reporteProveedores();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        try {
            reporteProductos();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        // TODO add your handling code here:
        try {
            reporteUsuario();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button5ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        // TODO add your handling code here:
        try {
            reporteVentas();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTE_GENERAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.anthony.swing.Button button1;
    private com.anthony.swing.Button button10;
    private com.anthony.swing.Button button2;
    private com.anthony.swing.Button button3;
    private com.anthony.swing.Button button4;
    private com.anthony.swing.Button button5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.anthony.swing.PictureBox pictureBox1;
    private com.anthony.swing.PictureBox pictureBox10;
    private com.anthony.swing.PictureBox pictureBox2;
    private com.anthony.swing.PictureBox pictureBox3;
    private com.anthony.swing.PictureBox pictureBox4;
    private com.anthony.swing.PictureBox pictureBox5;
    private com.anthony.swing.RoundPanel roundPanel1;
    private com.anthony.swing.RoundPanel roundPanel15;
    private com.anthony.swing.RoundPanel roundPanel19;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel4;
    private com.anthony.swing.RoundPanel roundPanel8;
    private com.anthony.swing.scrollbar.ScrollBarCustom scrollBarCustom2;
    // End of variables declaration//GEN-END:variables
    private void reporteClientes() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/CLIENTES/" + "REP" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE CLIENTE 0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosClientesMain = new PdfPTable(1);
        datosClientesMain.setWidthPercentage(100);
        PdfPTable datosClientes = new PdfPTable(4);
        datosClientesMain.getDefaultCell().setBorder(0);
        float[] columnaCliente = new float[]{30f, 60f, 30f, 60f};
        datosClientes.setWidths(columnaCliente);
        datosClientes.getDefaultCell().setBorder(0);
        List<CLIENTE> listCh = daoCli.listar();
        Iterator<CLIENTE> iterCli = listCh.iterator();
        cli = null;
        while (iterCli.hasNext()) {
            cli = iterCli.next();
            com.itextpdf.text.Paragraph CLI_ID = new com.itextpdf.text.Paragraph("CLIENTE:", normalBoldAzul);
            com.itextpdf.text.Paragraph CLI_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(cli.getID_CLIENTE()), normalBold);
            com.itextpdf.text.Paragraph CLI_NOMBRES = new com.itextpdf.text.Paragraph("Nombres", normalBold);
            com.itextpdf.text.Paragraph CLI_NOMBRES_DB = new com.itextpdf.text.Paragraph(cli.getCLI_NOMBRES(), normal);
            com.itextpdf.text.Paragraph CLI_APELLIDOS = new com.itextpdf.text.Paragraph("Apellidos", normalBold);
            com.itextpdf.text.Paragraph CLI_APELLIDOS_DB = new com.itextpdf.text.Paragraph(cli.getCLI_APELLIDOS(), normal);
            com.itextpdf.text.Paragraph CLI_CEDULA = new com.itextpdf.text.Paragraph("Cedula", normalBold);
            com.itextpdf.text.Paragraph CLI_CEDULA_DB = new com.itextpdf.text.Paragraph(cli.getCLI_CEDULA(), normal);
            com.itextpdf.text.Paragraph CLI_RUC = new com.itextpdf.text.Paragraph("RUC", normalBold);
            com.itextpdf.text.Paragraph CLI_RUC_DB = new com.itextpdf.text.Paragraph(cli.getCLI_RUC(), normal);
            com.itextpdf.text.Paragraph CLI_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
            com.itextpdf.text.Paragraph CLI_TELEFONO_DB = new com.itextpdf.text.Paragraph(cli.getCLI_TELEFONO(), normal);
            com.itextpdf.text.Paragraph CLI_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
            com.itextpdf.text.Paragraph CLI_DIRECCION_DB = new com.itextpdf.text.Paragraph(cli.getCLI_DIRECCION(), normal);
            com.itextpdf.text.Paragraph CLI_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
            com.itextpdf.text.Paragraph CLI_EMAIL_DB = new com.itextpdf.text.Paragraph(cli.getCLI_EMAIL(), normal);
            com.itextpdf.text.Paragraph CLI_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph CLI_ESTADO_DB = new com.itextpdf.text.Paragraph(cli.getCLI_ESTADO() + "\n", normal);
            datosClientes.addCell(CLI_ID);
            datosClientes.addCell(CLI_ID_DB);
            datosClientes.addCell(VACIO);
            datosClientes.addCell(VACIO);
            datosClientes.addCell(CLI_NOMBRES);
            datosClientes.addCell(CLI_NOMBRES_DB);
            datosClientes.addCell(CLI_APELLIDOS);
            datosClientes.addCell(CLI_APELLIDOS_DB);
            datosClientes.addCell(CLI_CEDULA);
            datosClientes.addCell(CLI_CEDULA_DB);
            datosClientes.addCell(CLI_RUC);
            datosClientes.addCell(CLI_RUC_DB);
            datosClientes.addCell(CLI_TELEFONO);
            datosClientes.addCell(CLI_TELEFONO_DB);
            datosClientes.addCell(CLI_DIRECCION);
            datosClientes.addCell(CLI_DIRECCION_DB);
            datosClientes.addCell(CLI_EMAIL);
            datosClientes.addCell(CLI_EMAIL_DB);
            datosClientes.addCell(CLI_ESTADO);
            datosClientes.addCell(CLI_ESTADO_DB);
            datosClientes.addCell(VACIO);
            datosClientes.addCell(VACIO);
            datosClientes.addCell(VACIO);
            datosClientes.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosClientesMain.addCell(datosClientes);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosClientesMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

    private void reporteEmpleados() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/EMPLEADOS/" + "REP" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE EMPLEADO 0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosEmpleadosMain = new PdfPTable(1);
        datosEmpleadosMain.setWidthPercentage(100);
        PdfPTable datosEmpleados = new PdfPTable(4);
        datosEmpleadosMain.getDefaultCell().setBorder(0);
        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
        datosEmpleados.setWidths(columnaEmpleados);
        datosEmpleados.getDefaultCell().setBorder(0);
        List<EMPLEADO> listEmp = empleadoDao.listar();
        Iterator<EMPLEADO> iterEmp = listEmp.iterator();
        empleado = null;
        while (iterEmp.hasNext()) {
            empleado = iterEmp.next();
            com.itextpdf.text.Paragraph EMP_ID = new com.itextpdf.text.Paragraph("EMPLEADO:", normalBoldAzul);
            com.itextpdf.text.Paragraph EMP_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(empleado.getID_EMPLEADO()), normalBold);
            com.itextpdf.text.Paragraph EMP_NOMBRES = new com.itextpdf.text.Paragraph("Nombres", normalBold);
            com.itextpdf.text.Paragraph EMP_NOMBRES_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_NOMBRES(), normal);
            com.itextpdf.text.Paragraph EMP_APELLIDOS = new com.itextpdf.text.Paragraph("Apellidos", normalBold);
            com.itextpdf.text.Paragraph EMP_APELLIDOS_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_APELLIDOS(), normal);
            com.itextpdf.text.Paragraph EMP_CEDULA = new com.itextpdf.text.Paragraph("Cedula", normalBold);
            com.itextpdf.text.Paragraph EMP_CEDULA_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_CEDULA(), normal);
            com.itextpdf.text.Paragraph EMP_RUC = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
            com.itextpdf.text.Paragraph EMP_RUC_DB = new com.itextpdf.text.Paragraph(empleado.getSUC_NOMBRE(), normal);
            com.itextpdf.text.Paragraph EMP_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
            com.itextpdf.text.Paragraph EMP_TELEFONO_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_TELEFONO(), normal);
            com.itextpdf.text.Paragraph EMP_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
            com.itextpdf.text.Paragraph EMP_DIRECCION_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_DIRECCION(), normal);
            com.itextpdf.text.Paragraph EMP_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
            com.itextpdf.text.Paragraph EMP_EMAIL_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_EMAIL(), normal);
            com.itextpdf.text.Paragraph EMP_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph EMP_ESTADO_DB = new com.itextpdf.text.Paragraph(empleado.getEMP_ESTADO() + "\n", normal);
            datosEmpleados.addCell(EMP_ID);
            datosEmpleados.addCell(EMP_ID_DB);
            datosEmpleados.addCell(VACIO);
            datosEmpleados.addCell(VACIO);
            datosEmpleados.addCell(EMP_NOMBRES);
            datosEmpleados.addCell(EMP_NOMBRES_DB);
            datosEmpleados.addCell(EMP_APELLIDOS);
            datosEmpleados.addCell(EMP_APELLIDOS_DB);
            datosEmpleados.addCell(EMP_CEDULA);
            datosEmpleados.addCell(EMP_CEDULA_DB);
            datosEmpleados.addCell(EMP_RUC);
            datosEmpleados.addCell(EMP_RUC_DB);
            datosEmpleados.addCell(EMP_TELEFONO);
            datosEmpleados.addCell(EMP_TELEFONO_DB);
            datosEmpleados.addCell(EMP_DIRECCION);
            datosEmpleados.addCell(EMP_DIRECCION_DB);
            datosEmpleados.addCell(EMP_EMAIL);
            datosEmpleados.addCell(EMP_EMAIL_DB);
            datosEmpleados.addCell(EMP_ESTADO);
            datosEmpleados.addCell(EMP_ESTADO_DB);
            datosEmpleados.addCell(VACIO);
            datosEmpleados.addCell(VACIO);
            datosEmpleados.addCell(VACIO);
            datosEmpleados.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosEmpleadosMain.addCell(datosEmpleados);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosEmpleadosMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

    private void reporteProveedores() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/PROVEEDORES/" + "REP" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE PROVEEDOR 0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosProveedorMain = new PdfPTable(1);
        datosProveedorMain.setWidthPercentage(100);
        PdfPTable datosProveedor = new PdfPTable(4);
        datosProveedorMain.getDefaultCell().setBorder(0);
        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
        datosProveedor.setWidths(columnaEmpleados);
        datosProveedor.getDefaultCell().setBorder(0);
        List<PROVEEDOR> listProv = provDao.listar();
        Iterator<PROVEEDOR> iterProv = listProv.iterator();
        prov = null;
        while (iterProv.hasNext()) {
            prov = iterProv.next();
            com.itextpdf.text.Paragraph PRO_ID = new com.itextpdf.text.Paragraph("PROVEEDOR:", normalBoldAzul);
            com.itextpdf.text.Paragraph PRO_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(prov.getID_PROVEEDOR()), normalBold);
            com.itextpdf.text.Paragraph PRO_WEB = new com.itextpdf.text.Paragraph("WEB:", normalBold);
            com.itextpdf.text.Paragraph PRO_WEB_DB = new com.itextpdf.text.Paragraph(prov.getPRO_WEB(), normal);
            com.itextpdf.text.Paragraph PRO_EMPRESA = new com.itextpdf.text.Paragraph("Empresa", normalBold);
            com.itextpdf.text.Paragraph PRO_EMPRESA_DB = new com.itextpdf.text.Paragraph(prov.getPRO_EMPRESA(), normal);
            com.itextpdf.text.Paragraph PRO_SUCURSAL = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
            com.itextpdf.text.Paragraph PRO_SUCURSAL_DB = new com.itextpdf.text.Paragraph(prov.getSUC_NOMBRE(), normal);
            com.itextpdf.text.Paragraph PRO_CONTACTO = new com.itextpdf.text.Paragraph("Contacto", normalBold);
            com.itextpdf.text.Paragraph PRO_CONTACTO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_CONTACTO(), normal);
            com.itextpdf.text.Paragraph PRO_RUC = new com.itextpdf.text.Paragraph("RUC", normalBold);
            com.itextpdf.text.Paragraph PRO_RUC_DB = new com.itextpdf.text.Paragraph(prov.getPRO_RUC(), normal);
            com.itextpdf.text.Paragraph PRO_TELEFONO = new com.itextpdf.text.Paragraph("Telefono", normalBold);
            com.itextpdf.text.Paragraph PRO_TELEFONO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_TELEFONO(), normal);
            com.itextpdf.text.Paragraph PRO_DIRECCION = new com.itextpdf.text.Paragraph("Direccion", normalBold);
            com.itextpdf.text.Paragraph PRO_DIRECCION_DB = new com.itextpdf.text.Paragraph(prov.getPRO_DIRECCION(), normal);
            com.itextpdf.text.Paragraph PRO_EMAIL = new com.itextpdf.text.Paragraph("Email", normalBold);
            com.itextpdf.text.Paragraph PRO_EMAIL_DB = new com.itextpdf.text.Paragraph(prov.getPRO_EMAIL(), normal);
            com.itextpdf.text.Paragraph PRO_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph PRO_ESTADO_DB = new com.itextpdf.text.Paragraph(prov.getPRO_ESTADO() + "\n", normal);
            datosProveedor.addCell(PRO_ID);
            datosProveedor.addCell(PRO_ID_DB);
            datosProveedor.addCell(PRO_SUCURSAL);
            datosProveedor.addCell(PRO_SUCURSAL_DB);
            datosProveedor.addCell(PRO_EMPRESA);
            datosProveedor.addCell(PRO_EMPRESA_DB);
            datosProveedor.addCell(PRO_CONTACTO);
            datosProveedor.addCell(PRO_CONTACTO_DB);
            datosProveedor.addCell(PRO_RUC);
            datosProveedor.addCell(PRO_RUC_DB);
            datosProveedor.addCell(PRO_TELEFONO);
            datosProveedor.addCell(PRO_TELEFONO_DB);
            datosProveedor.addCell(PRO_DIRECCION);
            datosProveedor.addCell(PRO_DIRECCION_DB);
            datosProveedor.addCell(PRO_EMAIL);
            datosProveedor.addCell(PRO_EMAIL_DB);
            datosProveedor.addCell(PRO_WEB);
            datosProveedor.addCell(PRO_WEB_DB);
            datosProveedor.addCell(PRO_ESTADO);
            datosProveedor.addCell(PRO_ESTADO_DB);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosProveedorMain.addCell(datosProveedor);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosProveedorMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

    private void reporteProductos() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/PRODUCTOS/" + "REP7" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE PRODUCTO 0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosProveedorMain = new PdfPTable(1);
        datosProveedorMain.setWidthPercentage(100);
        PdfPTable datosProveedor = new PdfPTable(4);
        datosProveedorMain.getDefaultCell().setBorder(0);
        float[] columnaEmpleados = new float[]{30f, 60f, 30f, 60f};
        datosProveedor.setWidths(columnaEmpleados);
        datosProveedor.getDefaultCell().setBorder(0);
        List<PRODUCTO> listPro = proDao.listar();
        Iterator<PRODUCTO> iterPro = listPro.iterator();
        pro = null;
        while (iterPro.hasNext()) {
            pro = iterPro.next();
            com.itextpdf.text.Paragraph PRO_ID = new com.itextpdf.text.Paragraph("PRODUCTO:", normalBoldAzul);
            com.itextpdf.text.Paragraph PRO_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(pro.getID_PRODUCTO()), normalBold);
            com.itextpdf.text.Paragraph PRO_NOMBRE = new com.itextpdf.text.Paragraph("Nombre:", normalBold);
            com.itextpdf.text.Paragraph PRO_NOMBRE_DB = new com.itextpdf.text.Paragraph(pro.getPRO_NOMBRE(), normal);
            com.itextpdf.text.Paragraph PRO_SUC = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
            com.itextpdf.text.Paragraph PRO_PROVEEDOR_DB = new com.itextpdf.text.Paragraph(pro.getPRO_EMPRESA(), normal);
            com.itextpdf.text.Paragraph PRO_PROVEEDOR = new com.itextpdf.text.Paragraph("Proveedor", normalBold);
            com.itextpdf.text.Paragraph PRO_SUCURSAL_DB = new com.itextpdf.text.Paragraph(pro.getSUC_NOMBRE(), normal);
            com.itextpdf.text.Paragraph PRO_DETALLE = new com.itextpdf.text.Paragraph("Detalle", normalBold);
            com.itextpdf.text.Paragraph PRO_DETALLE_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DETALLE(), normal);
            com.itextpdf.text.Paragraph PRO_COD_PRIN = new com.itextpdf.text.Paragraph("Codigo Principal", normalBold);
            com.itextpdf.text.Paragraph PRO_COD_PRIN_DB = new com.itextpdf.text.Paragraph(pro.getPRO_COD_PRINC(), normal);
            com.itextpdf.text.Paragraph PRO_COD_AUX = new com.itextpdf.text.Paragraph("Codigo Auxiliar", normalBold);
            com.itextpdf.text.Paragraph PRO_COD_AUX_DB = new com.itextpdf.text.Paragraph(pro.getPRO_COD_AUX(), normal);
            com.itextpdf.text.Paragraph PRO_DET_EXTRA = new com.itextpdf.text.Paragraph("Detalle Extra", normalBold);
            com.itextpdf.text.Paragraph PRO_DET_EXTRA_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DETALLE_EXTRA(), normal);
            com.itextpdf.text.Paragraph PRO_STOCK = new com.itextpdf.text.Paragraph("Stock", normalBold);
            com.itextpdf.text.Paragraph PRO_STOCK_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_STOCK()), normal);
            com.itextpdf.text.Paragraph PRO_FABRI = new com.itextpdf.text.Paragraph("Precio de Fabrica", normalBold);
            com.itextpdf.text.Paragraph PRO_FABRI_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_PRECIO_FABRICA()) + " USD", normal);
            com.itextpdf.text.Paragraph PRO_GANAN = new com.itextpdf.text.Paragraph("Ganancia", normalBold);
            com.itextpdf.text.Paragraph PRO_GANAN_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_GANANCIA()) + " %", normal);
            com.itextpdf.text.Paragraph PRO_PVP = new com.itextpdf.text.Paragraph("Precio de Venta", normalBold);
            com.itextpdf.text.Paragraph PRO_PVP_DB = new com.itextpdf.text.Paragraph(String.valueOf(pro.getPRO_PVP()) + " USD", normal);
            com.itextpdf.text.Paragraph PRO_TIPO_IVA = new com.itextpdf.text.Paragraph("Tipo IVA", normalBold);
            com.itextpdf.text.Paragraph PRO_TIPO_IVA_DB = new com.itextpdf.text.Paragraph(pro.getPRO_TIPO_IVA(), normal);
            com.itextpdf.text.Paragraph PRO_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph PRO_ESTADO_DB = new com.itextpdf.text.Paragraph(pro.getPRO_ESTADO(), normal);
            com.itextpdf.text.Paragraph PRO_CATEGO = new com.itextpdf.text.Paragraph("Categoria", normalBold);
            com.itextpdf.text.Paragraph PRO_CATEGO_DB = new com.itextpdf.text.Paragraph(pro.getPRO_CATEGORIA() + "\n", normal);
            com.itextpdf.text.Paragraph PRO_DESCU = new com.itextpdf.text.Paragraph("Descuento", normalBold);
            com.itextpdf.text.Paragraph PRO_DESCU_DB = new com.itextpdf.text.Paragraph(pro.getPRO_DESCUENTO() + "\n", normal);
            datosProveedor.addCell(PRO_ID);
            datosProveedor.addCell(PRO_ID_DB);
            datosProveedor.addCell(PRO_ESTADO);
            datosProveedor.addCell(PRO_ESTADO_DB);
            datosProveedor.addCell(PRO_NOMBRE);
            datosProveedor.addCell(PRO_NOMBRE_DB);
            datosProveedor.addCell(PRO_SUC);
            datosProveedor.addCell(PRO_PROVEEDOR_DB);
            datosProveedor.addCell(PRO_PROVEEDOR);
            datosProveedor.addCell(PRO_SUCURSAL_DB);
            datosProveedor.addCell(PRO_DETALLE);
            datosProveedor.addCell(PRO_DETALLE_DB);
            datosProveedor.addCell(PRO_CATEGO);
            datosProveedor.addCell(PRO_CATEGO_DB);
            datosProveedor.addCell(PRO_COD_PRIN);
            datosProveedor.addCell(PRO_COD_PRIN_DB);
            datosProveedor.addCell(PRO_COD_AUX);
            datosProveedor.addCell(PRO_COD_AUX_DB);
            datosProveedor.addCell(PRO_DET_EXTRA);
            datosProveedor.addCell(PRO_DET_EXTRA_DB);
            datosProveedor.addCell(PRO_STOCK);
            datosProveedor.addCell(PRO_STOCK_DB);
            datosProveedor.addCell(PRO_FABRI);
            datosProveedor.addCell(PRO_FABRI_DB);
            datosProveedor.addCell(PRO_GANAN);
            datosProveedor.addCell(PRO_GANAN_DB);
            datosProveedor.addCell(PRO_PVP);
            datosProveedor.addCell(PRO_PVP_DB);
            datosProveedor.addCell(PRO_TIPO_IVA);
            datosProveedor.addCell(PRO_TIPO_IVA_DB);
            datosProveedor.addCell(PRO_DESCU);
            datosProveedor.addCell(PRO_DESCU_DB);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosProveedorMain.addCell(datosProveedor);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosProveedorMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

    private void reporteUsuario() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/USUARIOS/" + "REP7" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE USUARIOS0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosProveedorMain = new PdfPTable(1);
        datosProveedorMain.setWidthPercentage(100);
        PdfPTable datosProveedor = new PdfPTable(4);
        datosProveedorMain.getDefaultCell().setBorder(0);
        float[] columnaEmpleados = new float[]{20f, 60f, 20f, 60f};
        datosProveedor.setWidths(columnaEmpleados);
        datosProveedor.getDefaultCell().setBorder(0);
        List<USUARIO> listPro = daoUsu.listar();
        Iterator<USUARIO> iterPro = listPro.iterator();
        usu = null;
        while (iterPro.hasNext()) {
            usu = iterPro.next();
            com.itextpdf.text.Paragraph FAC_ID = new com.itextpdf.text.Paragraph("USUARIO:", normalBoldAzul);
            com.itextpdf.text.Paragraph FAC_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(usu.getID_USUARIO()), normalBold);
            com.itextpdf.text.Paragraph USU_ALIAS = new com.itextpdf.text.Paragraph("Alias:", normalBold);
            com.itextpdf.text.Paragraph USU_ALIAS_DB = new com.itextpdf.text.Paragraph(usu.getUSU_USUARIO(), normal);
            com.itextpdf.text.Paragraph EMP_NOMBRES = new com.itextpdf.text.Paragraph("Empleado", normalBold);
            com.itextpdf.text.Paragraph EMP_NOMBRES_DB = new com.itextpdf.text.Paragraph(usu.getEMP_APELLIDOS() + " " + usu.getEMP_NOMBRES(), normal);
            com.itextpdf.text.Paragraph USU_CLAVE = new com.itextpdf.text.Paragraph("Clave", normalBold);
            com.itextpdf.text.Paragraph USU_CLAVE_DB = new com.itextpdf.text.Paragraph(usu.getUSU_CLAVE(), normal);
            com.itextpdf.text.Paragraph USU_PARAMETRO = new com.itextpdf.text.Paragraph("Parametro", normalBold);
            com.itextpdf.text.Paragraph USU_PARAMETRO_DB = new com.itextpdf.text.Paragraph(usu.getUSU_PARAMETRO(), normal);
            com.itextpdf.text.Paragraph USU_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph USU_ESTADO_DB = new com.itextpdf.text.Paragraph(usu.getUSU_ESTADO(), normal);
            com.itextpdf.text.Paragraph USU_SUCURSAL = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
            com.itextpdf.text.Paragraph USU_SUCURSAL_DB = new com.itextpdf.text.Paragraph(usu.getSUC_NOMBRE(), normal);
            datosProveedor.addCell(FAC_ID);
            datosProveedor.addCell(FAC_ID_DB);
            datosProveedor.addCell(USU_ESTADO);
            datosProveedor.addCell(USU_ESTADO_DB);
            datosProveedor.addCell(USU_ALIAS);
            datosProveedor.addCell(USU_ALIAS_DB);
            datosProveedor.addCell(USU_SUCURSAL);
            datosProveedor.addCell(USU_SUCURSAL_DB);
            datosProveedor.addCell(EMP_NOMBRES);
            datosProveedor.addCell(EMP_NOMBRES_DB);
            datosProveedor.addCell(USU_PARAMETRO);
            datosProveedor.addCell(USU_PARAMETRO_DB);
            datosProveedor.addCell(USU_CLAVE);
            datosProveedor.addCell(USU_CLAVE_DB);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosProveedorMain.addCell(datosProveedor);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosProveedorMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

    private void reporteVentas() throws FileNotFoundException, DocumentException, BadElementException, IOException {
        /* ============================
        DATOS PRINCIPALES FECHAS
        ============================ */

        String MES = "";
        switch (mes) {
            case 1:
                MES = "ENERO";
                break;
            case 2:
                MES = "FEBRERO";
                break;
            case 3:
                MES = "MARZO";
                break;
            case 4:
                MES = "ABRIL";
                break;
            case 5:
                MES = "MAYO";
                break;
            case 6:
                MES = "JUNIO";
                break;
            case 7:
                MES = "JULIO";
                break;
            case 8:
                MES = "AGOSTO";
                break;
            case 9:
                MES = "SEPTIEMRBRE";
                break;
            case 10:
                MES = "OCTUBRE";
                break;
            case 11:
                MES = "NOVIEMBRE";
                break;
            case 12:
                MES = "DICIEMBRE";
                break;
            default:
                throw new AssertionError();
        }
        /* ============================
        DATOS PRINCIPALES DEL DOCUMENTO
        ============================ */
        FileOutputStream archivo;
        String ruta = "C:/FACTURING_V1/" + year + "/" + MES + "/REPORTES GENERALES/FACTURAS/" + "REP7" + ".pdf";
        File file = new File(ruta);//Ruta donde se guarda el archivo:   C:\Facturing\pdfFacturas 
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, archivo);
        doc.open();

        /* ============================
        FUENTES Y TIPOS
        ============================ */
        //Espacio 
        Paragraph espacio = new Paragraph();
        espacio.add("\n");
        /* ============================
        PARTE ENCABEZADO
        ============================ */
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);
        float[] ColumnaEncabezado = new float[]{15f, 70f, 10f, 50f};
        encabezado.setWidths(ColumnaEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //VARIABLES EMPRESA
        Image img = Image.getInstance("src/com/anthony/icons/logo_empresa.png");
        emp = empDao.list();
        String datosEmpresa = emp.getEMP_NOMBRE_COMERCIAL() + "\n"
                + "Matriz: " + emp.getEMP_MATRIZ() + "\n"
                + "Telf: " + "0980868422 - 02-3089-081" + "\n"
                + "Contribuyente Especial: " + emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL() + "\n"
                + "Obligado a llevar contabilidad: " + emp.getEMP_LLEVAR_CONTABILIDAD();
        /* ============================
        AGREGAR TABLA DATOS FACTURA
        ============================ */
        //ENVIO DATOS A LA TABLA
        Paragraph parrafoEmpresa = new Paragraph(datosEmpresa, negrita);
        encabezado.addCell(img);//img empresa
        encabezado.addCell(parrafoEmpresa);//datos empresa
        encabezado.addCell("");//espacio

        /* ============================
        AGREGAR CODIGO DE BARRAS
        ============================ */
        PdfPTable codigoBarras = new PdfPTable(1);
        codigoBarras.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Barcode128 b = new Barcode128();
        b.setCode("REPORTE VENTAS0000001");
        b.setBarHeight(40);
        PdfPCell barCodeCell = new PdfPCell();
        barCodeCell.setBorder(0);
        barCodeCell.addElement(b.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK));
        codigoBarras.setWidthPercentage(40f);
        codigoBarras.addCell(barCodeCell);

        encabezado.addCell(codigoBarras);//datos factura
        /* ============================
        DATOS DB
        ============================ */
        PdfPTable datosProveedorMain = new PdfPTable(1);
        datosProveedorMain.setWidthPercentage(100);
        PdfPTable datosProveedor = new PdfPTable(4);
        datosProveedorMain.getDefaultCell().setBorder(0);
        float[] columnaEmpleados = new float[]{20f, 60f, 20f, 60f};
        datosProveedor.setWidths(columnaEmpleados);
        datosProveedor.getDefaultCell().setBorder(0);
        List<FACTURA> listPro = daoFac.listar();
        Iterator<FACTURA> iterPro = listPro.iterator();
        fac = null;
        while (iterPro.hasNext()) {
            fac = iterPro.next();
            com.itextpdf.text.Paragraph FAC_ID = new com.itextpdf.text.Paragraph("FACTURA:", normalBoldAzul);
            com.itextpdf.text.Paragraph FAC_ID_DB = new com.itextpdf.text.Paragraph("00" + String.valueOf(fac.getID_FACTURA()), normalBold);
            com.itextpdf.text.Paragraph FAC_EMPLEADO = new com.itextpdf.text.Paragraph("Empleado:", normalBold);
            com.itextpdf.text.Paragraph FAC_EMPLEADO_DB = new com.itextpdf.text.Paragraph(fac.getEMP_APELLIDOS() + " " + fac.getEMP_NOMBRES(), normal);
            com.itextpdf.text.Paragraph FAC_SUC = new com.itextpdf.text.Paragraph("Sucursal", normalBold);
            com.itextpdf.text.Paragraph FAC_SUC_DB = new com.itextpdf.text.Paragraph(fac.getSUC_NOMBRE(), normal);
            com.itextpdf.text.Paragraph FAC_CLIENTE = new com.itextpdf.text.Paragraph("Cliente", normalBold);
            com.itextpdf.text.Paragraph FAC_CLIENTE_DB = new com.itextpdf.text.Paragraph(fac.getCLI_APELLIDOS() + " " + fac.getCLI_NOMBRES(), normal);
            com.itextpdf.text.Paragraph FAC_FECHA = new com.itextpdf.text.Paragraph("Fecha", normalBold);
            com.itextpdf.text.Paragraph FAC_FECHA_DB = new com.itextpdf.text.Paragraph(fac.getFAC_FECHA(), normal);
            com.itextpdf.text.Paragraph FAC_HORA = new com.itextpdf.text.Paragraph("Hora", normalBold);
            com.itextpdf.text.Paragraph FAC_HORA_DB = new com.itextpdf.text.Paragraph(fac.getFAC_HORA(), normal);
            com.itextpdf.text.Paragraph FAC_COD_AUT = new com.itextpdf.text.Paragraph("Codigo Autotizacion", normalBold);
            com.itextpdf.text.Paragraph FAC_COD_AUT_DB = new com.itextpdf.text.Paragraph(fac.getFAC_COD_AUT(), normal);
            com.itextpdf.text.Paragraph FAC_COD = new com.itextpdf.text.Paragraph("Codigo", normalBold);
            com.itextpdf.text.Paragraph FAC_COD_DB = new com.itextpdf.text.Paragraph(fac.getFAC_CODIGO(), normal);
            com.itextpdf.text.Paragraph FAC_RUTA = new com.itextpdf.text.Paragraph("Ruta", normalBold);
            com.itextpdf.text.Paragraph FAC_RUTA_DB = new com.itextpdf.text.Paragraph(fac.getFAC_RUTA(), normal);
            com.itextpdf.text.Paragraph FAC_ESTADO = new com.itextpdf.text.Paragraph("Estado", normalBold);
            com.itextpdf.text.Paragraph FAC_ESTADO_DB = new com.itextpdf.text.Paragraph(fac.getFAC_ESTADO(), normal);
            datosProveedor.addCell(FAC_ID);
            datosProveedor.addCell(FAC_ID_DB);
            datosProveedor.addCell(FAC_ESTADO);
            datosProveedor.addCell(FAC_ESTADO_DB);
            datosProveedor.addCell(FAC_EMPLEADO);
            datosProveedor.addCell(FAC_EMPLEADO_DB);
            datosProveedor.addCell(FAC_SUC);
            datosProveedor.addCell(FAC_SUC_DB);
            datosProveedor.addCell(FAC_FECHA);
            datosProveedor.addCell(FAC_FECHA_DB);
            datosProveedor.addCell(FAC_HORA);
            datosProveedor.addCell(FAC_HORA_DB);
            datosProveedor.addCell(FAC_COD_AUT);
            datosProveedor.addCell(FAC_COD_AUT_DB);
            datosProveedor.addCell(FAC_COD);
            datosProveedor.addCell(FAC_COD_DB);
            datosProveedor.addCell(FAC_CLIENTE);
            datosProveedor.addCell(FAC_CLIENTE_DB);
            datosProveedor.addCell(FAC_RUTA);
            datosProveedor.addCell(FAC_RUTA_DB);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
            datosProveedor.addCell(VACIO);
        }

        //agregarmos a la tabladatosClientesMain
        datosProveedorMain.addCell(datosProveedor);

        /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
        doc.add(encabezado);
        doc.add(espacio);
        doc.add(datosProveedorMain);
        doc.close();
        Desktop.getDesktop().open(file);
    }

}
