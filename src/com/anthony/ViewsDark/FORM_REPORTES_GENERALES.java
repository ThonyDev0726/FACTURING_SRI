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

public class FORM_REPORTES_GENERALES extends javax.swing.JPanel {

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
    public FORM_REPORTES_GENERALES() {
        initComponents();
        scroll();
        init();
    }

    public FORM_REPORTES_GENERALES(USUARIO usu, MainAdministradorDark admin) {
        initComponents();
        scroll();
        init();
    }

    private void init() {
        pnlReporteGeneral.setVisible(false);
        pnlReporteEstadisticos.setVisible(false);
        pnlRevNotasCred.setVisible(false);
        pnlRevNotasDeb.setVisible(false);
        pnlCambioClave.setVisible(false);
        pnlEstadoCaja.setVisible(false);
        pnlCaja.setVisible(false);
        pnlAuditoria.setVisible(false);
//        pnlReporteGeneral.setVisible(false);
    }

    /* ============================
        AGREGAR DATOS AL DOCUMENTO
        ============================ */
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
        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
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
        ruta_archivo = ruta;
        abrir_pdf(ruta_archivo);
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
        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
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
        ruta_archivo = ruta;
        abrir_pdf(ruta_archivo);
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
        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
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
        ruta_archivo = ruta;
        abrir_pdf(ruta_archivo);
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
        float[] ColumnaEncabezado = new float[]{10f, 70f, 10f, 50f};
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
        ruta_archivo = ruta;
        abrir_pdf(ruta_archivo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new com.anthony.swing.RoundPanel();
        sc = new javax.swing.JScrollPane();
        img = new mivisorpdf.CuadroImagen();
        scrollBarCustom1 = new com.anthony.swing.scrollbar.ScrollBarCustom();
        roundPanel3 = new com.anthony.swing.RoundPanel();
        p = new javax.swing.JLabel();
        roundPanel5 = new com.anthony.swing.RoundPanel();
        p1 = new javax.swing.JLabel();
        roundPanel6 = new com.anthony.swing.RoundPanel();
        p2 = new javax.swing.JLabel();
        roundPanel8 = new com.anthony.swing.RoundPanel();
        p4 = new javax.swing.JLabel();
        roundPanel9 = new com.anthony.swing.RoundPanel();
        lblArchivo = new javax.swing.JLabel();
        roundPanel7 = new com.anthony.swing.RoundPanel();
        p3 = new javax.swing.JLabel();
        roundPanel10 = new com.anthony.swing.RoundPanel();
        lblNombreArchivo = new javax.swing.JLabel();
        spItems = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlCliente = new com.anthony.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlEmpleados = new com.anthony.swing.RoundPanel();
        jLabel17 = new javax.swing.JLabel();
        pnlProveedores = new com.anthony.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlProductos = new com.anthony.swing.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlReporteGeneral = new com.anthony.swing.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        pnlReporteEstadisticos = new com.anthony.swing.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        pnlRevFacturas = new com.anthony.swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlRevNotasCred = new com.anthony.swing.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        pnlRevNotasDeb = new com.anthony.swing.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlUsuarios = new com.anthony.swing.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        pnlCambioClave = new com.anthony.swing.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        pnlEmpresa = new com.anthony.swing.RoundPanel();
        jLabel12 = new javax.swing.JLabel();
        pnlSucursal = new com.anthony.swing.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        pnlEstadoCaja = new com.anthony.swing.RoundPanel();
        jLabel14 = new javax.swing.JLabel();
        pnlCaja = new com.anthony.swing.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        pnlAuditoria = new com.anthony.swing.RoundPanel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(63, 81, 102));
        jLabel1.setText("REPORTES GENERALES");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        roundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        sc.setBackground(new java.awt.Color(32, 32, 32));
        sc.setBorder(null);
        sc.setHorizontalScrollBar(scrollBarCustom1);

        img.setBackground(new java.awt.Color(32, 32, 32));
        img.setPreferredSize(new java.awt.Dimension(210, 297));

