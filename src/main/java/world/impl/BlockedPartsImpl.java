package world.impl;

import world.BlockedParts;

import java.util.ArrayList;

public class BlockedPartsImpl implements BlockedParts {
    ArrayList<Integer> blockedX;
    ArrayList<Integer> blockedY;

    public BlockedPartsImpl(){
        blockedX = new ArrayList<>();
        blockedY = new ArrayList<>();
    }
    public void addBlockedPart(int x, int y) {
        blockedX.add(x);
        blockedY.add(y);
    }

    public ArrayList<Integer> getBlockedX() {
        return this.blockedX;
    }

    public ArrayList<Integer> getBlockedY() {
        return this.blockedY;
    }

    public boolean hasBlockedX(int x) {
        return blockedX.contains(x);
    }

    public boolean hasBlockedY(int y) {
        return blockedY.contains(y);
    }
    public boolean hasBlocked(int x, int y){
        boolean blocked = false;
        int index;
        for(int i=0;i<=36;i++) {
            int angle = i*10;
            double tempX = x + Math.cos(angle);
            double tempY = y + Math.sin(angle);
            if (blockedX.contains((int)tempX)) {
                index = blockedX.indexOf((int)tempX);
                if (blockedY.get(index) == (int)tempY) {
                    blocked = true;
                }
            }
        }
        return blocked;
    }
}
