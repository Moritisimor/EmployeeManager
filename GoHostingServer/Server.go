package main 

import (
	"fmt"
	"net/http"
	"os"
	"strings"
)

func handleCommands(writer http.ResponseWriter, requestPtr *http.Request) {
	command := strings.TrimPrefix(requestPtr.URL.Path, "/command/")
	if command == "exit" || command == "quit" {
		fmt.Printf("Got command: %s from %s, shutting down now...\n", command, requestPtr.RemoteAddr)
		writer.Write([]byte("Shutting down Server now...\n"))
		os.Exit(0)
	}
}

func main() {
	var selectedPort string
	if len(os.Args) < 2 {
		fmt.Println("No args, defaulting to port 8080.")
		selectedPort = "0.0.0.0:8080"
	} else {
		selectedPort = "0.0.0.0:" + os.Args[1]
	}
	fmt.Println("Starting Server...")
	fmt.Printf("Listening on: %s\n", selectedPort)
	http.Handle("/", http.FileServer(http.Dir("Site")))
	http.HandleFunc("/command/", handleCommands)
	http.ListenAndServe(selectedPort, nil)
}
