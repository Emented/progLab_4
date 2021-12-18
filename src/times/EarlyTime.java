package times;

import java.util.Objects;

public class EarlyTime implements InterfaceOfTime {
    private final String name;

    public EarlyTime() {
        name = "очень - очень рано";
    }

    @Override
    public String getTime() {
        return this.name;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name: " + name + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object anotherObj) {
        if (this == anotherObj) return true;
        if (anotherObj == null) return false;
        if (getClass() != anotherObj.getClass()) return false;
        EarlyTime anotherTime = (EarlyTime) anotherObj;
        return name.equals(anotherTime.name);
    }
}
