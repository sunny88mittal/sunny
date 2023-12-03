package solution2500to2600;

public class Solution2591 {
	public int distMoney(int money, int children) {
		if (children > money) {
			return -1;
		}

		int qt = money / 8;
		int rem = money - 8 * qt;

		// Distribute 8 dollars to every child.
		if (qt == children && rem == 0)
			return qt;

		// If last child is going to get 4 dollar, adjust it with other child
		if (qt == children - 1 && rem == 4) {
			return --qt;
		}

		// money greater than 8* children, one child will get more than 8, rest 8
		if (qt >= children) {
			return children - 1;
		}

		// Decrease 8 dollar childs, till everybody gets at least 1
		while (qt + rem < children) {
			--qt;
			rem += 8;
		}
		return qt;
	}
}
