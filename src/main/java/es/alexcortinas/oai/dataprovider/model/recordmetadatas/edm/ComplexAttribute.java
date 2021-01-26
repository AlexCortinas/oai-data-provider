package es.alexcortinas.oai.dataprovider.model.recordmetadatas.edm;

import es.alexcortinas.oai.dataprovider.model.recordmetadatas.MultipleAttribute;

public class ComplexAttribute<E> {

	private MultipleAttribute<E> values;
	private IContextualClassEDM ref;

	public ComplexAttribute() {
		super();
		this.values = new MultipleAttribute<E>();
	}

	public ComplexAttribute(IContextualClassEDM ref) {
		super();
		this.values = new MultipleAttribute<E>();
		this.ref = ref;
	}

	public void addRef(IContextualClassEDM ref) {
		this.ref = ref;
	}

	public void removeRef(E value) {
		this.ref = null;
	}

	public void reset() {
		this.values.resetValues();
		this.ref = null;
	}

	public boolean hasRef() {
		if (ref == null) {
			return false;
		}

		return true;
	}

	public MultipleAttribute<E> getValues() {
		return values;
	}

	public void setValues(MultipleAttribute<E> multipleAttribute) {
		this.values = multipleAttribute;
	}

	public IContextualClassEDM getRef() {
		return ref;
	}

	public void setRef(IContextualClassEDM ref) {
		this.ref = ref;
	}

}
