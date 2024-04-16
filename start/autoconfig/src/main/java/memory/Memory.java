package memory;

public class Memory {

    private long used;
    private long max;
    private float percentOfUsed;

    public Memory(long used, long max) {
        this.used = used;
        this.max = max;
        this.percentOfUsed = (float)used / max * 100;
    }

    public long getUsed() {
        return used;
    }

    public long getMax() {
        return max;
    }

    public float getPercentOfUsed() {
        return percentOfUsed;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "used=" + used +
                ", max=" + max +
                ", percentOfUsed=" + percentOfUsed +
                '}';
    }
}
