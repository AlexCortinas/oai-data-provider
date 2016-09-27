package es.alexcortinas.oai.dataprovider.model.recordmetadatas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.alexcortinas.oai.dataprovider.util.StringUtils;

public class MultipleAttribute<E> {
    private List<E> list;

    @Deprecated
    public MultipleAttribute(E... values) {
        list = new ArrayList<E>();
        this.addValues(values);
    }

    public MultipleAttribute() {
        list = new ArrayList<E>();
    }

    public void addValue(E value) {
        if (value != null && !StringUtils.isBlank(value.toString())) {
            if (!list.contains(value)) {
                list.add(value);
            }
        }
    }

    public MultipleAttribute<E> addValues(Collection<E> values) {
        if (values != null) {
            for (E e : values) {
                if (e != null && !StringUtils.isBlank(e.toString())) {
                    addValue(e);
                }
            }
        }
        return this;
    }

    @Deprecated
    public MultipleAttribute<E> addValues(E... values) {
        if (values.length > 0) {
            for (E e : values) {
                if (e != null && !StringUtils.isBlank(e.toString())) {
                    addValue(e);
                }
            }
        }
        return this;
    }

    public MultipleAttribute<E> removeValues(E value) {
        if (value != null) {
            if (value != null && !StringUtils.isBlank(value.toString())) {
                list.remove(value);
            }
        }
        return this;
    }

    @Deprecated
    public MultipleAttribute<E> removeValues(E... values) {
        if (values.length > 0) {
            for (E e : values) {
                if (e != null && !StringUtils.isBlank(e.toString())) {
                    list.remove(e);
                }
            }
        }
        return this;
    }

    public void resetValues() {
        this.list = new ArrayList<E>();
    }

    public boolean hasValues() {
        if (list == null) {
            return false;
        }

        if (list.size() == 0) {
            return false;
        }

        return true;
    }

    public List<String> toList() {
        List<String> ret = new ArrayList<String>();

        if (hasValues()) {
            for (E e : list) {
                ret.add(e.toString());
            }
        }

        return ret;
    }

}
