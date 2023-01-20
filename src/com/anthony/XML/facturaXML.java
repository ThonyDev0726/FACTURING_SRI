package com.anthony.XML;

import com.anthony.Models.EMPRESA;
import com.anthony.ModelsDAO.EMPRESA_DAO;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author perez
 */
public class facturaXML {

    public static void main(String[] args) {
        try {
            EMPRESA emp = new EMPRESA_DAO().list();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "factura", null);
            documento.setXmlVersion("1.0");

            /* ===========================            
            agregar al nodo infoTributaria
            =========================== */
            Element infoTributaria = documento.createElement("infoTributaria");
            /* ===========================            
            Elementos nodo infoTributaria
            =========================== */
            Element ambiente = documento.createElement("ambiente");
            Element tipoEmision = documento.createElement("tipoEmision");
            Element razonSocial = documento.createElement("razonSocial");
            Element nombreComercial = documento.createElement("nombreComercial");
            Element ruc = documento.createElement("ruc");
            Element claveAcceso = documento.createElement("claveAcceso");
            Element codDoc = documento.createElement("codDoc");
            Element estab = documento.createElement("estab");
            Element ptoEmi = documento.createElement("ptoEmi");
            Element secuencial = documento.createElement("secuencial");
            Element dirMatriz = documento.createElement("dirMatriz");
            Element regimenMicroempresas = documento.createElement("regimenMicroempresas");

            /* ===========================            
            VALORES DEL TEXTO DEL NODO infoTributaria
            =========================== */
            Text textAmbiente = documento.createTextNode("1");
            Text textTipoEmision = documento.createTextNode("1");
            Text textRazonSocial = documento.createTextNode(emp.getEMP_RAZON_SOCIAL());
            Text textNombreComercial = documento.createTextNode(emp.getEMP_NOMBRE_COMERCIAL());
            Text textRuc = documento.createTextNode(emp.getEMP_RUC());
            Text textClaveAcceso = documento.createTextNode("textClaveAcceso");
            Text textCodDoc = documento.createTextNode("01");
            Text textEstab = documento.createTextNode("001");
            Text textPtoEmi = documento.createTextNode("001");
            Text textSecuencial = documento.createTextNode("000000002");
            Text textDirMatriz = documento.createTextNode(emp.getEMP_MATRIZ());
            Text textRegimenMicroempresas = documento.createTextNode("textRegimenMicroempresas");

            //datos agregados a los nodos correspondientes            
            ambiente.appendChild(textAmbiente);
            tipoEmision.appendChild(textTipoEmision);
            razonSocial.appendChild(textRazonSocial);
            nombreComercial.appendChild(textNombreComercial);
            ruc.appendChild(textRuc);
            claveAcceso.appendChild(textClaveAcceso);
            codDoc.appendChild(textCodDoc);
            estab.appendChild(textEstab);
            ptoEmi.appendChild(textPtoEmi);
            secuencial.appendChild(textSecuencial);
            dirMatriz.appendChild(textDirMatriz);
            regimenMicroempresas.appendChild(textRegimenMicroempresas);

            /* ===========================            
            agregar al nodo infoTributaria
            =========================== */
            infoTributaria.appendChild(ambiente);
            infoTributaria.appendChild(tipoEmision);
            infoTributaria.appendChild(razonSocial);
            infoTributaria.appendChild(nombreComercial);
            infoTributaria.appendChild(ruc);
            infoTributaria.appendChild(claveAcceso);
            infoTributaria.appendChild(codDoc);
            infoTributaria.appendChild(estab);
            infoTributaria.appendChild(ptoEmi);
            infoTributaria.appendChild(secuencial);
            infoTributaria.appendChild(dirMatriz);
            infoTributaria.appendChild(regimenMicroempresas);

            /* ===========================            
            agregar al nodo infoTributaria
            =========================== */
            Element infoFactura = documento.createElement("infoFactura");

            /* ===========================            
            Elementos nodo infoFactura
            =========================== */
            Element fechaEmision = documento.createElement("fechaEmision");
            Element dirEstablecimiento = documento.createElement("dirEstablecimiento");
            Element contribuyenteEspecial = documento.createElement("contribuyenteEspecial");
            Element obligadoContabilidad = documento.createElement("obligadoContabilidad");
            Element tipoIdentificacionComprador = documento.createElement("tipoIdentificacionComprador");
            Element razonSocialComprador = documento.createElement("razonSocialComprador");
            Element identificacionComprador = documento.createElement("identificacionComprador");
            Element totalSinImpuestos = documento.createElement("totalSinImpuestos");
            Element totalDescuento = documento.createElement("totalDescuento");
            Element totalConImpuestos = documento.createElement("totalConImpuestos");// Agregar nuevo nodo
            /**/
            Element totalImpuesto = documento.createElement("totalImpuesto");// Agregar nuevo nodo
            totalConImpuestos.appendChild(totalImpuesto);
            Element codigo = documento.createElement("codigo");// Agregar nuevo nodo
            Element codigoPorcentaje = documento.createElement("codigoPorcentaje");// Agregar nuevo nodo
            Element baseImponible = documento.createElement("baseImponible");// Agregar nuevo nodo
            Element tarifa = documento.createElement("tarifa");// Agregar nuevo nodo
            Element valor = documento.createElement("valor");// Agregar nuevo nodo
            totalImpuesto.appendChild(codigo);
            totalImpuesto.appendChild(codigoPorcentaje);
            totalImpuesto.appendChild(baseImponible);
            totalImpuesto.appendChild(tarifa);
            totalImpuesto.appendChild(valor);
            /**/
            Element propina = documento.createElement("propina");
            Element importeTotal = documento.createElement("importeTotal");
            Element moneda = documento.createElement("moneda");
            Element pagos = documento.createElement("pagos");// Agregar nuevo nodo
            Element pago = documento.createElement("pago");// Agregar nuevo nodo
            Element formaPago = documento.createElement("formaPago");// Agregar nuevo nodo
            Element totalPago = documento.createElement("totalPago");// Agregar nuevo nodo
            Element plazo = documento.createElement("plazo");// Agregar nuevo nodo
            Element unidadTiempo = documento.createElement("unidadTiempo");// Agregar nuevo nodo
            Element valorRetIva = documento.createElement("valorRetIva");
            Element valorRetRenta = documento.createElement("valorRetRenta");

            //datos agregados a los nodos correspondientes    
            //
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Text textFechaEmision = documento.createTextNode(dtf2.format(LocalDate.now()));
            Text textDirEstablecimiento = documento.createTextNode(emp.getEMP_ESTABLECIMIENTO());
            Text textContribuyenteEspecial = documento.createTextNode(emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL());
            Text textObligadoContabilidad = documento.createTextNode(emp.getEMP_LLEVAR_CONTABILIDAD());
            Text textTipoIdentificacionComprador = documento.createTextNode("textTipoIdentificacionComprador");
            Text textRazonSocialComprador = documento.createTextNode("textRazonSocialComprador");
            Text textIdentificacionComprador = documento.createTextNode("textIdentificacionComprador");
            Text textTotalSinImpuestos = documento.createTextNode("textTotalSinImpuestos");
            Text textTotalDescuento = documento.createTextNode("textTotalDescuento");
            Text textCodigo = documento.createTextNode("textCodigo");
            Text textCodigoPorcentaje = documento.createTextNode("textCodigoPorcentaje");
            Text textBaseImponible = documento.createTextNode("textBaseImponible");
            Text textTarifa = documento.createTextNode("textTarifa");
            Text textValor = documento.createTextNode("textValor");
            Text textPropina = documento.createTextNode("textPropina");
            Text textImporteTotal = documento.createTextNode("textImporteTotal");
            Text textMoneda = documento.createTextNode("textMoneda");
            Text textFormaPago = documento.createTextNode("textFormaPago");
            Text textTotalPago = documento.createTextNode("textTotalPago");
            Text textPlazo = documento.createTextNode("textPlazo");
            Text textUnidadTiempo = documento.createTextNode("textUnidadTiempo");
            Text textRetIva = documento.createTextNode("textRetIva");
            Text textValorRetRenta = documento.createTextNode("textValorRetRenta");

            /**/
            fechaEmision.appendChild(textFechaEmision);
            dirEstablecimiento.appendChild(textDirEstablecimiento);
            contribuyenteEspecial.appendChild(textContribuyenteEspecial);
            obligadoContabilidad.appendChild(textObligadoContabilidad);
            tipoIdentificacionComprador.appendChild(textTipoIdentificacionComprador);
            razonSocialComprador.appendChild(textRazonSocialComprador);
            identificacionComprador.appendChild(textIdentificacionComprador);
            totalSinImpuestos.appendChild(textTotalSinImpuestos);
            totalDescuento.appendChild(textTotalDescuento);
            pagos.appendChild(pago);
            pago.appendChild(formaPago);
            pago.appendChild(totalPago);
            pago.appendChild(plazo);
            pago.appendChild(unidadTiempo);
            codigo.appendChild(textCodigo);
            codigoPorcentaje.appendChild(textCodigoPorcentaje);
            baseImponible.appendChild(textBaseImponible);
            tarifa.appendChild(textTarifa);
            valor.appendChild(textValor);

            propina.appendChild(textPropina);
            importeTotal.appendChild(textImporteTotal);
            moneda.appendChild(textMoneda);
            valor.appendChild(textValor);

            formaPago.appendChild(textFormaPago);
            totalPago.appendChild(textTotalPago);
            plazo.appendChild(textPlazo);
            unidadTiempo.appendChild(textUnidadTiempo);
            valorRetIva.appendChild(textRetIva);
            valorRetRenta.appendChild(textValorRetRenta);

            /* ===========================            
            Insercion al nodo infoTributaria
            =========================== */
            infoFactura.appendChild(fechaEmision);
            infoFactura.appendChild(dirEstablecimiento);
            infoFactura.appendChild(contribuyenteEspecial);
            infoFactura.appendChild(obligadoContabilidad);
            infoFactura.appendChild(tipoIdentificacionComprador);
            infoFactura.appendChild(razonSocialComprador);
            infoFactura.appendChild(identificacionComprador);
            infoFactura.appendChild(totalSinImpuestos);
            infoFactura.appendChild(totalDescuento);
            infoFactura.appendChild(totalConImpuestos);
            infoFactura.appendChild(propina);
            infoFactura.appendChild(importeTotal);
            infoFactura.appendChild(moneda);
            infoFactura.appendChild(pagos);
            infoFactura.appendChild(valorRetIva);
            infoFactura.appendChild(valorRetRenta);

            /* ===========================            
            agregar al nodo detalles
            =========================== */
            Element detalles = documento.createElement("detalles");
            ArrayList<String> listaProductos = new ArrayList<String>();
            listaProductos.add("Hola1");
            listaProductos.add("Hola2");
            listaProductos.add("Hola3");
            listaProductos.add("Hola4");
            listaProductos.add("Hola5");
            for (int i = 0; i < listaProductos.size(); i++) {
                Element detalle = documento.createElement("detalle");
                detalles.appendChild(detalle);
                Element codigoPrincipal = documento.createElement("codigoPrincipal");
                Element descripcion = documento.createElement("descripcion");
                Element cantidad = documento.createElement("cantidad");
                Element precioUnitario = documento.createElement("precioUnitario");
                Element descuento = documento.createElement("descuento");
                Element precioTotalSinImpuesto = documento.createElement("precioTotalSinImpuesto");
                Element impuestos = documento.createElement("impuestos");
                Element impuesto = documento.createElement("impuesto");
                Element codigoDetalle = documento.createElement("codigo");
                Element codigoPorcentajeDetalle = documento.createElement("codigoPorcentaje");
                Element tarifaDetalle = documento.createElement("tarifa");
                Element baseImponibleDetalle = documento.createElement("baseImponible");
                Element valorDetalle = documento.createElement("valor");

                //Informacion 
                Text textCodPrincipal = documento.createTextNode(listaProductos.get(i));
                Text textDescripcion = documento.createTextNode("textDescripcion");
                Text textCantidad = documento.createTextNode("textCantidad");
                Text textPrecioUnitario = documento.createTextNode("textPrecioUnitario");
                Text textDescuento = documento.createTextNode("textDescuento");
                Text textPrecioTotalSinImpuesto = documento.createTextNode("textPrecioTotalSinImpuesto");
                Text textCodigoDetalle = documento.createTextNode("textCodigoDetalle");
                Text textCodigoPorcentajeDetalle = documento.createTextNode("textCodigoPorcentajeDetalle");
                Text textTarifaDetalle = documento.createTextNode("textTarifaDetalle");
                Text textBaseImponibleDetalle = documento.createTextNode("textBaseImponibleDetalle");
                Text textValorDetalle = documento.createTextNode("textValorDetalle");

                /* ===========================            
            agregar al nodo detalles
            =========================== */
                detalle.appendChild(codigoPrincipal);
                detalle.appendChild(descripcion);
                detalle.appendChild(cantidad);
                detalle.appendChild(precioUnitario);
                detalle.appendChild(descuento);
                detalle.appendChild(precioTotalSinImpuesto);
                detalle.appendChild(impuestos);
                impuestos.appendChild(impuesto);
                impuesto.appendChild(codigoDetalle);
                impuesto.appendChild(codigoPorcentajeDetalle);
                impuesto.appendChild(tarifaDetalle);
                impuesto.appendChild(baseImponibleDetalle);
                impuesto.appendChild(valorDetalle);
                //agregar texto al nodo detalle
                codigoPrincipal.appendChild(textCodPrincipal);
                descripcion.appendChild(textDescripcion);
                cantidad.appendChild(textCantidad);
                precioUnitario.appendChild(textPrecioUnitario);
                descuento.appendChild(textDescuento);
                precioTotalSinImpuesto.appendChild(textPrecioTotalSinImpuesto);
                codigoDetalle.appendChild(textCodigoDetalle);
                codigoPorcentajeDetalle.appendChild(textCodigoPorcentajeDetalle);
                tarifaDetalle.appendChild(textTarifaDetalle);
                baseImponibleDetalle.appendChild(textBaseImponibleDetalle);
                valorDetalle.appendChild(textValorDetalle);
            }


            /* ===========================            
            agregar al nodo principal
            =========================== */
            documento.getDocumentElement().appendChild(infoTributaria);
            documento.getDocumentElement().appendChild(infoFactura);
            documento.getDocumentElement().appendChild(detalles);

            documento.setXmlStandalone(true);
            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("factura.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //Para formatear el documento
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
