import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode.com/problems/course-schedule-ii/ **/
public class CourseSchedule2 {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] courseOrder = new int[numCourses];
		int[] dependencyCount = new int[numCourses];
		Map<Integer, List<Integer>> reverseDependenciesMap = new HashMap<Integer, List<Integer>>();
		int completedCount = 0;

		// Prepare reverse dependency list
		for (int i = 0; i < prerequisites.length; i++) {
			int course = prerequisites[i][0];
			int required = prerequisites[i][1];
			dependencyCount[course] += 1;

			List<Integer> dependencies = reverseDependenciesMap.get(required);
			if (dependencies == null) {
				dependencies = new ArrayList<Integer>();
				reverseDependenciesMap.put(required, dependencies);
			}
			dependencies.add(course);
		}

		// Get courses with no dependencies
		List<Integer> completedCoursesQueue = new ArrayList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (dependencyCount[i] == 0) {
				completedCoursesQueue.add(i);
				courseOrder[completedCount] = i;
				++completedCount;
			}
		}

		// Recursively mark all courses which can be completed
		while (completedCoursesQueue.size() > 0) {
			int completedCourse = completedCoursesQueue.remove(0);
			if (reverseDependenciesMap.get(completedCourse) != null) {
				for (Integer course : reverseDependenciesMap.get(completedCourse)) {
					dependencyCount[course] -= 1;
					if (dependencyCount[course] == 0) {
						completedCoursesQueue.add(course);
						courseOrder[completedCount] = course;
						++completedCount;
					}
				}
			}
		}

		if (completedCount != numCourses) {
			return new int[] {};
		}

		return courseOrder;
	}

	public static void main(String args[]) {
		CourseSchedule2 obj = new CourseSchedule2();
		obj.findOrder(2, new int[][] { { 1, 0 } });
	}
}
