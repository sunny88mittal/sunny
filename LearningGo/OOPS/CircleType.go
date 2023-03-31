package main

import (
	"fmt"
	"math"
)

type Circle struct {
	x float64
	y float64
	r float64
}

func (c *Circle) area() float64 {
	return math.Pi * c.r * c.r
}

func main() {
	var circle1 = Circle{x: 0, y: 0, r: 5}
	fmt.Println(circle1.x)
	fmt.Println(circle1.area())
}
