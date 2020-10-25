package gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ModeloLista extends AbstractListModel{
	private Object[] items;
	
	public ModeloLista(ArrayList<String> a) {
		items = a.toArray();
	}
	
    @Override
    public Object getElementAt(int index) {
        return items[index];
    }

    @Override
    public int getSize() {
        return items.length;
    }
    
    public void update(ArrayList<String> a) {
    	items = a.toArray();
        this.fireContentsChanged(this, 0, items.length - 1);
    }
}
