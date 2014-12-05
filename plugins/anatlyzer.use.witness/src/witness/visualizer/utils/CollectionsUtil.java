package witness.visualizer.utils;

import java.util.*;

import org.eclipse.emf.ecore.*;


public class CollectionsUtil {
		
	public static <T> List<T> select (List<? extends T> list, IPredicate<? super T> pred) {
		List<T> lista = new ArrayList<T>();

		for (T el : list) 
			if (pred.applies(el)) lista.add(el);
		
		return lista;
	}	
	
	public static <T, R extends T> List<R> selectAs (List<? extends T> list, IPredicate<? super T> pred) {
		List<R> lista = new ArrayList<R>();

		for (T el : list) 
			if (pred.applies(el)) lista.add((R)el);
		
		return lista;
	}
}
