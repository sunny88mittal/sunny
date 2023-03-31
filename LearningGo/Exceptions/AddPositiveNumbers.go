package main

import "fmt"

func main() {
	fmt.Println(addNumbers(2, 3))
	fmt.Println(addNumbers(-2, 3))
}

func addNumbers(a, b int) int {
	if a < 0 || b < 0 {
		panic("Negative no")
	}

	return a + b
}
