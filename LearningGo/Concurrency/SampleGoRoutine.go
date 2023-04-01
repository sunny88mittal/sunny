package main

import (
	"fmt"
	"time"
)

func testGoRoutines(str string) {
	for i := 0; i < 5; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(str)
	}
}

func main() {
	fmt.Println("Starting")

	go testGoRoutines("GoRoutine")
	testGoRoutines("MainThread")

	fmt.Println("Ending")
}
