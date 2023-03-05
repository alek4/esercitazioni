public abstract class AbstractMultiSet implements StringMultiSet {
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        for (String el : this) {
            sb.append(String.format("%s: %d, ", el, multiplicity(el)));
        }

        sb.append("}");
        return sb.toString();
    }
}
