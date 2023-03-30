package main

import "fmt"

func main() {
	var a = 2
	var b = &a

	fmt.Println(a)
	fmt.Println(*b)
}
