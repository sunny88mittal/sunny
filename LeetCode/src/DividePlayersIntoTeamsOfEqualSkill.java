import java.util.HashMap;
import java.util.Map;

public class DividePlayersIntoTeamsOfEqualSkill {
	public long dividePlayers(int[] skill) {
		if (skill.length == 2) {
			return skill[0] * skill[1];
		}

		int sum = 0;
		int length = skill.length;
		int teams = length / 2;

		Map<Integer, Integer> skillsCountMap = new HashMap<Integer, Integer>();
		for (int i : skill) {
			sum += i;
			skillsCountMap.put(i, skillsCountMap.getOrDefault(i, 0) + 1);
		}
		if (sum % teams == 1) {
			return -1;
		}

		int skillToTarget = sum / teams;
		long chemistry = 0;

		for (int i = 0; i < skill.length; i++) {
			int currentSkill = skill[i];
			if (skillsCountMap.get(currentSkill) == 0) {
				continue;
			}
			int target = skillToTarget - currentSkill;
			if (skillsCountMap.get(target) != null && skillsCountMap.get(target) > 0) {
				chemistry += currentSkill * target;
			} else {
				return -1;
			}

			skillsCountMap.put(currentSkill, skillsCountMap.get(currentSkill) - 1);
			skillsCountMap.put(target, skillsCountMap.get(target) - 1);
		}

		return chemistry;
	}

	public static void main(String args[]) {
		DividePlayersIntoTeamsOfEqualSkill obj = new DividePlayersIntoTeamsOfEqualSkill();
		int[] skill = new int[] { 5, 3, 7, 1 };
		obj.dividePlayers(skill);
	}
}
