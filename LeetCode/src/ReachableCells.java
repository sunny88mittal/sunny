public class ReachableCells {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        boolean moved = false;
        while (true) {
            int xd = Math.abs(sx -fx);
            int yd = Math.abs(sy -fy);

            int toReduce = 0;
            if (xd == 0) {
                toReduce = yd;
            } else if (yd == 0) {
                toReduce = xd;
            } else if (xd < yd) {
                toReduce = xd;
            } else {
                toReduce = yd;
            }

            if (xd != 0) {
                if (sx > fx) {
                    sx -= toReduce;
                } else {
                    fx -= toReduce;
                }
            }

            if (yd != 0) {
                if (sy > fy) {
                    sy -= toReduce;
                } else {
                    fy -= toReduce;
                }
            }
            
            if (toReduce > 0) {
                moved = true;
            }
            t-= toReduce;
            if (toReduce == 0) {
                if (moved) {
                    break;
                } 

                if (t == 0) {
                    break;
                }

                t = t > 1 ? 0 : -1;
                break;
            }
        }

        return t >= 0;
    }
}
