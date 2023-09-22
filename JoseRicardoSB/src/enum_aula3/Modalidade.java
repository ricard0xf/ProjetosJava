/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enum_aula3;

/**
 *
 * @author cassio
 */
public enum Modalidade {
    FUTEBOL("Futebol"),
    GINASTICA("Ginástica"),
    NATACAO("Natação"),
    ATLETISMO("Atletismo");
    
    private String value;
    Modalidade(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
