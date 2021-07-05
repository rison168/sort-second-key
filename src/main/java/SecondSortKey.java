import java.io.Serializable;

/**
 * @author : Rison 2021/7/5 下午2:43
 * <p>
 * 二次排序
 */
public class SecondSortKey implements Serializable, Comparable<SecondSortKey> {
    private int first;
    private int second;

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public SecondSortKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 先比较第一个成员属性，如果相同在比较第二个大小
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(SecondSortKey other) {
        if (first - other.getFirst() == 0) {
            return second - other.getSecond();
        }else {
            return first - other.getFirst();
        }
    }
}
