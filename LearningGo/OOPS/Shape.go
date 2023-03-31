package main

import (
	"fmt"
	"math"
)

type Shape interface {
	Area() float32
}

type Rectangle struct {
	x float32
	y float32
}

type Circle struct {
	r float32
}

type Square struct {
	l float32
}

func (r *Rectangle) Area() float32 {
	return r.x * r.y
}

func (c Circle) Area() float32 {
	return math.Pi * c.r * c.r
}

func main() {
	// Implementing interface using pointer reciever
	var shape Shape = &Rectangle{2.0, 3.0}
	fmt.Println(shape.Area())

	// Implmeneting interface using copy reciever
	shape = Circle{2.0}
	fmt.Println(shape.Area())
}
