package org.walkerljl.toolkit.standard;

/**
 * Simple name-value holder.
 *
 * @author xingxun
 * @param <N>
 * @param <V>
 */
public class NameValue<N, V> {

    protected N name;
    protected V value;

    public NameValue() {}

    public NameValue(N name, V value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Sets name.
     */
    public void setName(N name) {
        this.name = name;
    }

    /**
     * Returns name.
     */
    public N getName() {
        return name;
    }

    /**
     * Returns value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets value.
     */
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        NameValue<N, V> other = (NameValue<N, V>) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}