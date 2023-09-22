/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generics_aula2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cassio
 */
public class Fila<T> {

    private List<T> list;

    public Fila() {
        list = new ArrayList<>();
    }
    
    
    public void insert(T t) {
        list.add(t);
    }

    public T remove() {
        return list.remove(0);
    }
    
    public T first(){
        return list.get(0);
    }
    
    public T last(){
        return list.get(list.size()-1);
    }
    
    public int size(){
        return list.size();
    }
    
    
}
