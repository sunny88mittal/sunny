package main

import "fmt"

// Person Type
type Person struct {
	Name string
}

func (p *Person) talk() {
	fmt.Println("I am ", p.Name)
}

type Android struct {
	Person
	Model string
}

func main() {
	var p = Person{Name: "sunny"}
	p.talk()
}
