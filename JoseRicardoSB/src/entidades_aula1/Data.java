/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_aula1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cassio
 */
public class Data {
    private Date data;
    
    public Data(){
        data = new Date();
    }
    
    public Data(Date data){
        this.data = data;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    
    public String toString(boolean dia, boolean mes, boolean ano) {
        String pattern = "";
        if(dia){
            pattern+="dd";
        }
        if(mes){
            if(dia){
                pattern+="/";
            }
            pattern+="MM";
        }
        if(ano){
            if(dia||mes){
                pattern+="/";
            }
            pattern+="yyyy";
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(data);
    }
    
    
    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
}
