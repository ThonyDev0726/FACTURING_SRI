 
package com.anthony.XML;


import java.math.BigDecimal;


/**
 *
 * @author Hugo
 */
 
    
public class FacturaImp {
    
    
    private String codigo;
    private String codigoPorcentaje;
    private String descuentoAdicional;
    private BigDecimal baseImponible;


    private String tarifa;
    private BigDecimal valor;

    
    
        public FacturaImp(String codigo, String codigoPorcentaje, String descuentoAdicional,
             BigDecimal baseImponible,String tarifa, BigDecimal valor) {
        this.codigo = codigo;
        this.codigoPorcentaje = codigoPorcentaje;
        this.descuentoAdicional = descuentoAdicional;
        this.baseImponible = baseImponible;
        this.tarifa = tarifa;
        this.valor=valor;
    }
    
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }
    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoPorcentaje
     */
    public String getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    /**
     * @param codigoPorcentaje the codigoPorcentaje to set
     */
    public void setCodigoPorcentaje(String codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    /**
     * @return the baseImponible
     */
   

    /**
     * @return the valor
     */
    
    /**
     * @return the descuentoAdicional
     */
    public String getDescuentoAdicional() {
        return descuentoAdicional;
    }

    /**
     * @param descuentoAdicional the descuentoAdicional to set
     */
    public void setDescuentoAdicional(String descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    /**
     * @return the tarifa
     */
    public String getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }
    
}

