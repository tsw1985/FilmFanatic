package dad.filmfanatic.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ListTableModel<E> implements TableModel {

	private List<TableModelListener> listeners;
	private List<ColumnDefinition> columnDefinitions;
	private List<E> objects = new ArrayList<E>();

	public ListTableModel() {
		listeners = new ArrayList<TableModelListener>();
		columnDefinitions = new ArrayList<ColumnDefinition>();
	}

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return columnDefinitions.get(column).getClazz();
	}

	@Override
	public int getColumnCount() {
		return columnDefinitions.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnDefinitions.get(column).getTitle();
	}

	@Override
	public int getRowCount() {
		return objects.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		ColumnDefinition cd = columnDefinitions.get(column);
		String propertyName = cd.getPropertyName();
		Object object = objects.get(row);
		return get(object, propertyName);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return columnDefinitions.get(column).isEditable();
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		ColumnDefinition cd = columnDefinitions.get(column);
		String propertyName = cd.getPropertyName();
		Object object = objects.get(row);
		set(object, propertyName, value);
		throwEvent(new TableModelEvent(this, row, row, column, TableModelEvent.UPDATE));
	}

	public List<E> getValues() {
		return objects;
	}

	public void setValues(List<E> objects) {
		this.objects = objects;
		throwEvent(new TableModelEvent(this, 
				TableModelEvent.HEADER_ROW, 
				0, 
				TableModelEvent.ALL_COLUMNS,
				TableModelEvent.DELETE)
		);
	}

	public List<ColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
	}

	private void throwEvent(TableModelEvent e) {
		for (TableModelListener listener : listeners) {
			listener.tableChanged(e);
		}
	}
	
	public void addColumn(String title, Class<?> clazz, Boolean editable, String propertyName) {
		
		getColumnDefinitions().add(new ColumnDefinition(title, clazz, editable, propertyName));
	}

	
	public static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
            	field.set(object, fieldValue);
	            return true;
	            
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    return false;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E get(Object object, String fieldName) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            return (E) field.get(object);
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            return null;
	        }
	    }
	    return null;
	}
	
	protected class ColumnDefinition {
		
		private String title;
		private Class<?> clazz;
		private Boolean editable;
		private String propertyName;

		public ColumnDefinition(String title, Class<?> clazz, Boolean editable, String propertyName) {
			this.title = title;
			this.clazz = clazz;
			this.editable = editable;
			this.propertyName = propertyName;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Boolean isEditable() {
			return editable;
		}

		public void setEditable(Boolean editable) {
			this.editable = editable;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

	}

	
}
