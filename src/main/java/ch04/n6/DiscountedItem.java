package ch04.n6;

import java.util.Objects;

public class DiscountedItem extends Item {
    private double discount;

    public DiscountedItem(double price, double discount) {
        super(price);
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {

        if (o.getClass() == this.getClass()) {
            if (!super.equals(o)) return false;
            DiscountedItem that = (DiscountedItem) o;
            return Double.compare(that.discount, discount) == 0;
        } else return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount);
    }
}
