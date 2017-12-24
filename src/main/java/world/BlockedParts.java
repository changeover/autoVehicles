package world;

import java.util.ArrayList;

public interface BlockedParts {

    void addBlockedPart(int x, int y);

    ArrayList<Integer> getBlockedX();

    ArrayList<Integer> getBlockedY();

    boolean hasBlockedX(int x);

    boolean hasBlockedY(int y);

    boolean hasBlocked(int x, int y);
}
