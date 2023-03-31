package main

import "fmt"

func main() {
	fmt.Println(addNumbers(2, 3))
	fmt.Println(addNumbers(-2, 3))
}

func handlePanic() {
	a := recover()

	if a != nil {
		fmt.Println("RECVOVER", a)
	}
}

func addNumbers(a, b int) int {
	defer handlePanic()

	if a < 0 || b < 0 {
		panic("Negative no")
	}

	return a + b
}