        javax.swing.GroupLayout imgLayout = new javax.swing.GroupLayout(img);
        img.setLayout(imgLayout);
        imgLayout.setHorizontalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );
        imgLayout.setVerticalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );

        sc.setViewportView(img);

        scrollBarCustom1.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollBarCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sc, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(sc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        roundPanel3.setBackground(new java.awt.Color(32, 32, 32));

        p.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        p.setForeground(new java.awt.Color(130, 119, 96));
        p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p.setText("0 paginas");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel5.setBackground(new java.awt.Color(32, 32, 32));

        p1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(63, 81, 102));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoIzquierda.png"))); // NOI18N
        p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel6.setBackground(new java.awt.Color(32, 32, 32));

        p2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p2.setForeground(new java.awt.Color(63, 81, 102));
        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoDerecha.png"))); // NOI18N
        p2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel8.setBackground(new java.awt.Color(32, 32, 32));

        p4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p4.setForeground(new java.awt.Color(63, 81, 102));
        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoMenos.png"))); // NOI18N
        p4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel9.setBackground(new java.awt.Color(32, 32, 32));

        lblArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArchivo.setForeground(new java.awt.Color(130, 119, 96));
        lblArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoArchivo.png"))); // NOI18N
        lblArchivo.setText("Abrir archivo");
        lblArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel7.setBackground(new java.awt.Color(32, 32, 32));

        p3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        p3.setForeground(new java.awt.Color(63, 81, 102));
        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/anthony/icons/iconoSuma.png"))); // NOI18N
        p3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        roundPanel10.setBackground(new java.awt.Color(32, 32, 32));

        lblNombreArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombreArchivo.setForeground(new java.awt.Color(102, 153, 255));
        lblNombreArchivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel10Layout = new javax.swing.GroupLayout(roundPanel10);
        roundPanel10.setLayout(roundPanel10Layout);
        roundPanel10Layout.setHorizontalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNombreArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        spItems.setBackground(new java.awt.Color(22, 23, 23));
        spItems.setBorder(null);
        spItems.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));

        pnlCliente.setBackground(new java.awt.Color(32, 32, 32));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Clientes");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlEmpleados.setBackground(new java.awt.Color(32, 32, 32));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 153, 255));
        jLabel17.setText("Empleados");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlEmpleadosLayout = new javax.swing.GroupLayout(pnlEmpleados);
        pnlEmpleados.setLayout(pnlEmpleadosLayout);
        pnlEmpleadosLayout.setHorizontalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEmpleadosLayout.setVerticalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlProveedores.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Proveedores");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlProveedoresLayout = new javax.swing.GroupLayout(pnlProveedores);
        pnlProveedores.setLayout(pnlProveedoresLayout);
        pnlProveedoresLayout.setHorizontalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProveedoresLayout.setVerticalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlProductos.setBackground(new java.awt.Color(32, 32, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 255));
        jLabel4.setText("Productos");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlProductosLayout = new javax.swing.GroupLayout(pnlProductos);
        pnlProductos.setLayout(pnlProductosLayout);
        pnlProductosLayout.setHorizontalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProductosLayout.setVerticalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlReporteGeneral.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 153, 255));
        jLabel5.setText("Gestion de reportes generales");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlReporteGeneralLayout = new javax.swing.GroupLayout(pnlReporteGeneral);
        pnlReporteGeneral.setLayout(pnlReporteGeneralLayout);
        pnlReporteGeneralLayout.setHorizontalGroup(
            pnlReporteGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReporteGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlReporteGeneralLayout.setVerticalGroup(
            pnlReporteGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlReporteEstadisticos.setBackground(new java.awt.Color(32, 32, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 153, 255));
        jLabel6.setText("Gestion de reportes estadisticos");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlReporteEstadisticosLayout = new javax.swing.GroupLayout(pnlReporteEstadisticos);
        pnlReporteEstadisticos.setLayout(pnlReporteEstadisticosLayout);
        pnlReporteEstadisticosLayout.setHorizontalGroup(
            pnlReporteEstadisticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReporteEstadisticosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlReporteEstadisticosLayout.setVerticalGroup(
            pnlReporteEstadisticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlRevFacturas.setBackground(new java.awt.Color(32, 32, 32));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("Ventas generadas");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlRevFacturasLayout = new javax.swing.GroupLayout(pnlRevFacturas);
        pnlRevFacturas.setLayout(pnlRevFacturasLayout);
        pnlRevFacturasLayout.setHorizontalGroup(
            pnlRevFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRevFacturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRevFacturasLayout.setVerticalGroup(
            pnlRevFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlRevNotasCred.setBackground(new java.awt.Color(32, 32, 32));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 153, 255));
        jLabel8.setText("Gestion de revision de notas de credito");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlRevNotasCredLayout = new javax.swing.GroupLayout(pnlRevNotasCred);
        pnlRevNotasCred.setLayout(pnlRevNotasCredLayout);
        pnlRevNotasCredLayout.setHorizontalGroup(
            pnlRevNotasCredLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRevNotasCredLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRevNotasCredLayout.setVerticalGroup(
            pnlRevNotasCredLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlRevNotasDeb.setBackground(new java.awt.Color(32, 32, 32));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 153, 255));
        jLabel9.setText("Gestion de revision de notas de debito");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlRevNotasDebLayout = new javax.swing.GroupLayout(pnlRevNotasDeb);
        pnlRevNotasDeb.setLayout(pnlRevNotasDebLayout);
        pnlRevNotasDebLayout.setHorizontalGroup(
            pnlRevNotasDebLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRevNotasDebLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRevNotasDebLayout.setVerticalGroup(
            pnlRevNotasDebLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlUsuarios.setBackground(new java.awt.Color(32, 32, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Usuarios");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlCambioClave.setBackground(new java.awt.Color(32, 32, 32));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 153, 255));
        jLabel11.setText("Cambio de clave");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlCambioClaveLayout = new javax.swing.GroupLayout(pnlCambioClave);
        pnlCambioClave.setLayout(pnlCambioClaveLayout);
        pnlCambioClaveLayout.setHorizontalGroup(
            pnlCambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCambioClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCambioClaveLayout.setVerticalGroup(
            pnlCambioClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlEmpresa.setBackground(new java.awt.Color(32, 32, 32));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 255));
        jLabel12.setText("Empresa");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlEmpresaLayout = new javax.swing.GroupLayout(pnlEmpresa);
        pnlEmpresa.setLayout(pnlEmpresaLayout);
        pnlEmpresaLayout.setHorizontalGroup(
            pnlEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEmpresaLayout.setVerticalGroup(
            pnlEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlSucursal.setBackground(new java.awt.Color(32, 32, 32));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 153, 255));
        jLabel13.setText("Sucursales");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlSucursalLayout = new javax.swing.GroupLayout(pnlSucursal);
        pnlSucursal.setLayout(pnlSucursalLayout);
        pnlSucursalLayout.setHorizontalGroup(
            pnlSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSucursalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSucursalLayout.setVerticalGroup(
            pnlSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlEstadoCaja.setBackground(new java.awt.Color(32, 32, 32));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 153, 255));
        jLabel14.setText("Estado de caja");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlEstadoCajaLayout = new javax.swing.GroupLayout(pnlEstadoCaja);
        pnlEstadoCaja.setLayout(pnlEstadoCajaLayout);
        pnlEstadoCajaLayout.setHorizontalGroup(
            pnlEstadoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstadoCajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEstadoCajaLayout.setVerticalGroup(
            pnlEstadoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlCaja.setBackground(new java.awt.Color(32, 32, 32));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 153, 255));
        jLabel15.setText("Apertura y cierre de caja");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlCajaLayout = new javax.swing.GroupLayout(pnlCaja);
        pnlCaja.setLayout(pnlCajaLayout);
        pnlCajaLayout.setHorizontalGroup(
            pnlCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCajaLayout.setVerticalGroup(
            pnlCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        pnlAuditoria.setBackground(new java.awt.Color(32, 32, 32));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 153, 255));
        jLabel16.setText("Auditoria del sisitema");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlAuditoriaLayout = new javax.swing.GroupLayout(pnlAuditoria);
        pnlAuditoria.setLayout(pnlAuditoriaLayout);
        pnlAuditoriaLayout.setHorizontalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAuditoriaLayout.setVerticalGroup(
            pnlAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAuditoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEstadoCaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSucursal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCambioClave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUsuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRevNotasDeb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRevNotasCred, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRevFacturas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlReporteEstadisticos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlReporteGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProveedores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEmpleados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlReporteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlReporteEstadisticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRevFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRevNotasCred, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRevNotasDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCambioClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEstadoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        spItems.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(spItems, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {roundPanel5, roundPanel6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(spItems))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void p1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p1MouseClicked
//Metodo usado para retroceder pagina
        //El if para comprobar que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //if para que comprueba si la pagina es 1 entonces se ira a la ultima pagina
            this.numImg -= 1;
            if (paginas == 1) {
                paginas = totalp;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            } else {
                paginas = paginas - 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            }
            //Aplicamos la pagina respectiva para mostrarlo
            if (this.numImg < 0) {
                this.numImg = (this.ListaComponente.size() - 1);
            }
            ArchivosVO pi = new ArchivosVO();
            for (int i = 0; i < ListaComponente.size(); i++) {
                pi = ListaComponente.get(numImg);
                break;
            }
            //Con este metodo mostramos la imagen
            this.img.setImagen(pi.getArchivos());
            //Este metodo adapta el zoom a la pantalla
            adaptar_vista();

        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p1MouseClicked

    private void p2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p2MouseClicked
        //Este metodo pasa la pagina hacia adelante
        //Verifica que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Este if comprueba si la pagina esta en el ultimo
            //O si esta en el primero pasara a la siguiente pagina
            this.numImg += 1;
            if (paginas == totalp) {
                paginas = 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            } else {
                paginas = paginas + 1;
                p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));
            }
            //Aplica los cambios de la pagina que corresponde
            if (this.numImg >= this.ListaComponente.size()) {
                this.numImg = 0;
            }
            ArchivosVO pi = new ArchivosVO();
            for (int i = 0; i < ListaComponente.size(); i++) {
                pi = ListaComponente.get(numImg);
                break;
            }
            //Muestra la pagina que corresponde
            this.img.setImagen(pi.getArchivos());
            //adapta la pagina a la pantalla
            adaptar_vista();
        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p2MouseClicked

    private void lblArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArchivoMouseClicked
        JnaFileChooser jnaCh = new JnaFileChooser();
        boolean save = jnaCh.showOpenDialog(null);
        if (save) {
            System.out.println(jnaCh.getSelectedFile());
            //Usamos el metodo abrir pdf para mostrarlo
            ruta_archivo = jnaCh.getSelectedFile().toString();
            lblNombreArchivo.setText("Archivo: " + jnaCh.getSelectedFile().getName());
            abrir_pdf(ruta_archivo);
            this.img.disminuir();
        }
    }//GEN-LAST:event_lblArchivoMouseClicked

    private void lblNombreArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreArchivoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreArchivoMouseClicked

    private void p3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p3MouseClicked
        //Este metodo comprueba que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Aplica el zoom
            this.img.aumentar();
            zoom = zoom + 1;
        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p3MouseClicked

    private void p4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p4MouseClicked
        //Este metodo comrpueba que haya paginas
        if (ruta_archivo.trim().length() != 0) {
            //Disminuir el zoom
            this.img.disminuir();
            zoom = zoom - 1;

        } else {
            JOptionPane.showMessageDialog(null, "Abrir un documento PDF primero");
        }
    }//GEN-LAST:event_p4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try {
            reporteClientes();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        try {
            reporteEmpleados();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
            reporteProveedores();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        try {
            reporteProductos();
        } catch (DocumentException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FORM_REPORTES_GENERALES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mivisorpdf.CuadroImagen img;
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
    private javax.swing.JLabel lblArchivo;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JLabel p;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private com.anthony.swing.RoundPanel pnlAuditoria;
    private com.anthony.swing.RoundPanel pnlCaja;
    private com.anthony.swing.RoundPanel pnlCambioClave;
    private com.anthony.swing.RoundPanel pnlCliente;
    private com.anthony.swing.RoundPanel pnlEmpleados;
    private com.anthony.swing.RoundPanel pnlEmpresa;
    private com.anthony.swing.RoundPanel pnlEstadoCaja;
    private com.anthony.swing.RoundPanel pnlProductos;
    private com.anthony.swing.RoundPanel pnlProveedores;
    private com.anthony.swing.RoundPanel pnlReporteEstadisticos;
    private com.anthony.swing.RoundPanel pnlReporteGeneral;
    private com.anthony.swing.RoundPanel pnlRevFacturas;
    private com.anthony.swing.RoundPanel pnlRevNotasCred;
    private com.anthony.swing.RoundPanel pnlRevNotasDeb;
    private com.anthony.swing.RoundPanel pnlSucursal;
    private com.anthony.swing.RoundPanel pnlUsuarios;
    private com.anthony.swing.RoundPanel roundPanel10;
    private com.anthony.swing.RoundPanel roundPanel2;
    private com.anthony.swing.RoundPanel roundPanel3;
    private com.anthony.swing.RoundPanel roundPanel5;
    private com.anthony.swing.RoundPanel roundPanel6;
    private com.anthony.swing.RoundPanel roundPanel7;
    private com.anthony.swing.RoundPanel roundPanel8;
    private com.anthony.swing.RoundPanel roundPanel9;
    private javax.swing.JScrollPane sc;
    private com.anthony.swing.scrollbar.ScrollBarCustom scrollBarCustom1;
    private javax.swing.JScrollPane spItems;
    // End of variables declaration//GEN-END:variables

//Metodo abrir documento PDF
    public void abrir_pdf(String url) {
        //Es considerado pagina 1
        this.numImg = 0;
        //Lee la pagina 1
        this.ListaComponente = pn.leerPDF(url);
        //Guardamos todas las paginas en el ArrayList
        for (int i = 0; i < ListaComponente.size(); i++) {
            pl = ListaComponente.get(i);
            this.img.setImagen(pl.getArchivos());
        }
        //Pagina 1 lo muestra en la pantalla
        paginas = 1;
        totalp = ListaComponente.size();
        p.setText(String.valueOf("Pagina " + paginas + " de " + totalp));

        //tp.setText(String.valueOf(totalp));
        //Mostramos la primera pagina
        ArchivosVO pi = new ArchivosVO();
        pi = ListaComponente.get(0);
        this.img.setImagen(pi.getArchivos());
    }

    //Metodo para adaptar el zoom a la pantalla
    public void adaptar_vista() {
        System.out.print(zoom);
        if (zoom > 0) {
            for (int i = 0; i <= zoom; i++) {
                this.img.aumentar();
            }
        }
        if (zoom < 0) {
            String z = String.valueOf(zoom);
            z = z.replace("-", "");
            int zz = Integer.parseInt(z);
            for (int j = 0; j <= zz; j++) {
                this.img.disminuir();
            }
        }

    }

    public static void abrirFichero(String ruta) {
        Desktop ficheroAEjecutar = Desktop.getDesktop();
        try {
            ficheroAEjecutar.open(new File(ruta));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
//Contador de paginas
    private int numImg;

    //Contiene el archivo PDF en bytes de imagenes
    private ArrayList<ArchivosVO> ListaComponente;
    MiVisorPDF pn = new MiVisorPDF();
    ArchivosVO pl = new ArchivosVO();

    //Para mostrar el total de paginas
    private int paginas = -1;
    private int totalp = -1;

    //Guardamos la ruta del PDF--------------------------------------
    private String ruta_archivo = "";
    //---------------------------------------------------------------

    //Mantiene el zoom en la pagina que asignamos
    int zoom = 0;

    private void scroll() {
        spItems.setVerticalScrollBar(new ScrollBarCustom());
        sc.setVerticalScrollBar(new ScrollBarCustom());
        sc.setHorizontalScrollBar(scrollBarCustom1);
        sc.getViewport().setOpaque(false);
        lblNombreArchivo.setText("Sin documento");
    }

    public void bordes() {
        sc.setVerticalScrollBar(new ScrollBarCustom());
        sc.setBorder(border);
    }
}
