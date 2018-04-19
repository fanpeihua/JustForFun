package fanpeihua.justforfun.cutomview.refresh;

/**
 * Created by oneball on 2018/3/27.
 */

public enum SwipeRefreshLayoutDirection {
    TOP(0),
    BOTTOM(1),
    BOTH(2);

    private int mValue;

    SwipeRefreshLayoutDirection(int value) {
        this.mValue = value;
    }

    public static SwipeRefreshLayoutDirection getFromInt(int value) {
        for (SwipeRefreshLayoutDirection direction : SwipeRefreshLayoutDirection.values()) {
            if (direction.mValue == value) {
                return direction;
            }
        }
        return BOTH;
    }
}
