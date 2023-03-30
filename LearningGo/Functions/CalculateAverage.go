package main

import "fmt"

func main() {
	var nums = []float64{1.0, 2.0, 3.0, 4.0}
	fmt.Println("Average is", getAverage(nums))
}

func getAverage(nums []float64) float64 {
	var sum = 0.0

	for _, num := range nums {
		sum += num
	}

	return sum / float64(len(nums))
}
